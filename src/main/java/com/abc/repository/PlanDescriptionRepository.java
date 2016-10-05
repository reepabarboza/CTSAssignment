package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.entity.PlanDescription;

@Repository
public interface PlanDescriptionRepository extends JpaRepository<PlanDescription, String>{
	
	PlanDescription findByPlanId(String planId);

}
