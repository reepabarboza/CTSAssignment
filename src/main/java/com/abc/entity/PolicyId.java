package com.abc.entity;

import java.io.Serializable;


public class PolicyId implements Serializable{


	private static final long serialVersionUID = 1L;
	private Long policyId;
	private Long policyHolderId;
	
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
}
