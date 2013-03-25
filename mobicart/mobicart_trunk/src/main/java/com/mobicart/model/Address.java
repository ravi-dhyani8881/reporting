package com.mobicart.model;

import com.mobicart.dto.LatLong;

public class Address {

	
	/**
	 *This field corresponds to the database column users_address.id
	 * 
	 */
	private Long id;
	/**
	 *This field corresponds to the database column users_address.user_id
	 * 
	 */
	private Long userId;
	/**
	 *This field corresponds to the database column users_address.s_address_type
	 * 
	 */
	private String sAddressType;
	/**
	 *This field corresponds to the database column users_address.s_address
	 * 
	 */
	private String sAddress;
	/**
	 *This field corresponds to the database column users_address.s_city
	 * 
	 */
	private String sCity;
	/**
	 *This field corresponds to the database column users_address.s_state
	 * 
	 */
	private String sState;
	/**
	 *This field corresponds to the database column users_address.s_zip
	 * 
	 */
	private String sZip;
	/**
	 *This field corresponds to the database column users_address.s_country
	 * 
	 */
	private String sCountry;
	/**
	 *This field corresponds to the database column users_address.b_enabled
	 * 
	 */
	private Boolean bEnabled;

	
	private Long stateId;
	
	private Long countryId;
	
	private LatLong latLong; 
	
	
	
	public Address() {
		super();
	}

	
	public Address(Long id) {
		super();
		this.id = id;
	}


	public Address(String sAddressType, String sAddress, String sCity,
			String sState, String sZip, String sCountry) {
		super();
		this.sAddressType = sAddressType;
		this.sAddress = sAddress;
		this.sCity = sCity;
		this.sState = sState;
		this.sZip = sZip;
		this.sCountry = sCountry;
	}

	
	/**
	 *  This method returns the value of the database column users_address.id
	 * @return  the value of users_address.id
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 *  This method sets the value of the database column users_address.id
	 * @param id  the value for users_address.id
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *  This method returns the value of the database column users_address.user_id
	 * @return  the value of users_address.user_id
	 * 
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 *  This method sets the value of the database column users_address.user_id
	 * @param userId  the value for users_address.user_id
	 * 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 *  This method returns the value of the database column users_address.s_address_type
	 * @return  the value of users_address.s_address_type
	 * 
	 */
	public String getsAddressType() {
		return sAddressType;
	}

	/**
	 *  This method sets the value of the database column users_address.s_address_type
	 * @param sAddressType  the value for users_address.s_address_type
	 * 
	 */
	public void setsAddressType(String sAddressType) {
		this.sAddressType = sAddressType == null ? null : sAddressType.trim();
	}

	/**
	 *  This method returns the value of the database column users_address.s_address
	 * @return  the value of users_address.s_address
	 * 
	 */
	public String getsAddress() {
		return sAddress;
	}

	/**
	 *  This method sets the value of the database column users_address.s_address
	 * @param sAddress  the value for users_address.s_address
	 * 
	 */
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress == null ? null : sAddress.trim();
	}

	/**
	 *  This method returns the value of the database column users_address.s_city
	 * @return  the value of users_address.s_city
	 * 
	 */
	public String getsCity() {
		return sCity;
	}

	/**
	 *  This method sets the value of the database column users_address.s_city
	 * @param sCity  the value for users_address.s_city
	 * 
	 */
	public void setsCity(String sCity) {
		this.sCity = sCity == null ? null : sCity.trim();
	}

	/**
	 *  This method returns the value of the database column users_address.s_state
	 * @return  the value of users_address.s_state
	 * 
	 */
	public String getsState() {
		return sState;
	}

	/**
	 *  This method sets the value of the database column users_address.s_state
	 * @param sState  the value for users_address.s_state
	 * 
	 */
	public void setsState(String sState) {
		this.sState = sState == null ? null : sState.trim();
	}

	/**
	 *  This method returns the value of the database column users_address.s_zip
	 * @return  the value of users_address.s_zip
	 * 
	 */
	public String getsZip() {
		return sZip;
	}

	/**
	 *  This method sets the value of the database column users_address.s_zip
	 * @param sZip  the value for users_address.s_zip
	 * 
	 */
	public void setsZip(String sZip) {
		this.sZip = sZip == null ? null : sZip.trim();
	}

	/**
	 *  This method returns the value of the database column users_address.s_country
	 * @return  the value of users_address.s_country
	 * 
	 */
	public String getsCountry() {
		return sCountry;
	}

	/**
	 *  This method sets the value of the database column users_address.s_country
	 * @param sCountry  the value for users_address.s_country
	 * 
	 */
	public void setsCountry(String sCountry) {
		this.sCountry = sCountry == null ? null : sCountry.trim();
	}

	/**
	 *  This method returns the value of the database column users_address.b_enabled
	 * @return  the value of users_address.b_enabled
	 * 
	 */
	public Boolean getbEnabled() {
		return bEnabled;
	}

	/**
	 *  This method sets the value of the database column users_address.b_enabled
	 * @param bEnabled  the value for users_address.b_enabled
	 * 
	 */
	public void setbEnabled(Boolean bEnabled) {
		this.bEnabled = bEnabled;
	}

	

	public Long getStateId() {
		return stateId;
	}


	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}


	public Long getCountryId() {
		return countryId;
	}


	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
	

	public LatLong getLatLong() {
		return latLong;
	}


	public void setLatLong(LatLong latLong) {
		this.latLong = latLong;
	}


	@Override
	public String toString() {
		return "[ Street=" + sAddress + ", City=" + sCity
				+ ", State=" + sState + ", PostalCode=" + sZip + ", Country="
				+ sCountry + "]";
	}
	
	
	public String toStringFormatted() {
		/*return "Street : " + sAddress + "<br/>" + "City : " + sCity + "<br/>" + "State : " + sState + "<br/>" + "PostalCode : " + sZip + "<br/>" + "Country : " + sCountry;*/
		return sAddress + "<br/>" + sCity + "<br/>" + sState + "<br/>" + sZip + "<br/>" + sCountry;
	}
	
	
	
	
	
}