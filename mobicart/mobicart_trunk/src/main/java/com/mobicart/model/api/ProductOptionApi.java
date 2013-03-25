package com.mobicart.model.api;


public class ProductOptionApi {

	
	private Long optionId;
	private String optionName;
	private String optionTitle;
	private String optionSKU;
	private Double optionPrice;
	
	public Double getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(Double optionPrice) {
		this.optionPrice = optionPrice;
	}
	public String getOptionSKU() {
		return optionSKU;
	}
	public void setOptionSKU(String optionSKU) {
		this.optionSKU = optionSKU;
	}
	public String getOptionTitle() {
		return optionTitle;
	}
	public void setOptionTitle(String optionTitle) {
		this.optionTitle = optionTitle;
	}
	private Integer quantityInStock;
	
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	
}