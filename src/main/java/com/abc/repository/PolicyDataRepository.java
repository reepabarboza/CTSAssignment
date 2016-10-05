package com.abc.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.abc.entity.PolicyData;

@Repository
@Transactional
public interface PolicyDataRepository extends JpaRepository<PolicyData, Long>{
	
	PolicyData findByPolicyIdAndPolicyHolderId(@Param("policyId") Long policyId, 
			@Param("policyHolderId") Long policyHolderId);
	
	@Modifying
	@Transactional
	@Query("update PolicyData p set p.individualAccumulatedDed = :individualAccumulatedDed where p.policyId = :policyId and p.policyHolderId = :policyHolderId")
	int setIndividualDeductibleAmount(@Param("individualAccumulatedDed") BigDecimal individualAccumulatedDed, 
			@Param("policyId") Long policyId, @Param("policyHolderId") Long policyHolderId);
	
	@Modifying
	@Transactional
	@Query("update PolicyData p set p.familyAccumulatedDed = :familyAccumulatedDed where p.policyId = :policyId")
	int setFamilyDeductibleAmount(@Param("familyAccumulatedDed") BigDecimal familyAccumulatedDed, 
			@Param("policyId") Long policyId);
}
