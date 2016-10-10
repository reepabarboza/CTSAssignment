package com.abc.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PolicyId.class)
public class PolicyData{
	
	@Id
	private Long policyId;
	@Id
	private Long policyHolderId ;
	private String firstName;
	private String lastName;
	private String planId;
	private Date coverageStartDate ;
	private Date coverageEndDate ;
	private BigDecimal individualAccumulatedDed ;
	private BigDecimal familyAccumulatedDed ;
	
	public Long getPolicyId() {
		return policyId;
	}
	
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	
	public Long getPolicyHolderId() {
		return policyHolderId;
	}
	
	public void setPolicyHolderId(Long policyHolderId) {
		this.policyHolderId = policyHolderId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPlanId() {
		return planId;
	}
	
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Date getCoverageStartDate() {
		return coverageStartDate;
	}
	
	public void setCoverageStartDate(Date coverageStartDate) {
		this.coverageStartDate = coverageStartDate;
	}
	
	public Date getCoverageEndDate() {
		return coverageEndDate;
	}
	
	public void setCoverageEndDate(Date coverageEndDate) {
		this.coverageEndDate = coverageEndDate;
	}
	
	public BigDecimal getIndividualAccumulatedDed() {
		return individualAccumulatedDed;
	}
	
	public void setIndividualAccumulatedDed(BigDecimal individualAccumulatedDed) {
		this.individualAccumulatedDed = individualAccumulatedDed;
	}
	
	public BigDecimal getFamilyAccumulatedDed() {
		return familyAccumulatedDed;
	}
	
	public void setFamilyAccumulatedDed(BigDecimal familyAccumulatedDed) {
		this.familyAccumulatedDed = familyAccumulatedDed;
	}

	@Override
	public String toString() {
		return "PolicyData [policyId=" + policyId + ", policyHolderId=" + policyHolderId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", planId=" + planId + ", coverageStartDate=" + coverageStartDate
				+ ", coverageEndDate=" + coverageEndDate + ", individualAccumulatedDed=" + individualAccumulatedDed
				+ ", familyAccumulatedDed=" + familyAccumulatedDed + "]";
	}
	
}

