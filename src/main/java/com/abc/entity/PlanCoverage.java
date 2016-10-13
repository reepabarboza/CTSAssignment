package com.abc.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Plan coverage details
 *
 */
@Entity
public class PlanCoverage {

	@Id
	@GeneratedValue
	private Long id;
	private String mainCategory;
	private String subCategory;
	private String planId;
	private BigDecimal deductiblePercentage;
	private BigDecimal deductibleAmt;
	private String deductibleRule;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public BigDecimal getDeductiblePercentage() {
		return deductiblePercentage;
	}
	public void setDeductiblePercentage(BigDecimal deductiblePercentage) {
		this.deductiblePercentage = deductiblePercentage;
	}
	
	public BigDecimal getDeductibleAmt() {
		return deductibleAmt;
	}
	public void setDeductibleAmt(BigDecimal deductibleAmt) {
		this.deductibleAmt = deductibleAmt;
	}
	public String getDeductibleRule() {
		return deductibleRule;
	}
	public void setDeductibleRule(String deductibleRule) {
		this.deductibleRule = deductibleRule;
	}
	@Override
	public String toString() {
		return "PlanCoverage [id=" + id + ", mainCategory=" + mainCategory + ", subCategory=" + subCategory
				+ ", planId=" + planId + ", deductiblePercentage=" + deductiblePercentage + ", deductibleRule="
				+ deductibleRule + "]";
	}
	
}
