package com.abc.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abc.entity.PolicyData;

@Repository
public interface PolicyDataRepository extends JpaRepository<PolicyData, Long>{
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	PolicyData findByPolicyIdAndPolicyHolderId(Long policyId, Long policyHolderId);
	
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query("update PolicyData p set p.individualAccumulatedDed = :individualAccumulatedDed where p.policyId = :policyId and p.policyHolderId = :policyHolderId")
	int setIndividualDeductibleAmount(@Param("individualAccumulatedDed") BigDecimal individualAccumulatedDed, 
			@Param("policyId") Long policyId, @Param("policyHolderId") Long policyHolderId);
	
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query("update PolicyData p set p.familyAccumulatedDed = :familyAccumulatedDed where p.policyId = :policyId")
	int setFamilyDeductibleAmount(@Param("familyAccumulatedDed") BigDecimal familyAccumulatedDed, 
			@Param("policyId") Long policyId);
}
