package com.mobicart.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author vivek.sidher
 *
 */
@SuppressWarnings("serial")
public class ProductOption implements Serializable {
	
	private Long id;
	private Long productId;
	private String sSaleLabel;
	private String sTitle;
	private String sName;
	private BigDecimal fQuantity;
	private Integer iAvailableQuantity;
	private Integer iToBeShippedQuantity;
	private Double pPrice;
	
	/**
	 * This method returns the value of the database column product_options.id
	 * @return  the value of product_options.id
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method sets the value of the database column product_options.id
	 * @param id  the value for product_options.id
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	/**
	 * This method returns the value of the database column product_options.product_id
	 * @return  the value of product_options.product_id
	 * 
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * This method sets the value of the database column product_options.product_id
	 * @param productId  the value for product_options.product_id
	 * 
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getsSaleLabel() {
		return sSaleLabel;
	}

	public void setsSaleLabel(String sSaleLabel) {
		this.sSaleLabel = sSaleLabel;
	}

	/**
	 * This method returns the value of the database column product_options.s_name
	 * @return  the value of product_options.s_name
	 * 
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * This method sets the value of the database column product_options.s_name
	 * @param sName  the value for product_options.s_name
	 * 
	 */
	public void setsName(String sName) {
		this.sName = sName == null ? null : sName.trim();
	}

	

	

	public Double getpPrice() {
		return pPrice;
	}

	public void setpPrice(Double pPrice) {
		this.pPrice = pPrice;
	}

	/**
	 * This method returns the value of the database column product_options.f_quantity
	 * @return  the value of product_options.f_quantity
	 * 
	 */
	public BigDecimal getfQuantity() {
		return fQuantity;
	}

	/**
	 * This method sets the value of the database column product_options.f_quantity
	 * @param fQuantity  the value for product_options.f_quantity
	 * 
	 */
	public void setfQuantity(BigDecimal fQuantity) {
		this.fQuantity = fQuantity;
	}

	/**
	 * This method returns the value of the database column product_options.i_available_quantity
	 * @return  the value of product_options.i_available_quantity
	 * 
	 */
	public Integer getiAvailableQuantity() {
		return iAvailableQuantity!=null?iAvailableQuantity:-1;
	}

	/**
	 * This method sets the value of the database column product_options.i_available_quantity
	 * @param iAvailableQuantity  the value for product_options.i_available_quantity
	 * 
	 */
	public void setiAvailableQuantity(Integer iAvailableQuantity) {
		this.iAvailableQuantity = iAvailableQuantity;
	}

	/**
	 * This method returns the value of the database column product_options.i_to_be_shipped_quantity
	 * @return  the value of product_options.i_to_be_shipped_quantity
	 * 
	 */
	public Integer getiToBeShippedQuantity() {
		return iToBeShippedQuantity;
	}

	/**
	 * This method sets the value of the database column product_options.i_to_be_shipped_quantity
	 * @param iToBeShippedQuantity  the value for product_options.i_to_be_shipped_quantity
	 * 
	 */
	public void setiToBeShippedQuantity(Integer iToBeShippedQuantity) {
		this.iToBeShippedQuantity = iToBeShippedQuantity;
	}

	public ProductOption(Long id) {
		super();
		this.id = id;
	}

	public ProductOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductOption [id=" + id + ", productId=" + productId + ", sTitle=" + sTitle 
				+ ", sName=" + sName + ", fQuantity=" + fQuantity +  ", pPrice=" + pPrice + "]";
	}

	
	
	
}