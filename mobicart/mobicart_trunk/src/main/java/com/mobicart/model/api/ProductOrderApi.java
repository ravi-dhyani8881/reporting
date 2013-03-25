package com.mobicart.model.api;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mobicart.web.external.OrderApiWebServiceController;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@JsonWriteNullProperties(false)
public class ProductOrderApi {
	
	
	private long orderId;
	@XStreamOmitField
	private long merchantId;
	private long storeId;
	private String merchantPayPalEmail;
	private BigDecimal orderAmount;
	private BigDecimal orderShippingAmount;
	private BigDecimal orderTaxAmount;
	private BigDecimal orderGrandTotalAmount;
	private String buyerName;
	private String buyerEmail;
	private Long buyerPhone;
	private String shippingStreet;
	private String shippingCity;
	private String shippingState;
	private String shippingPostalCode;
	private String shippingCountry;
	private String billingStreet;
	private String billingCity;
	private String billingState;
	private String billingPostalCode;
	private String billingCountry;
	private String orderStatus;
	@XStreamOmitField
	private Date date;
	private String orderDate;
	
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ProductOrderApi.class);

	private List<ProductOrderItemApi> productOrderItems;


	public long getOrderId() {
		return orderId;
	}

	public long getStoreId() {
		return storeId;
	}

	@JsonIgnore
	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public void setBuyerPhone(Long buyerPhone) {
		this.buyerPhone = buyerPhone;
	} 

	
	public Long getBuyerPhone() {
		return buyerPhone;
	}

	public String getMerchantPayPalEmail() {
		return merchantPayPalEmail;
	}

	public void setMerchantPayPalEmail(String merchantPayPalEmail) {
		this.merchantPayPalEmail = merchantPayPalEmail;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getOrderShippingAmount() {
		return orderShippingAmount;
	}

	public void setOrderShippingAmount(BigDecimal orderShippingAmount) {
		this.orderShippingAmount = orderShippingAmount;
	}

	public BigDecimal getOrderTaxAmount() {
		return orderTaxAmount;
	}

	public void setOrderTaxAmount(BigDecimal orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}

	public BigDecimal getOrderGrandTotalAmount() {
		return orderGrandTotalAmount;
	}

	public void setOrderGrandTotalAmount(BigDecimal orderGrandTotalAmount) {
		this.orderGrandTotalAmount = orderGrandTotalAmount;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	

	public String getShippingStreet() {
		return shippingStreet;
	}

	public void setShippingStreet(String shippingStreet) {
		this.shippingStreet = shippingStreet;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingPostalCode() {
		return shippingPostalCode;
	}

	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getBillingStreet() {
		return billingStreet;
	}

	public void setBillingStreet(String billingStreet) {
		this.billingStreet = billingStreet;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@JsonIgnore
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ProductOrderItemApi> getProductOrderItems() {
		return productOrderItems;
	}

	public void setProductOrderItems(List<ProductOrderItemApi> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getOrderDate() {
		if (logger.isDebugEnabled()) {
			logger.debug("getFormattedOrderDate() - start"); //$NON-NLS-1$
		}

		Format formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		try{
			orderDate = formatter.format(date); // Tue, 09 Jan 2002 22:14:02 -0500
		}catch(Exception e){
			logger.warn("getFormattedOrderDate() - exception ignored", e); //$NON-NLS-1$
			
		}

		return orderDate;
	}
	
	
	
}