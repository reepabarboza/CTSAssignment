package com.abc.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.entity.PlanCoverage;


/**
 * Test class for com.abc.repository.PlanCoverageRepository
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanCoverageRepositoryTests {

	@Autowired
	private PlanCoverageRepository planCoverageRepository;
	
	
	/**
	 * Success test case with valid main category, sub category and plan id values
	 * @throws Exception
	 */
	@Test
    public void findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanIdTest() throws Exception {
    	PlanCoverage planCoverage = planCoverageRepository.
    	findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanId("Prescription Drugs", "Generic", "P001");
    	
    	Assert.assertTrue(new BigDecimal(0).compareTo(planCoverage.getDeductibleAmt()) == 0);
    	Assert.assertTrue(new BigDecimal(60).compareTo(planCoverage.getDeductiblePercentage()) == 0);
    	Assert.assertEquals("60% AFTER DEDUCTIBLE", planCoverage.getDeductibleRule());
    }
	
	/**
	 * Test with valid main category and sub category but invalid plan id values. returns null
	 * @throws Exception
	 */
	@Test
    public void findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanIdInvalidTest() throws Exception {
    	PlanCoverage planCoverage = planCoverageRepository.
    	findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanId("Prescription Drugs", "Generic", "P00");
    	
    	Assert.assertNull(planCoverage);
    }
}
