package com.mobicart.model;



import java.math.BigDecimal;
import java.util.Date;

public class RecurringPaymentDetail {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private Long id;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.merchant_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private Long merchantId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.store_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private Long storeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.d_payment_date
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private Date dPaymentDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.s_service_type
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private String sServiceType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.f_amount
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private BigDecimal fAmount;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.s_remarks
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private String sRemarks;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.app_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private Long appId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.f_payment_fee
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private BigDecimal fPaymentFee;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column recurring_payment_detail.txn_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    private String txnId;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.id
     *
     * @return the value of recurring_payment_detail.id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.id
     *
     * @param id the value for recurring_payment_detail.id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.merchant_id
     *
     * @return the value of recurring_payment_detail.merchant_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.merchant_id
     *
     * @param merchantId the value for recurring_payment_detail.merchant_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.store_id
     *
     * @return the value of recurring_payment_detail.store_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.store_id
     *
     * @param storeId the value for recurring_payment_detail.store_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.d_payment_date
     *
     * @return the value of recurring_payment_detail.d_payment_date
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public Date getdPaymentDate() {
        return dPaymentDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.d_payment_date
     *
     * @param dPaymentDate the value for recurring_payment_detail.d_payment_date
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setdPaymentDate(Date dPaymentDate) {
        this.dPaymentDate = dPaymentDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.s_service_type
     *
     * @return the value of recurring_payment_detail.s_service_type
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public String getsServiceType() {
        return sServiceType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.s_service_type
     *
     * @param sServiceType the value for recurring_payment_detail.s_service_type
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setsServiceType(String sServiceType) {
        this.sServiceType = sServiceType == null ? null : sServiceType.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.f_amount
     *
     * @return the value of recurring_payment_detail.f_amount
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public BigDecimal getfAmount() {
        return fAmount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.f_amount
     *
     * @param fAmount the value for recurring_payment_detail.f_amount
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setfAmount(BigDecimal fAmount) {
        this.fAmount = fAmount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.s_remarks
     *
     * @return the value of recurring_payment_detail.s_remarks
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public String getsRemarks() {
        return sRemarks;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.s_remarks
     *
     * @param sRemarks the value for recurring_payment_detail.s_remarks
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setsRemarks(String sRemarks) {
        this.sRemarks = sRemarks == null ? null : sRemarks.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.app_id
     *
     * @return the value of recurring_payment_detail.app_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.app_id
     *
     * @param appId the value for recurring_payment_detail.app_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.f_payment_fee
     *
     * @return the value of recurring_payment_detail.f_payment_fee
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public BigDecimal getfPaymentFee() {
        return fPaymentFee;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.f_payment_fee
     *
     * @param fPaymentFee the value for recurring_payment_detail.f_payment_fee
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setfPaymentFee(BigDecimal fPaymentFee) {
        this.fPaymentFee = fPaymentFee;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column recurring_payment_detail.txn_id
     *
     * @return the value of recurring_payment_detail.txn_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public String getTxnId() {
        return txnId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column recurring_payment_detail.txn_id
     *
     * @param txnId the value for recurring_payment_detail.txn_id
     *
     * @ibatorgenerated Mon Apr 18 20:28:46 IST 2011
     */
    public void setTxnId(String txnId) {
        this.txnId = txnId == null ? null : txnId.trim();
    }
}