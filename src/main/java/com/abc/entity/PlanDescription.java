package com.abc.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Details about the plans.
 *
 */
@Entity
@Table(name = "PlanDescription")
public class PlanDescription {

	@Id
	private String planId;
	private String planName ;
	private String coverageType;
	private BigDecimal estimatedPremium;
	private BigDecimal annualDeductibleIndividual;
	private BigDecimal annualDeductibleFamily;

	public String getPlanId() {
		return planId;
	}
	
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public String getPlanName() {
		return planName;
	}
	
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public String getCoverageType() {
		return coverageType;
	}
	
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	public BigDecimal getEstimatedPremium() {
		return estimatedPremium;
	}
	
	public void setEstimatedPremium(BigDecimal estimatedPremium) {
		this.estimatedPremium = estimatedPremium;
	}
	
	public BigDecimal getAnnualDeductibleIndividual() {
		return annualDeductibleIndividual;
	}
	
	public void setAnnualDeductibleIndividual(BigDecimal annualDeductibleIndividual) {
		this.annualDeductibleIndividual = annualDeductibleIndividual;
	}
	
	public BigDecimal getAnnualDeductibleFamily() {
		return annualDeductibleFamily;
	}
	
	public void setAnnualDeductibleFamily(BigDecimal annualDeductibleFamily) {
		this.annualDeductibleFamily = annualDeductibleFamily;
	}

	@Override
	public String toString() {
		return "PlanDescription [planId=" + planId + ", planName=" + planName + ", coverageType=" + coverageType
				+ ", estimatedPremium=" + estimatedPremium + ", annualDeductibleIndividual="
				+ annualDeductibleIndividual + ", annualDeductibleFamily=" + annualDeductibleFamily + "]";
	}
	
	
	
}
