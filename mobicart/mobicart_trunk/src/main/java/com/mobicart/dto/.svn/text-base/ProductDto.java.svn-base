package com.mobicart.dto;

import com.mobicart.util.CountryUtil;
import com.mobicart.util.FileUtils;

/**
 * product dto
 * @author jasdeep.singh
 *
 */
public class ProductDto {

	private long id;
	private String name;
	private double price;
	private double discount;
	private double discountedPrice;
	private String image;
	private String imageMedium;
	private String imageSmall;
	private Double rating;
	private String status;
	private String currency;/**Note The setter of this property have custom implementation **/ 	
	private Boolean bTaxable;
 
	
	 
	
 
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
	
		String cureencycode[]=currency.split("-");
		String currencySymbol="-1";
		if(cureencycode.length>1)
		  currencySymbol=CountryUtil.getCurrencySymbol(cureencycode[1]);
		else
	      currencySymbol=CountryUtil.getCurrencySymbol(currency);
		 
		this.currency = currencySymbol;
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * This method returns the value of medium sized copy of Product Image 
	 * @return  the value of product_images.s_location appended with _medium
	 */
	public String getImageMedium() {
		try{
		if(image!=null)
			imageMedium=FileUtils.stuffedFilename(image, "_medium");
		}catch(Exception e){
			imageMedium=null;
		}
		return imageMedium;
	}

	/**
	 * This method returns the value of small sized copy of Product Image 
	 * @return  the value of product_images.s_location appended with _small
	 */
	public String getImageSmall() {
		try{
			if(image!=null)	
				imageSmall=FileUtils.stuffedFilename(image, "_small");
		}catch(Exception e){
			imageSmall=null;	
		}
		return imageSmall;
	}
	
	public String getIphonImage() {
		try{
			if(image!=null)	
				imageSmall=FileUtils.stuffedFilename(image, "_small");
		}catch(Exception e){
			imageSmall=null;	
		}
		return imageSmall;
	}
	
	public String getIpadImage() {
		try{
			if(image!=null)	
				imageSmall=FileUtils.stuffedFilename(image, "_small");
		}catch(Exception e){
			imageSmall=null;	
		}
		return imageSmall;
	}
	
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getDiscountedPrice() {
		discountedPrice=price-(price*discount/100);
		return discountedPrice;
	}
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	 
	public Boolean getbTaxable() {
		return bTaxable;
	}
	public void setbTaxable(Boolean bTaxable) {
		this.bTaxable = bTaxable;
	}
	
	
	
	
	
}
