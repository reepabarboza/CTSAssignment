package com.abc.processor;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.constant.ErrorMessages;
import com.abc.constant.ProcessingMessages;
import com.abc.entity.PlanCoverage;
import com.abc.entity.PlanDescription;
import com.abc.entity.PolicyData;
import com.abc.entity.PolicyTransaction;
import com.abc.repository.PlanCoverageRepository;
import com.abc.repository.PlanDescriptionRepository;
import com.abc.repository.PolicyDataRepository;

public class PolicyDataProcessor implements ItemProcessor<PolicyTransaction, PolicyTransaction> {
	
	private static final Logger log = LoggerFactory.getLogger(PolicyDataProcessor.class);
	
	@Autowired
	private PolicyDataRepository policyDataRepository;
	@Autowired
	private PlanDescriptionRepository planDescriptionRepository;
	@Autowired
	private PlanCoverageRepository planCoverageRepository;

	@Override
    public PolicyTransaction process(final PolicyTransaction policyTransaction) throws Exception {
      
    	processOutput(policyTransaction);
    	
    	log.info("Final policyTransaction :: " + policyTransaction);
        return policyTransaction;
    }

	

	private void processOutput(PolicyTransaction policyTransaction) {
		
		PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(policyTransaction.getPolicyId(), policyTransaction.getPolicyHolderId());
		
		log.info("policyData :: " + policyData);
		
		if(policyData == null) {
			log.info("No policy holder");
			policyTransaction.setErrorCode(ErrorMessages.E0001.toString());
			policyTransaction.setErrorMessage(ErrorMessages.E0001.getValue());
		} else if(policyData.getCoverageEndDate() != null && policyTransaction.getDateOfService().after(policyData.getCoverageEndDate())) {
			log.info("After coverage end date");
			policyTransaction.setErrorCode(ErrorMessages.E0002.toString());
			policyTransaction.setErrorMessage(ErrorMessages.E0002.getValue());
		} else {
			String planId = policyData.getPlanId();
			
			PlanCoverage planCoverage = planCoverageRepository.findByMainCategoryAndSubCategoryAndPlanId(
					policyTransaction.getCoverageMainCategory(), policyTransaction.getCoverageSubCategory(), planId);
			
			
			if(planCoverage == null) {
				log.info("No plan found");
				policyTransaction.setErrorCode(ErrorMessages.E0003.toString());
				policyTransaction.setErrorMessage(ErrorMessages.E0003.getValue());
			} else {
				log.info("Plan found");
				policyTransaction.setDeductibleRule(planCoverage.getDeductibleRule());
				policyTransaction.setDeductiblePercentage(planCoverage.getDeductiblePercentage());
				
				//calculateDeductible(policyTransaction, policyData);
				BigDecimal planAmt = new BigDecimal(0);
				BigDecimal deductiblePercentage = new BigDecimal(policyTransaction.getDeductiblePercentage());
				
				PlanDescription planDescription = planDescriptionRepository.findByPlanId(policyData.getPlanId());
				
				
				log.info("planDescription.getAnnualDeductibleIndividual() :: " + planDescription.getAnnualDeductibleIndividual());
				log.info("policyData.getIndividualAccumulatedDed() :: " + policyData.getIndividualAccumulatedDed());
				
				if(planDescription.getAnnualDeductibleIndividual() != null && policyData.getIndividualAccumulatedDed().compareTo(planDescription.getAnnualDeductibleIndividual()) >= 0) {
					planAmt = deductiblePercentage.multiply(policyTransaction.getBilledAmount()).divide(new BigDecimal(100));
					calculateDeductible(policyTransaction, policyData, planAmt);
					policyTransaction.setProcessingMessage(ProcessingMessages.INDIVIDUAL_PLAN_MET);
				} else if(planDescription.getAnnualDeductibleFamily() != null && policyData.getFamilyAccumulatedDed().compareTo(planDescription.getAnnualDeductibleFamily()) >= 0) {
					planAmt = deductiblePercentage.multiply(policyTransaction.getBilledAmount()).divide(new BigDecimal(100));
					calculateDeductible(policyTransaction, policyData, planAmt);
					policyTransaction.setProcessingMessage(ProcessingMessages.FAMILY_PLAN_MET);
				} else {
					planAmt = new BigDecimal(0);
					calculateDeductible(policyTransaction, policyData, planAmt);
					policyTransaction.setProcessingMessage(ProcessingMessages.INDIVIDUAL_FAMILY_PLAN_NOT_MET);
				}
			}
		}
	}

	private void calculateDeductible(PolicyTransaction policyTransaction, PolicyData policyData, BigDecimal planAmt) {
		BigDecimal policyHolderAmt = policyTransaction.getBilledAmount().subtract(planAmt);
		BigDecimal totalIndividualAccumulatedDed = new BigDecimal(0);
		BigDecimal totalFamilyAccumulatedDed = new BigDecimal(0);
		
		policyTransaction.setPlanAmt(planAmt);
		policyTransaction.setPolicyHolderAmt(policyHolderAmt);
		
		totalIndividualAccumulatedDed = policyData.getIndividualAccumulatedDed().add(policyHolderAmt);
		totalFamilyAccumulatedDed = policyData.getFamilyAccumulatedDed().add(policyHolderAmt);
		
		log.info(" totalIndividualAccumulatedDed :: " + totalIndividualAccumulatedDed);
		log.info(" totalFamilyAccumulatedDed :: " + totalFamilyAccumulatedDed);
		
		policyTransaction.setIndividualAccumulatedDed(totalIndividualAccumulatedDed);
		policyTransaction.setFamilyAccumulatedDed(totalFamilyAccumulatedDed);
		
		policyDataRepository.setIndividualDeductibleAmount(totalIndividualAccumulatedDed, policyData.getPolicyId(), policyData.getPolicyHolderId());
		policyDataRepository.setFamilyDeductibleAmount(totalFamilyAccumulatedDed, policyData.getPolicyId());
		
	}


}
