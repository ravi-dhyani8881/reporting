package com.mobicart.model.api;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@JsonWriteNullProperties(false)
public class StateApi {

	private Long stateId;
    private String stateName;
    private String stateCode;
    private Long countryId;
	
    
    public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
    
       	
}
