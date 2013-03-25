package com.mobicart.util;

import java.math.BigDecimal;
import java.util.Date;

import com.mobicart.model.App;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.User;



public class PaypalResponse {
	
	private User user;
	
	private String paymentStatus; 
	private String payerId; 
	private String custom; 
	private String payerStatus; 
	private BigDecimal paymentGross; 
	private Date paymentDate; 
	private String txnId; 
	private String receiptId; 
	private BigDecimal paymentFee; 
	private String pendingReason;
	private String remarks;
	private String serviceType;
	private Long appId;
	private BigDecimal transactionAmount;
	private Long storeId;
	private DiskSpacePricing diskSpacePricing;
	private SiteConstant siteConstant;
	private App app;
	
	
	
	public PaypalResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaypalResponse(String paymentStatus, String payerId, String custom,
			String payerStatus, BigDecimal paymentGross, Date paymentDate,
			String txnId, String receiptId, BigDecimal paymentFee,
			String pendingReason) {
		super();
		this.paymentStatus = paymentStatus;
		this.payerId = payerId;
		this.custom = custom;
		this.payerStatus = payerStatus;
		this.paymentGross = paymentGross;
		this.paymentDate = paymentDate;
		this.txnId = txnId;
		this.receiptId = receiptId;
		this.paymentFee = paymentFee;
		this.pendingReason = pendingReason;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getPayerStatus() {
		return payerStatus;
	}
	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}
	public BigDecimal getPaymentGross() {
		return paymentGross;
	}
	public void setPaymentGross(BigDecimal paymentGross) {
		this.paymentGross = paymentGross;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public BigDecimal getPaymentFee() {
		return paymentFee;
	}
	public void setPaymentFee(BigDecimal paymentFee) {
		this.paymentFee = paymentFee;
	}
	public String getPendingReason() {
		return pendingReason;
	}
	public void setPendingReason(String pendingReason) {
		this.pendingReason = pendingReason;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public DiskSpacePricing getDiskSpacePricing() {
		return diskSpacePricing;
	}
	public void setDiskSpacePricing(DiskSpacePricing diskSpacePricing) {
		this.diskSpacePricing = diskSpacePricing;
	}
	public SiteConstant getSiteConstant() {
		return siteConstant;
	}
	public void setSiteConstant(SiteConstant siteConstant) {
		this.siteConstant = siteConstant;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	} 

}
