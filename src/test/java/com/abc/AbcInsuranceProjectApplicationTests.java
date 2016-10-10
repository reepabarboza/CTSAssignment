package com.abc;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.entity.PlanCoverage;
import com.abc.entity.PlanDescription;
import com.abc.entity.PolicyData;
import com.abc.entity.PolicyTransaction;
import com.abc.processor.PolicyDataProcessor;
import com.abc.repository.PlanCoverageRepository;
import com.abc.repository.PlanDescriptionRepository;
import com.abc.repository.PolicyDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbcInsuranceProjectApplicationTests {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
    @Autowired
    PolicyDataProcessor policyDataProcessor;
    
    @Autowired
	private PlanCoverageRepository planCoverageRepository;
    
    @Autowired
	private PolicyDataRepository policyDataRepository;
    
	@Autowired
	private PlanDescriptionRepository planDescriptionRepository;

    @Test
    public void launchPolicyUploadJobTest() throws Exception {
    	Path path = Paths.get("src/main/resources/");
    	String inputFileName = path + "/SampleInputTransactionFile.csv";
    	String outputFileName = path + "/SampleOutputTransactionFile.csv";
    	
    	JobParameters jobParameters = new JobParametersBuilder()
        		  .addString("inputFilePath", inputFileName)
        		  .addString("outputFilePath", outputFileName).toJobParameters();
          
    	JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
    
    @Test
    public void processPolicyTest() throws Exception {
    	
    	PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(new Long(100001));
		policyTransaction.setPolicyHolderId(new Long(1000011));
		policyTransaction.setDateOfService("5/6/2016");
		policyTransaction.setCoverageMainCategory("Inpatient Hospital Care");
		policyTransaction.setCoverageSubCategory("ROOM AND BOARD");
		policyTransaction.setBilledAmount(new BigDecimal(1000));
		
		policyTransaction = policyDataProcessor.process(policyTransaction);
		
		PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
		
		Assert.assertEquals(new BigDecimal(1000), policyTransaction.getPolicyHolderAmt());
		Assert.assertEquals(new BigDecimal(0), policyTransaction.getPlanAmt());
		Assert.assertEquals(40, policyTransaction.getDeductiblePercentage());
		Assert.assertEquals("40% AFTER DEDUCTIBLE", policyTransaction.getDeductibleRule());
		Assert.assertEquals("ANNUAL DECUCTIBLE  (INDIVIDUAL or FAMILY) not met", policyTransaction.getProcessingMessage());
		
		/*PolicyTransaction expectedPolicyTransaction = new PolicyTransaction();
		expectedPolicyTransaction.setPolicyId(new Long(100001));
		expectedPolicyTransaction.setPolicyHolderId(new Long(1000011));
		expectedPolicyTransaction.setDateOfService("5/6/2016");
		expectedPolicyTransaction.setCoverageMainCategory("Inpatient Hospital Care");
		expectedPolicyTransaction.setCoverageSubCategory("ROOM AND BOARD");
		expectedPolicyTransaction.setBilledAmount(new BigDecimal(1000));
		expectedPolicyTransaction.setPolicyHolderAmt(new BigDecimal(1000));
		expectedPolicyTransaction.setPlanAmt(new BigDecimal(0));
		expectedPolicyTransaction.setDeductiblePercentage(40);
		expectedPolicyTransaction.setDeductibleRule("40% AFTER DEDUCTIBLE");
		
		// Individual accumulated deductible will be fetched from Policy Data Table. So this value has be set from the table on run
		expectedPolicyTransaction.setIndividualAccumulatedDed(new BigDecimal(1000));
		// Family accumulated deductible will be fetched from Policy Data Table. So this value has be set from the table on run
		expectedPolicyTransaction.setFamilyAccumulatedDed(new BigDecimal(1000));
		
		expectedPolicyTransaction.setErrorCode(null);
		expectedPolicyTransaction.setErrorMessage(null);
		expectedPolicyTransaction.setProcessingMessage("ANNUAL DECUCTIBLE  (INDIVIDUAL or FAMILY) not met");
		
		
		Assert.assertEquals(expectedPolicyTransaction, policyTransaction);*/
		
		
    }
    

    @Test
    public void findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanIdTest() throws Exception {
    	PlanCoverage planCoverage = planCoverageRepository.
    	findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanId("Prescription Drugs", "Generic", "P001");
    	
    	/*PlanCoverage expectedPlanCoverage = new PlanCoverage();
    	expectedPlanCoverage.setDeductibleAmt(new BigDecimal(0));
    	expectedPlanCoverage.setDeductiblePercentage(new BigDecimal(40));
    	expectedPlanCoverage.setDeductibleRule("");*/
    	
    	Assert.assertEquals(new BigDecimal(0), planCoverage.getDeductibleAmt());
    	Assert.assertEquals(new BigDecimal(60), planCoverage.getDeductiblePercentage());
    	Assert.assertEquals("60% AFTER DEDUCTIBLE", planCoverage.getDeductibleRule());
    }
    
    @Test
    public void findByPlanIdTest() throws Exception {
    	PlanDescription planDescription = planDescriptionRepository.findByPlanId("P001");
    	
    	Assert.assertEquals("Family", planDescription.getCoverageType());
    	Assert.assertEquals(new BigDecimal(962), planDescription.getEstimatedPremium());
    	Assert.assertEquals(new BigDecimal(6000), planDescription.getAnnualDeductibleIndividual());
    	Assert.assertEquals(new BigDecimal(12000), planDescription.getAnnualDeductibleFamily());
    }
    
    @Test
    public void findByPolicyIdAndPolicyHolderIdTest() throws Exception {
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
    	
    	Assert.assertEquals("Jina", policyData.getFirstName());
    	Assert.assertEquals("Collons", policyData.getLastName());
    	Assert.assertEquals("P001", policyData.getPlanId());
    	Assert.assertEquals("12/5/2008", policyData.getCoverageStartDate());
    	Assert.assertEquals(null, policyData.getCoverageEndDate());
    	Assert.assertEquals(new BigDecimal(0), policyData.getIndividualAccumulatedDed());
    	Assert.assertEquals(new BigDecimal(0), policyData.getFamilyAccumulatedDed());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void setIndividualDeductibleAmountTest() throws Exception {
    	
    	policyDataRepository.setIndividualDeductibleAmount(new BigDecimal(2000), new Long(100001), new Long(1000012));
    	
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
    	Assert.assertEquals(new BigDecimal(2000), policyData.getIndividualAccumulatedDed());
    	
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void setFamilyDeductibleAmountTest() throws Exception {
    	
    	policyDataRepository.setFamilyDeductibleAmount(new BigDecimal(2000), new Long(100001));
    	
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
    	
    	Assert.assertEquals(new BigDecimal(2000), policyData.getFamilyAccumulatedDed());
    }

}
