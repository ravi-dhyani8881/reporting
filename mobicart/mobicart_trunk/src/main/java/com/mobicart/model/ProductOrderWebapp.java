package com.mobicart.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductOrderWebapp {
    
	/**
     * This field corresponds to the database column product_orders_webapp.id
     */
    private Long id;

    /**
     * This field corresponds to the database column product_orders_webapp.merchant_id
     */
    private Long merchantId;

    /**
     * This field corresponds to the database column product_orders_webapp.store_id
     */
    private Long storeId;

    /**
     * This field corresponds to the database column product_orders_webapp.app_id
     */
    private Long appId;

    /**
     * This field corresponds to the database column product_orders_webapp.s_merchant_paypal_email
     */
    private String sMerchantPaypalEmail;

    /**
     * This field corresponds to the database column product_orders_webapp.f_amount
     */
    private BigDecimal fAmount;

    /**
     * This field corresponds to the database column product_orders_webapp.f_shipping_amount
     */
    
    private BigDecimal fShippingAmount;

    /**
     * This field corresponds to the database column product_orders_webapp.f_tax_amount
     */
    private BigDecimal fTaxAmount;

    /**
     * This field corresponds to the database column product_orders_webapp.f_total_amount
     */
    private BigDecimal fTotalAmount;

    /**
     * This field corresponds to the database column product_orders_webapp.s_buyer_name
     */
    private String sBuyerName;

    /**
     * This field corresponds to the database column product_orders_webapp.s_buyer_email
     */
    private String sBuyerEmail;

    /**
     * This field corresponds to the database column product_orders_webapp.i_buyer_phone
     */
    private Long iBuyerPhone;

    /**
     * This field corresponds to the database column product_orders_webapp.s_shipping_street
     */
    private String sShippingStreet;

    /**
     * This field corresponds to the database column product_orders_webapp.s_shipping_city
     */
    private String sShippingCity;

    /**
     * This field corresponds to the database column product_orders_webapp.s_shipping_state
     */
    private String sShippingState;

    /**
     * This field corresponds to the database column product_orders_webapp.s_shipping_postal_code
     */
    private String sShippingPostalCode;

    /**
     * This field corresponds to the database column product_orders_webapp.s_shipping_country
     */
    private String sShippingCountry;

    /**
     * This field corresponds to the database column product_orders_webapp.s_billing_street
     */
    private String sBillingStreet;

    /**
     * This field corresponds to the database column product_orders_webapp.s_billing_city
     */
    private String sBillingCity;

    /**
     * This field corresponds to the database column product_orders_webapp.s_billing_state
     */
    private String sBillingState;

    /**
     * This field corresponds to the database column product_orders_webapp.s_billing_postal_code
     */
    private String sBillingPostalCode;

    /**
     * This field corresponds to the database column product_orders_webapp.s_billing_country
     */
    private String sBillingCountry;

    /**
     * This field corresponds to the database column product_orders_webapp.s_status
     */
    private String sStatus;

    /**
     * This field corresponds to the database column product_orders_webapp.d_order_date
     */
    private Date dOrderDate;

    /**
     * This field corresponds to the database column product_orders_webapp.s_order_source
     */
    private String sOrderSource;

    /**
     * This method returns the value of the database column product_orders_webapp.id
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets the value of the database column product_orders_webapp.id
     * @param id the value for product_orders_webapp.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column product_orders_webapp.merchant_id
     *
     * @return the value of product_orders_webapp.merchant_id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method sets the value of the database column product_orders_webapp.merchant_id
     * @param merchantId the value for product_orders_webapp.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method returns the value of the database column product_orders_webapp.store_id
     * @return the value of product_orders_webapp.store_id
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * This method sets the value of the database column product_orders_webapp.store_id
     * @param storeId the value for product_orders_webapp.store_id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * This method returns the value of the database column product_orders_webapp.app_id
     * @return the value of product_orders_webapp.app_id
     */
    public Long getAppId() {
        return appId;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.app_id
     *
     * @param appId the value for product_orders_webapp.app_id
     *
     * 
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_merchant_paypal_email
     *
     * @return the value of product_orders_webapp.s_merchant_paypal_email
     *
     * 
     */
    public String getsMerchantPaypalEmail() {
        return sMerchantPaypalEmail;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_merchant_paypal_email
     *
     * @param sMerchantPaypalEmail the value for product_orders_webapp.s_merchant_paypal_email
     *
     * 
     */
    public void setsMerchantPaypalEmail(String sMerchantPaypalEmail) {
        this.sMerchantPaypalEmail = sMerchantPaypalEmail == null ? null : sMerchantPaypalEmail.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.f_amount
     *
     * @return the value of product_orders_webapp.f_amount
     *
     * 
     */
    public BigDecimal getfAmount() {
        return fAmount;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.f_amount
     *
     * @param fAmount the value for product_orders_webapp.f_amount
     *
     * 
     */
    public void setfAmount(BigDecimal fAmount) {
        this.fAmount = fAmount;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.f_shipping_amount
     *
     * @return the value of product_orders_webapp.f_shipping_amount
     *
     * 
     */
    public BigDecimal getfShippingAmount() {
        return fShippingAmount;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.f_shipping_amount
     *
     * @param fShippingAmount the value for product_orders_webapp.f_shipping_amount
     *
     * 
     */
    public void setfShippingAmount(BigDecimal fShippingAmount) {
        this.fShippingAmount = fShippingAmount;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.f_tax_amount
     *
     * @return the value of product_orders_webapp.f_tax_amount
     *
     * 
     */
    public BigDecimal getfTaxAmount() {
        return fTaxAmount;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.f_tax_amount
     *
     * @param fTaxAmount the value for product_orders_webapp.f_tax_amount
     *
     * 
     */
    public void setfTaxAmount(BigDecimal fTaxAmount) {
        this.fTaxAmount = fTaxAmount;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.f_total_amount
     *
     * @return the value of product_orders_webapp.f_total_amount
     *
     * 
     */
    public BigDecimal getfTotalAmount() {
        return fTotalAmount;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.f_total_amount
     *
     * @param fTotalAmount the value for product_orders_webapp.f_total_amount
     *
     * 
     */
    public void setfTotalAmount(BigDecimal fTotalAmount) {
        this.fTotalAmount = fTotalAmount;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_buyer_name
     *
     * @return the value of product_orders_webapp.s_buyer_name
     *
     * 
     */
    public String getsBuyerName() {
        return sBuyerName;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_buyer_name
     *
     * @param sBuyerName the value for product_orders_webapp.s_buyer_name
     *
     * 
     */
    public void setsBuyerName(String sBuyerName) {
        this.sBuyerName = sBuyerName == null ? null : sBuyerName.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_buyer_email
     *
     * @return the value of product_orders_webapp.s_buyer_email
     *
     * 
     */
    public String getsBuyerEmail() {
        return sBuyerEmail;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_buyer_email
     *
     * @param sBuyerEmail the value for product_orders_webapp.s_buyer_email
     *
     * 
     */
    public void setsBuyerEmail(String sBuyerEmail) {
        this.sBuyerEmail = sBuyerEmail == null ? null : sBuyerEmail.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.i_buyer_phone
     *
     * @return the value of product_orders_webapp.i_buyer_phone
     *
     * 
     */
    public Long getiBuyerPhone() {
        return iBuyerPhone;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.i_buyer_phone
     *
     * @param iBuyerPhone the value for product_orders_webapp.i_buyer_phone
     *
     * 
     */
    public void setiBuyerPhone(Long iBuyerPhone) {
        this.iBuyerPhone = iBuyerPhone;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_shipping_street
     *
     * @return the value of product_orders_webapp.s_shipping_street
     *
     * 
     */
    public String getsShippingStreet() {
        return sShippingStreet;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_shipping_street
     *
     * @param sShippingStreet the value for product_orders_webapp.s_shipping_street
     *
     * 
     */
    public void setsShippingStreet(String sShippingStreet) {
        this.sShippingStreet = sShippingStreet == null ? null : sShippingStreet.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_shipping_city
     *
     * @return the value of product_orders_webapp.s_shipping_city
     *
     * 
     */
    public String getsShippingCity() {
        return sShippingCity;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_shipping_city
     *
     * @param sShippingCity the value for product_orders_webapp.s_shipping_city
     *
     * 
     */
    public void setsShippingCity(String sShippingCity) {
        this.sShippingCity = sShippingCity == null ? null : sShippingCity.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_shipping_state
     *
     * @return the value of product_orders_webapp.s_shipping_state
     *
     * 
     */
    public String getsShippingState() {
        return sShippingState;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_shipping_state
     *
     * @param sShippingState the value for product_orders_webapp.s_shipping_state
     *
     * 
     */
    public void setsShippingState(String sShippingState) {
        this.sShippingState = sShippingState == null ? null : sShippingState.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_shipping_postal_code
     *
     * @return the value of product_orders_webapp.s_shipping_postal_code
     *
     * 
     */
    public String getsShippingPostalCode() {
        return sShippingPostalCode;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_shipping_postal_code
     *
     * @param sShippingPostalCode the value for product_orders_webapp.s_shipping_postal_code
     *
     * 
     */
    public void setsShippingPostalCode(String sShippingPostalCode) {
        this.sShippingPostalCode = sShippingPostalCode == null ? null : sShippingPostalCode.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_shipping_country
     *
     * @return the value of product_orders_webapp.s_shipping_country
     *
     * 
     */
    public String getsShippingCountry() {
        return sShippingCountry;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_shipping_country
     *
     * @param sShippingCountry the value for product_orders_webapp.s_shipping_country
     *
     * 
     */
    public void setsShippingCountry(String sShippingCountry) {
        this.sShippingCountry = sShippingCountry == null ? null : sShippingCountry.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_billing_street
     *
     * @return the value of product_orders_webapp.s_billing_street
     *
     * 
     */
    public String getsBillingStreet() {
        return sBillingStreet;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_billing_street
     *
     * @param sBillingStreet the value for product_orders_webapp.s_billing_street
     *
     * 
     */
    public void setsBillingStreet(String sBillingStreet) {
        this.sBillingStreet = sBillingStreet == null ? null : sBillingStreet.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_billing_city
     *
     * @return the value of product_orders_webapp.s_billing_city
     *
     * 
     */
    public String getsBillingCity() {
        return sBillingCity;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_billing_city
     *
     * @param sBillingCity the value for product_orders_webapp.s_billing_city
     *
     * 
     */
    public void setsBillingCity(String sBillingCity) {
        this.sBillingCity = sBillingCity == null ? null : sBillingCity.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_billing_state
     *
     * @return the value of product_orders_webapp.s_billing_state
     *
     * 
     */
    public String getsBillingState() {
        return sBillingState;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_billing_state
     *
     * @param sBillingState the value for product_orders_webapp.s_billing_state
     *
     * 
     */
    public void setsBillingState(String sBillingState) {
        this.sBillingState = sBillingState == null ? null : sBillingState.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_billing_postal_code
     *
     * @return the value of product_orders_webapp.s_billing_postal_code
     *
     * 
     */
    public String getsBillingPostalCode() {
        return sBillingPostalCode;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_billing_postal_code
     *
     * @param sBillingPostalCode the value for product_orders_webapp.s_billing_postal_code
     *
     * 
     */
    public void setsBillingPostalCode(String sBillingPostalCode) {
        this.sBillingPostalCode = sBillingPostalCode == null ? null : sBillingPostalCode.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_billing_country
     *
     * @return the value of product_orders_webapp.s_billing_country
     *
     * 
     */
    public String getsBillingCountry() {
        return sBillingCountry;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_billing_country
     *
     * @param sBillingCountry the value for product_orders_webapp.s_billing_country
     *
     * 
     */
    public void setsBillingCountry(String sBillingCountry) {
        this.sBillingCountry = sBillingCountry == null ? null : sBillingCountry.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_status
     *
     * @return the value of product_orders_webapp.s_status
     *
     * 
     */
    public String getsStatus() {
        return sStatus;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_status
     *
     * @param sStatus the value for product_orders_webapp.s_status
     *
     * 
     */
    public void setsStatus(String sStatus) {
        this.sStatus = sStatus == null ? null : sStatus.trim();
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.d_order_date
     *
     * @return the value of product_orders_webapp.d_order_date
     *
     * 
     */
    public Date getdOrderDate() {
        return dOrderDate;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.d_order_date
     *
     * @param dOrderDate the value for product_orders_webapp.d_order_date
     *
     * 
     */
    public void setdOrderDate(Date dOrderDate) {
        this.dOrderDate = dOrderDate;
    }

    /**
     *
     * This method returns the value of the database column product_orders_webapp.s_order_source
     *
     * @return the value of product_orders_webapp.s_order_source
     *
     * 
     */
    public String getsOrderSource() {
        return sOrderSource;
    }

    /**
     *
     * This method sets the value of the database column product_orders_webapp.s_order_source 
     *
     * @param sOrderSource the value for product_orders_webapp.s_order_source
     *
     * 
     */
    public void setsOrderSource(String sOrderSource) {
        this.sOrderSource = sOrderSource == null ? null : sOrderSource.trim();
    }
}