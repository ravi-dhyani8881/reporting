package com.mobicart.model;

import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSetter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("ProductOrderShippingDetails")
public class ProductOrderShippingDetail {

	@XStreamOmitField
	private Long id;

	@XStreamAlias("ProductOrderId")
	private Long productOrderId;
	
	
	
	@XStreamAlias("TrackingNumber")
	private String sTrackingNumber;
	
	@XStreamAlias("ShippingCarrier")
	private String sShippingCarrier;
	
	@XStreamAlias("ShippingStatus")
	private String sShippingStatus;
	
	@XStreamAlias("ShippingRemarks")
	private String sShippingRemarks;

	
	
	
	
	
	
	public ProductOrderShippingDetail() {
		super();
	}

	public ProductOrderShippingDetail(Long id) {
		super();
		this.id = id;
		
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	@JsonGetter("ProductOrderId")	
	public Long getProductOrderId() {
		return productOrderId;
	}

	
	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column product_order_shipping_details.s_tracking_number
	 * @return  the value of product_order_shipping_details.s_tracking_number
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	@JsonGetter("TrackingNumber")
	public String getsTrackingNumber() {
		return sTrackingNumber;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column product_order_shipping_details.s_tracking_number
	 * @param sTrackingNumber  the value for product_order_shipping_details.s_tracking_number
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	public void setsTrackingNumber(String sTrackingNumber) {
		this.sTrackingNumber = sTrackingNumber == null ? null : sTrackingNumber
				.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column product_order_shipping_details.s_shipping_carrier
	 * @return  the value of product_order_shipping_details.s_shipping_carrier
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	@JsonGetter("ShippingCarrier")
	public String getsShippingCarrier() {
		return sShippingCarrier;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column product_order_shipping_details.s_shipping_carrier
	 * @param sShippingCarrier  the value for product_order_shipping_details.s_shipping_carrier
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	public void setsShippingCarrier(String sShippingCarrier) {
		this.sShippingCarrier = sShippingCarrier == null ? null
				: sShippingCarrier.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column product_order_shipping_details.s_shipping_status
	 * @return  the value of product_order_shipping_details.s_shipping_status
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	@JsonGetter("ShippingStatus")
	public String getsShippingStatus() {
		return sShippingStatus;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column product_order_shipping_details.s_shipping_status
	 * @param sShippingStatus  the value for product_order_shipping_details.s_shipping_status
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	public void setsShippingStatus(String sShippingStatus) {
		this.sShippingStatus = sShippingStatus == null ? null : sShippingStatus
				.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column product_order_shipping_details.s_shipping_remarks
	 * @return  the value of product_order_shipping_details.s_shipping_remarks
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	@JsonGetter("ShippingRemarks")
	public String getsShippingRemarks() {
		return sShippingRemarks;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column product_order_shipping_details.s_shipping_remarks
	 * @param sShippingRemarks  the value for product_order_shipping_details.s_shipping_remarks
	 * @ibatorgenerated  Wed May 18 13:09:33 GMT+05:30 2011
	 */
	public void setsShippingRemarks(String sShippingRemarks) {
		this.sShippingRemarks = sShippingRemarks == null ? null
				: sShippingRemarks.trim();
	}
}