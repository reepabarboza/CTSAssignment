package com.abc.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.entity.PlanDescription;


/**
 * Test class for com.abc.repository.PlanDescriptionRepository
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanDescriptionRepositoryTests {
	
	@Autowired
	private PlanDescriptionRepository planDescriptionRepository;
	
	
	
	/**
	 * Test case with valid Plan ID as input.
	 */
	@Test
    public void findByPlanIdTest() {
    	PlanDescription planDescription = planDescriptionRepository.findByPlanId("P001");
    	
    	Assert.assertEquals("Family", planDescription.getCoverageType());
    	Assert.assertTrue(new BigDecimal(962).compareTo(planDescription.getEstimatedPremium()) == 0);
    	Assert.assertTrue(new BigDecimal(6000).compareTo(planDescription.getAnnualDeductibleIndividual()) == 0);
    	Assert.assertTrue(new BigDecimal(12000).compareTo(planDescription.getAnnualDeductibleFamily()) == 0);
    	
    }

	/**
	 * Test case with invalid Plan ID as input. Returns null
	 */
	@Test
    public void findByPlanIdInvalidTest() {
    	PlanDescription planDescription = planDescriptionRepository.findByPlanId("P0055");
    	
    	Assert.assertNull(planDescription);
    	
    }
}
