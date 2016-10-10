package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.entity.PlanCoverage;

@Repository
public interface PlanCoverageRepository  extends JpaRepository<PlanCoverage, Long>{

	PlanCoverage findByMainCategoryIgnoreCaseAndSubCategoryIgnoreCaseAndPlanId(String mainCategory, String subCategory, String planId);
}
