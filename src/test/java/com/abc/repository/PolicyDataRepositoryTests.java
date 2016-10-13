package com.abc.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.abc.entity.PolicyData;

/**
 * Test class for com.abc.repository.PolicyDataRepository
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyDataRepositoryTests {
	
	@Autowired
	private PolicyDataRepository policyDataRepository;
	
	
	/**
	 * Test for findByPolicyIdAndPolicyHolderId method with valid policyId and policyHolderId
	 */
	@Test
	@Transactional
    public void findByPolicyIdAndPolicyHolderIdTest() {
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
    	
    	Assert.assertEquals("Jina", policyData.getFirstName());
    	Assert.assertEquals("Collins", policyData.getLastName());
    	Assert.assertEquals("P001", policyData.getPlanId());
    }
	
	/**
	 * Test for findByPolicyIdAndPolicyHolderId method with invalid policyId and policyHolderId
	 */
	@Test
	@Transactional
    public void findByPolicyIdAndPolicyHolderIdInvalidTest() {
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(10000122), new Long(90));
    	
    	Assert.assertNull(policyData);
    }
    
	/**
	 * Test for setIndividualDeductibleAmount with valid individual Accumulated Deductible, policy Id and policy Holder Id values
	 */
    @Test
    @Transactional
    @Rollback(true)
    public void setIndividualDeductibleAmountTest() {
    	
    	policyDataRepository.setIndividualDeductibleAmount(new BigDecimal(2000), new Long(100001), new Long(1000012));
    	
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
    	
    	Assert.assertTrue(new BigDecimal(2000.00).compareTo(policyData.getIndividualAccumulatedDed()) == 0);
    }
    
    
    /**
	 * Test for setIndividualDeductibleAmount with valid individual Accumulated Deductible,invalid policy Id and invalid policy Holder Id values
	 */
    @Test
    @Transactional
    @Rollback(true)
    public void setIndividualDeductibleAmountInvalidTest() {
    	
    	int updateFlag = policyDataRepository.setIndividualDeductibleAmount(new BigDecimal(2000), new Long(10000111), new Long(10000123));
    	
    	Assert.assertEquals(0, updateFlag);
    }
    
    
    /**
	 * Test for setFamilyDeductibleAmount with valid family Accumulated Deductible and policy Id
	 */
    @Test
    @Transactional
    @Rollback(true)
    public void setFamilyDeductibleAmountTest() {
    	
    	policyDataRepository.setFamilyDeductibleAmount(new BigDecimal(2000), new Long(100001));
    	
    	PolicyData policyData = policyDataRepository.findByPolicyIdAndPolicyHolderId(new Long(100001), new Long(1000012));
    	
    	Assert.assertTrue(new BigDecimal(2000.00).compareTo(policyData.getFamilyAccumulatedDed()) == 0);
    }
	
    /**
	 * Test for setFamilyDeductibleAmount with valid family Accumulated Deductible and invalid policy Id
	 */
    @Test
    @Transactional
    @Rollback(true)
    public void setFamilyDeductibleAmountInvalidTest() {
    	
    	int updateFlag = policyDataRepository.setFamilyDeductibleAmount(new BigDecimal(2000), new Long(1000013323));
    	
    	Assert.assertEquals(0, updateFlag);
    }
}
