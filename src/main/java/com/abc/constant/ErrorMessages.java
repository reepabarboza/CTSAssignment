package com.abc.constant;



/**
 * Error codes and error message details
 *
 */
public enum ErrorMessages {
	E0001("Policy holder does not exists"),
	E0002("Date of Service is after the policy Coverage End Date"),
	E0003("No plan coverage found for the given main and subcategory");
	
	private String value;
	
	private ErrorMessages(String value) {
		this.value = value;
	}
	
	public String getValue() { return value; }
}
