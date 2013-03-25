package com.mobicart.model;

import java.util.Date;

public class Api {

	/**
	 *  This field corresponds to the database column api.id
	 * 
	 */
	private Long id;
	/**
	 *  This field corresponds to the database column api.user_id
	 * 
	 */
	private Long userId;
	/**
	 *  This field corresponds to the database column api.api_key
	 * 
	 */
	private String apiKey;
	/**
	 *  This field corresponds to the database column api.oauth_secret
	 * 
	 */
	private String oauthSecret;
	/**
	 *  This field corresponds to the database column api.threshold_general_count
	 * 
	 */
	private Integer thresholdGeneralCount;
	/**
	 *  This field corresponds to the database column api.threshold_store_count
	 * 
	 */
	private Integer thresholdStoreCount;
	/**
	 *  This field corresponds to the database column api.threshold_refresh_count
	 * 
	 */
	private Integer thresholdRefreshCount;
	/**
	 *  This field corresponds to the database column api.general_counter
	 * 
	 */
	private Integer generalCounter;
	/**
	 *  This field corresponds to the database column api.store_counter
	 * 
	 */
	private Integer storeCounter;
	/**
	 *  This field corresponds to the database column api.last_refresh_time
	 * 
	 */
	private Date lastRefreshTime;

	
	public Api() {
		super();
	}

	public Api(Long id) {
		super();
		this.id = id;
	}
	

	/**
	 *  This method returns the value of the database column api.id
	 * @return  the value of api.id
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 *  This method sets the value of the database column api.id
	 * @param id  the value for api.id
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *  This method returns the value of the database column api.user_id
	 * @return  the value of api.user_id
	 * 
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 *  This method sets the value of the database column api.user_id
	 * @param userId  the value for api.user_id
	 * 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 *  This method returns the value of the database column api.api_key
	 * @return  the value of api.api_key
	 * 
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 *  This method sets the value of the database column api.api_key
	 * @param apiKey  the value for api.api_key
	 * 
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey == null ? null : apiKey.trim();
	}

	/**
	 *  This method returns the value of the database column api.oauth_secret
	 * @return  the value of api.oauth_secret
	 * 
	 */
	public String getOauthSecret() {
		return oauthSecret;
	}

	/**
	 *  This method sets the value of the database column api.oauth_secret
	 * @param oauthSecret  the value for api.oauth_secret
	 * 
	 */
	public void setOauthSecret(String oauthSecret) {
		this.oauthSecret = oauthSecret == null ? null : oauthSecret.trim();
	}

	/**
	 *  This method returns the value of the database column api.threshold_general_count
	 * @return  the value of api.threshold_general_count
	 * 
	 */
	public Integer getThresholdGeneralCount() {
		return thresholdGeneralCount;
	}

	/**
	 *  This method sets the value of the database column api.threshold_general_count
	 * @param thresholdGeneralCount  the value for api.threshold_general_count
	 * 
	 */
	public void setThresholdGeneralCount(Integer thresholdGeneralCount) {
		this.thresholdGeneralCount = thresholdGeneralCount;
	}

	/**
	 *  This method returns the value of the database column api.threshold_store_count
	 * @return  the value of api.threshold_store_count
	 * 
	 */
	public Integer getThresholdStoreCount() {
		return thresholdStoreCount;
	}

	/**
	 *  This method sets the value of the database column api.threshold_store_count
	 * @param thresholdStoreCount  the value for api.threshold_store_count
	 * 
	 */
	public void setThresholdStoreCount(Integer thresholdStoreCount) {
		this.thresholdStoreCount = thresholdStoreCount;
	}

	/**
	 *  This method returns the value of the database column api.threshold_refresh_count
	 * @return  the value of api.threshold_refresh_count
	 * 
	 */
	public Integer getThresholdRefreshCount() {
		return thresholdRefreshCount;
	}

	/**
	 *  This method sets the value of the database column api.threshold_refresh_count
	 * @param thresholdRefreshCount  the value for api.threshold_refresh_count
	 * 
	 */
	public void setThresholdRefreshCount(Integer thresholdRefreshCount) {
		this.thresholdRefreshCount = thresholdRefreshCount;
	}

	/**
	 *  This method returns the value of the database column api.general_counter
	 * @return  the value of api.general_counter
	 * 
	 */
	public Integer getGeneralCounter() {
		return generalCounter;
	}

	/**
	 *  This method sets the value of the database column api.general_counter
	 * @param generalCounter  the value for api.general_counter
	 * 
	 */
	public void setGeneralCounter(Integer generalCounter) {
		this.generalCounter = generalCounter;
	}

	/**
	 *  This method returns the value of the database column api.store_counter
	 * @return  the value of api.store_counter
	 * 
	 */
	public Integer getStoreCounter() {
		return storeCounter;
	}

	/**
	 *  This method sets the value of the database column api.store_counter
	 * @param storeCounter  the value for api.store_counter
	 * 
	 */
	public void setStoreCounter(Integer storeCounter) {
		this.storeCounter = storeCounter;
	}

	/**
	 *  This method returns the value of the database column api.last_refresh_time
	 * @return  the value of api.last_refresh_time
	 * 
	 */
	public Date getLastRefreshTime() {
		return lastRefreshTime;
	}

	/**
	 * This method sets the value of the database column api.last_refresh_time
	 * @param lastRefreshTime  the value for api.last_refresh_time
	 * 
	 */
	public void setLastRefreshTime(Date lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	@Override
	public String toString() {
		return "Api [id=" + id + ", userId=" + userId + ", apiKey=" + apiKey
				+ ", oauthSecret=" + oauthSecret + ", thresholdGeneralCount="
				+ thresholdGeneralCount + ", thresholdStoreCount="
				+ thresholdStoreCount + ", thresholdRefreshCount="
				+ thresholdRefreshCount + ", generalCounter=" + generalCounter
				+ ", storeCounter=" + storeCounter + ", lastRefreshTime="
				+ lastRefreshTime + "]";
	}
	
	
}