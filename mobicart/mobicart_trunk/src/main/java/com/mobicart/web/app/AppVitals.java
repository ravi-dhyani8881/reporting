package com.mobicart.web.app;

import java.io.Serializable;

public class AppVitals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 495299465058041779L;
	
	private float price;
	private String[] countries;
	private String description;
	private String copyright;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String[] getCountries() {
		return countries;
	}
	public void setCountries(String[] countries) {
		this.countries = countries;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	
	
	
	
}
