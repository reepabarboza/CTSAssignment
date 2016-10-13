package com.abc.entity;

import java.math.BigDecimal;


/**
 * Processed policy details
 *
 */
public class PolicyTransaction {
	
	private Long policyId;
	private Long policyHolderId;
	private String dateOfService;
	private String coverageMainCategory;
	private String coverageSubCategory;
	private BigDecimal billedAmount;
	private BigDecimal policyHolderAmt;
	private BigDecimal planAmt;
	private BigDecimal deductiblePercentage;
	private String deductibleRule;
	private BigDecimal individualAccumulatedDed;
	private BigDecimal familyAccumulatedDed;
	private String errorCode;
	private String errorMessage;
	private String processingMessage;
	
	
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
	
	public String getDateOfService() {
		return dateOfService;
	}
	
	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}
	
	public String getCoverageMainCategory() {
		return coverageMainCategory;
	}
	
	public void setCoverageMainCategory(String coverageMainCategory) {
		this.coverageMainCategory = coverageMainCategory;
	}
	
	public String getCoverageSubCategory() {
		return coverageSubCategory;
	}
	
	public void setCoverageSubCategory(String coverageSubCategory) {
		this.coverageSubCategory = coverageSubCategory;
	}
	
	public BigDecimal getBilledAmount() {
		return billedAmount;
	}
	
	public void setBilledAmount(BigDecimal billedAmount) {
		this.billedAmount = billedAmount;
	}

	public BigDecimal getPolicyHolderAmt() {
		return policyHolderAmt;
	}
	
	public BigDecimal getPlanAmt() {
		return planAmt;
	}

	public void setPlanAmt(BigDecimal planAmt) {
		this.planAmt = planAmt;
	}
	

	public BigDecimal getDeductiblePercentage() {
		return deductiblePercentage;
	}

	public void setDeductiblePercentage(BigDecimal deductiblePercentage) {
		this.deductiblePercentage = deductiblePercentage;
	}

	public void setPolicyHolderAmt(BigDecimal policyHolderAmt) {
		this.policyHolderAmt = policyHolderAmt;
	}

	public String getDeductibleRule() {
		return deductibleRule;
	}

	public void setDeductibleRule(String deductibleRule) {
		this.deductibleRule = deductibleRule;
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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getProcessingMessage() {
		return processingMessage;
	}

	public void setProcessingMessage(String processingMessage) {
		this.processingMessage = processingMessage;
	}

	@Override
	public String toString() {
		return "PolicyTransaction [policyId=" + policyId + ", policyHolderId=" + policyHolderId + ", dateOfService="
				+ dateOfService + ", coverageMainCategory=" + coverageMainCategory + ", coverageSubCategory="
				+ coverageSubCategory + ", billedAmount=" + billedAmount + ", policyHolderAmt=" + policyHolderAmt
				+ ", planAmt=" + planAmt + ", deductiblePercentage=" + deductiblePercentage + ", deductibleRule="
				+ deductibleRule + ", individualAccumulatedDed=" + individualAccumulatedDed + ", familyAccumulatedDed="
				+ familyAccumulatedDed + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ ", processingMessage=" + processingMessage + "]";
	}
	
	
	
}
