package com.mobicart.model;

import java.math.BigDecimal;

public class ProductOrderItemMultiple {
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
	private String productOptionId;
	
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

	
	
	
	
	public String getProductOptionId() {
		return productOptionId;
	}

	public void setProductOptionId(String productOptionId) {
		this.productOptionId = productOptionId;
	}

	@Override
	public String toString() {
		return "ProductOrderItemMultiple [id=" + id + ", orderId=" + orderId
				+ ", productId=" + productId + ", productOptionId="
				+ productOptionId + ", iQuantity=" + iQuantity + ", fAmount="
				+ fAmount + "]";
	}

	
	
	
}