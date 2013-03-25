package com.mobicart.model;

public class AppStoreUser {

	private Long appId;  
	private Long storeId;  
	private Long userId;
	private String plan;
	
	
	public String getPlan() {
		return plan;
	}


	public void setPlan(String plan) {
		this.plan = plan;
	}


	public AppStoreUser() {
		super();
	}


	public AppStoreUser(Long appId, Long storeId, Long userId,String plan) {
		super();
		this.appId = appId;
		this.storeId = storeId;
		this.userId = userId;
		this.plan = plan;
	}


	public Long getAppId() {
		return appId;
	}


	public void setAppId(Long appId) {
		this.appId = appId;
	}


	public Long getStoreId() {
		return storeId;
	}


	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
