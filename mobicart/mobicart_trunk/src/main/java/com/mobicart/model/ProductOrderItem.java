package com.mobicart.model;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class ProductOrderItem {
	/**
	 * id genereted by database while insertion
	 */
	private Long id;
	/**
	 * {@link ProductOrder} id
	 */
	private Long orderId;
	/**
	 * {@link Product} id
	 */
	private Long productId;
	/**
	 *  {@link ProductOption} id
	 */
	private Long productOptionId;
	
	/**
	 * Quantity of products bought
	 */
	private Integer iQuantity;
	
	/**
	 * price dedcuted for each product
	 */
	private BigDecimal fAmount;
	
	/**
	 * s_order_source unique key for the order 
	 */
	private BigDecimal s_order_source;
	
	public BigDecimal getS_order_source() {
		return s_order_source;
	}

	public void setS_order_source(BigDecimal s_order_source) {
		this.s_order_source = s_order_source;
	}

	/**
	 * Product name
	 */
	private String productName;
	
	/**
	 * prodcut prcie after discount
	 */
	private BigDecimal productPriceAfterDiscount;
	// we get name and title as it the one required
	
	/**
	 * {@link ProductOption}
	 */
	private ProductOption productOption;


	private String optionDescription=""; 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	
	public Long getProductOptionId() {
		return productOptionId;
	}

	
	public void setProductOptionId(Long productOptionId) {
		this.productOptionId = productOptionId;
	}

	public Integer getiQuantity() {
		return iQuantity;
	}

	
	public void setiQuantity(Integer iQuantity) {
		this.iQuantity = iQuantity;
	}

	
	public BigDecimal getfAmount() {
		return fAmount;
	}

	
	public void setfAmount(BigDecimal fAmount) {
		this.fAmount = fAmount;
	}

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	public ProductOption getProductOption() {
		return productOption;
	}

	public void setProductOption(ProductOption productOption) {
		this.productOption = productOption;
	}


	
	
	public BigDecimal getProductPriceAfterDiscount() {
		return productPriceAfterDiscount;
	}

	public void setProductPriceAfterDiscount(BigDecimal productPriceAfterDiscount) {
		this.productPriceAfterDiscount = productPriceAfterDiscount;
	}

	
	
	
	public String getOptionDescription() {
		if(productOption!=null && StringUtils.isNotEmpty(productOption.getsName()) ){
			optionDescription= " ( "+ productOption.getsName()+" : "+productOption.getsTitle() +" ) ";
		}
		return optionDescription;
	}

	public void setOptionDescription(String optionDescription) {
		this.optionDescription = optionDescription;
	}

	@Override
	public String toString() {
		return "ProductOrderItem [id=" + id + ", orderId=" + orderId
				+ ", productId=" + productId + ", productOptionId="
				+ productOptionId + ", iQuantity=" + iQuantity + ", fAmount="
				+ fAmount + ", productName=" + productName
				+ ", productPriceAfterDiscount=" + productPriceAfterDiscount
				+ ", productOption=" + productOption + ", optionDescription="
				+ optionDescription + "]";
	}

	
	
	
}