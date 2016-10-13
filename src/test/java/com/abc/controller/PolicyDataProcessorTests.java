package com.abc.controller;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.abc.constant.ErrorMessages;
import com.abc.constant.ProcessingMessages;
import com.abc.entity.PolicyTransaction;
import com.abc.processor.PolicyDataProcessor;

/**
 * Test class for com.abc.controller.PolicyTransactionController
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyDataProcessorTests {
	
	@Autowired
    PolicyDataProcessor policyDataProcessor;
	
	/**
	 * Test policyDataProcessor.process method with valid Policy Id, Policy Holder Id,
	 * Date of service, main category, sub category, billed amount values
	 * @throws Exception
	 */
	@Test
    @Transactional
    @Rollback(true)
    public void processPolicyTest() throws Exception {
    	
    	PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(new Long(100001));
		policyTransaction.setPolicyHolderId(new Long(1000011));
		policyTransaction.setDateOfService("5/6/2016");
		policyTransaction.setCoverageMainCategory("Inpatient Hospital Care");
		policyTransaction.setCoverageSubCategory("ROOM AND BOARD");
		policyTransaction.setBilledAmount(new BigDecimal(1000));
		
		policyTransaction = policyDataProcessor.process(policyTransaction);
		
		Assert.assertTrue(new BigDecimal(1000).compareTo(policyTransaction.getPolicyHolderAmt()) == 0);
		Assert.assertTrue(new BigDecimal(0).compareTo(policyTransaction.getPlanAmt()) == 0);
		Assert.assertTrue(new BigDecimal(40).compareTo(policyTransaction.getDeductiblePercentage()) == 0);
		Assert.assertEquals("40% AFTER DEDUCTIBLE", policyTransaction.getDeductibleRule());
		Assert.assertTrue(new BigDecimal(1000).compareTo(policyTransaction.getIndividualAccumulatedDed()) == 0);
		Assert.assertTrue(new BigDecimal(1000).compareTo(policyTransaction.getFamilyAccumulatedDed()) == 0);
		Assert.assertEquals(null, policyTransaction.getErrorCode());
		Assert.assertEquals(null, policyTransaction.getErrorMessage());
		Assert.assertEquals(ProcessingMessages.INDIVIDUAL_FAMILY_PLAN_NOT_MET, policyTransaction.getProcessingMessage());
		
    }
	
	
	/**
	 * Test policyDataProcessor.process method with invalid Policy Id and Policy Holder Id.
	 * Returns ErrorMessages.E0001 code and value
	 * @throws Exception
	 */
	@Test
    @Transactional
    @Rollback(true)
    public void processPolicyInvalidTest() throws Exception {
    	
    	PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(new Long(10000133));
		policyTransaction.setPolicyHolderId(new Long(100003311));
		policyTransaction.setDateOfService("5/6/2016");
		policyTransaction.setCoverageMainCategory("Inpatient Hospital Care");
		policyTransaction.setCoverageSubCategory("ROOM AND BOARD");
		policyTransaction.setBilledAmount(new BigDecimal(1000));
		
		policyTransaction = policyDataProcessor.process(policyTransaction);
		
		Assert.assertEquals(ErrorMessages.E0001.toString(), policyTransaction.getErrorCode());
		Assert.assertEquals(ErrorMessages.E0001.getValue(), policyTransaction.getErrorMessage());
		Assert.assertEquals(null, policyTransaction.getProcessingMessage());
		
    }
	
	/**
	 * Test policyDataProcessor.process method with policy Date of Service after the policy coverage end date.
	 * Returns ErrorMessages.E0002 code and value
	 * @throws Exception
	 */
	@Test
    @Transactional
    @Rollback(true)
    public void processPolicyInvalidDateOfServiceTest() throws Exception {
    	
    	PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(new Long(100004));
		policyTransaction.setPolicyHolderId(new Long(1000041));
		policyTransaction.setDateOfService("10/10/2016");
		policyTransaction.setCoverageMainCategory("Inpatient Hospital Care");
		policyTransaction.setCoverageSubCategory("ROOM AND BOARD");
		policyTransaction.setBilledAmount(new BigDecimal(2000));
		
		policyTransaction = policyDataProcessor.process(policyTransaction);
		
		Assert.assertEquals(ErrorMessages.E0002.toString(), policyTransaction.getErrorCode());
		Assert.assertEquals(ErrorMessages.E0002.getValue(), policyTransaction.getErrorMessage());
		Assert.assertEquals(null, policyTransaction.getProcessingMessage());
		
    }
	
	/**
	 * Test policyDataProcessor.process method with invalid main and sub category values
	 * Returns ErrorMessages.E0003 code and value
	 * @throws Exception
	 */
	@Test
    @Transactional
    @Rollback(true)
    public void processPolicyInvalidMainAndSubCategoryTest() throws Exception {
    	
    	PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(new Long(100001));
		policyTransaction.setPolicyHolderId(new Long(1000012));
		policyTransaction.setDateOfService("10/10/2016");
		policyTransaction.setCoverageMainCategory("Invalid Inpatient Hospital Care");
		policyTransaction.setCoverageSubCategory("Invalid ROOM AND BOARD");
		policyTransaction.setBilledAmount(new BigDecimal(2000));
		
		policyTransaction = policyDataProcessor.process(policyTransaction);
		
		Assert.assertEquals(ErrorMessages.E0003.toString(), policyTransaction.getErrorCode());
		Assert.assertEquals(ErrorMessages.E0003.getValue(), policyTransaction.getErrorMessage());
		Assert.assertEquals(null, policyTransaction.getProcessingMessage());
		
    }
	
	
	/**
	 * Test policyDataProcessor.process method with main and sub category 'Emergency And Urgent Care' and 'URGENT CARE VISIT' respectively
	 * The provided categories have deductible amount as $100 for plan ID P002.
	 * @throws Exception
	 */
	@Test
    @Transactional
    @Rollback(true)
    public void processPolicyWithDeductibleAmtTest() throws Exception {
    	
    	PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(new Long(100002));
		policyTransaction.setPolicyHolderId(new Long(1000021));
		policyTransaction.setDateOfService("10/10/2016");
		policyTransaction.setCoverageMainCategory("Emergency And Urgent Care");
		policyTransaction.setCoverageSubCategory("URGENT CARE VISIT");
		policyTransaction.setBilledAmount(new BigDecimal(1000));
		
		policyTransaction = policyDataProcessor.process(policyTransaction);
		
		Assert.assertTrue(new BigDecimal(900).compareTo(policyTransaction.getPolicyHolderAmt()) == 0);
		Assert.assertTrue(new BigDecimal(100).compareTo(policyTransaction.getPlanAmt()) == 0);
		Assert.assertTrue(new BigDecimal(0).compareTo(policyTransaction.getDeductiblePercentage()) == 0);
		Assert.assertTrue(new BigDecimal(4900).compareTo(policyTransaction.getIndividualAccumulatedDed()) == 0);
		Assert.assertTrue(new BigDecimal(4900).compareTo(policyTransaction.getFamilyAccumulatedDed()) == 0);
		Assert.assertEquals(null, policyTransaction.getErrorCode());
		Assert.assertEquals(null, policyTransaction.getErrorMessage());
		Assert.assertEquals("PLAN PAID AMOUNT $100.00", policyTransaction.getProcessingMessage());
		
    }
} 
