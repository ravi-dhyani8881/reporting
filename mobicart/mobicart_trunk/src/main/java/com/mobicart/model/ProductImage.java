package com.mobicart.model;

import java.io.Serializable;

import com.mobicart.util.Constants;
import com.mobicart.util.FileUtils;

@SuppressWarnings("serial")
public class ProductImage  implements Serializable{

	
	/**
	 * This field corresponds to the database column product_images.id
	 */
	private Long id;
	/**
	 * This field corresponds to the database column product_images.product_id
	 */
	private Long productId;
	/**
	 * This field corresponds to the database column product_images.s_title
	 */
	private String sTitle;
	
	/**
	 * This field corresponds to the database column product_images.s_location
	 */
	private String sLocation;

	/**
	 * This field corresponds to medium sized ( 130 x 150) copy of stored product image 
	 */
	private String sLocationMedium;
	
	/**
	 * This field corresponds to small sized copy ( 48 x 48) of stored product image 
	 */
	private String sLocationSmall;
	
	private String productImageSmallAndroid3;	
	private String productImageSmallAndroid4;	
	private String productImageSmallAndroid6;	
	private String productImageSmallIpad;	
	private String productImageSmallIphone;
	private String productImageSmallIphone4;


	private String 	productImageMediumAndroid3;	
	private String productImageMediumAndroid4;	
	private String productImageMediumAndroid6;	
	private String productImageMediumIpad;	
	private String productImageMediumIphone;
	private String productImageMediumIphone4;


	private String 	productImageCoverFlowAndroid3;	
	private String productImageCoverFlowAndroid4;	
	private String productImageCoverFlowAndroid6;	
	private String productImageCoverFlowIpad;	
	private String productImageCoverFlowIphone;
	private String productImageCoverFlowIphone4;

	//ProductImageDetail
	private String  productImageDetail;
	
	

	public void setProductImageDetail(String productImageDetail) {
		this.productImageDetail = productImageDetail;
	}

	public void setsLocationMedium(String sLocationMedium) {
		this.sLocationMedium = sLocationMedium;
	}

	public void setsLocationSmall(String sLocationSmall) {
		this.sLocationSmall = sLocationSmall;
	}

	public void setProductImageSmallAndroid3(String productImageSmallAndroid3) {
		this.productImageSmallAndroid3 = productImageSmallAndroid3;
	}

	public void setProductImageSmallAndroid4(String productImageSmallAndroid4) {
		this.productImageSmallAndroid4 = productImageSmallAndroid4;
	}

	public void setProductImageSmallAndroid6(String productImageSmallAndroid6) {
		this.productImageSmallAndroid6 = productImageSmallAndroid6;
	}

	public void setProductImageSmallIpad(String productImageSmallIpad) {
		this.productImageSmallIpad = productImageSmallIpad;
	}

	public void setProductImageSmallIphone(String productImageSmallIphone) {
		this.productImageSmallIphone = productImageSmallIphone;
	}

	public void setProductImageMediumAndroid3(String productImageMediumAndroid3) {
		this.productImageMediumAndroid3 = productImageMediumAndroid3;
	}

	public void setProductImageMediumAndroid4(String productImageMediumAndroid4) {
		this.productImageMediumAndroid4 = productImageMediumAndroid4;
	}

	public void setProductImageMediumAndroid6(String productImageMediumAndroid6) {
		this.productImageMediumAndroid6 = productImageMediumAndroid6;
	}

	public void setProductImageMediumIpad(String productImageMediumIpad) {
		this.productImageMediumIpad = productImageMediumIpad;
	}

	public void setProductImageMediumIphone(String productImageMediumIphone) {
		this.productImageMediumIphone = productImageMediumIphone;
	}

	public void setProductImageCoverFlowAndroid3(
			String productImageCoverFlowAndroid3) {
		this.productImageCoverFlowAndroid3 = productImageCoverFlowAndroid3;
	}

	public void setProductImageCoverFlowAndroid4(
			String productImageCoverFlowAndroid4) {
		this.productImageCoverFlowAndroid4 = productImageCoverFlowAndroid4;
	}

	public void setProductImageCoverFlowAndroid6(
			String productImageCoverFlowAndroid6) {
		this.productImageCoverFlowAndroid6 = productImageCoverFlowAndroid6;
	}

	public void setProductImageCoverFlowIpad(String productImageCoverFlowIpad) {
		this.productImageCoverFlowIpad = productImageCoverFlowIpad;
	}

	public void setProductImageCoverFlowIphone(String productImageCoverFlowIphone) {
		this.productImageCoverFlowIphone = productImageCoverFlowIphone;
	}
	
	

	public void setProductImageSmallIphone4(String productImageSmallIphone4) {
		this.productImageSmallIphone4 = productImageSmallIphone4;
	}

	public void setProductImageMediumIphone4(String productImageMediumIphone4) {
		this.productImageMediumIphone4 = productImageMediumIphone4;
	}

	public void setProductImageCoverFlowIphone4(String productImageCoverFlowIphone4) {
		this.productImageCoverFlowIphone4 = productImageCoverFlowIphone4;
	}

	public ProductImage(Long id) {
		super();
		this.id = id;
	}

	public ProductImage() {
		super();
	}

	/**
	 * This method returns the value of the database column product_images.id
	 * @return  the value of product_images.id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method sets the value of the database column product_images.id
	 * @param id  the value for product_images.id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method returns the value of the database column product_images.product_id
	 * @return  the value of product_images.product_id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * This method sets the value of the database column product_images.product_id
	 * @param productId  the value for product_images.product_id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * This method returns the value of the database column product_images.s_title
	 * @return  the value of product_images.s_title
	 */
	public String getsTitle() {
		return sTitle;
	}

	/**
	 * This method sets the value of the database column product_images.s_title
	 * @param sTitle  the value for product_images.s_title
	 */
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle == null ? null : sTitle.trim();
	}

	/**
	 * This method returns the value of the database column product_images.s_location
	 * @return  the value of product_images.s_location
	 */
	public String getsLocation() {
		return sLocation;
	}

	/**
	 * This method sets the value of the database column product_images.s_location
	 * @param sLocation  the value for product_images.s_location
	 */
	public void setsLocation(String sLocation) {
		this.sLocation = sLocation == null ? null : sLocation.trim();
	}
	
	
	/**
	 * This method returns the value of medium sized copy of Product Image 
	 * @return  the value of product_images.s_location appended with _medium
	 */
	public String getsLocationMedium() {
		try{
		if(sLocation!=null)
			sLocationMedium=FileUtils.stuffedFilename(sLocation, "_medium");
		}catch(Exception e){
			sLocationMedium=null;
		}
		return sLocationMedium;
	}

	/**
	 * This method returns the value of small sized copy of Product Image 
	 * @return  the value of product_images.s_location appended with _small
	 */
	public String getsLocationSmall() {
		try{
			if(sLocation!=null)	
				sLocationSmall=FileUtils.stuffedFilename(sLocation, "_small");
		}catch(Exception e){
			sLocationSmall=null;	
		}
		return sLocationSmall;
	}


	
	
	
	public String getProductImageSmallAndroid3() {
		if(sLocation!=null)productImageSmallAndroid3=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY);
		return productImageSmallAndroid3;
	}

	public String getProductImageSmallAndroid4() {
		if(sLocation!=null)productImageSmallAndroid4=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY);
		return productImageSmallAndroid4;
	}

	public String getProductImageSmallAndroid6() {
		if(sLocation!=null)productImageSmallAndroid6=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY);
		return productImageSmallAndroid6;
	}

	public String getProductImageSmallIpad() {
		if(sLocation!=null)productImageSmallIpad=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY);
		return productImageSmallIpad;
	}

	public String getProductImageSmallIphone() {
		if(sLocation!=null)productImageSmallIphone=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY);
		return productImageSmallIphone;
	}

	public String getProductImageMediumAndroid3() {
		if(sLocation!=null)productImageMediumAndroid3=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY);
		return productImageMediumAndroid3;
	}

	public String getProductImageMediumAndroid4() {
		if(sLocation!=null)productImageMediumAndroid4=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY);
		return productImageMediumAndroid4;
	}

	public String getProductImageMediumAndroid6() {
		if(sLocation!=null)productImageMediumAndroid6=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY);
		return productImageMediumAndroid6;
	}

	public String getProductImageMediumIpad() {
		if(sLocation!=null)productImageMediumIpad=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY);
		return productImageMediumIpad;
	}

	public String getProductImageMediumIphone() {
		if(sLocation!=null)productImageMediumIphone=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY);
		return productImageMediumIphone;
	}

	public String getProductImageCoverFlowAndroid3() {
		if(sLocation!=null)productImageCoverFlowAndroid3=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY);
		return productImageCoverFlowAndroid3;
	}

	public String getProductImageCoverFlowAndroid4() {
		if(sLocation!=null)productImageCoverFlowAndroid4=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY);
		return productImageCoverFlowAndroid4;
	}

	public String getProductImageCoverFlowAndroid6() {
		if(sLocation!=null)productImageCoverFlowAndroid6=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY);
		return productImageCoverFlowAndroid6;
	}

	public String getProductImageCoverFlowIpad() {
		if(sLocation!=null)productImageCoverFlowIpad=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY);
		return productImageCoverFlowIpad;
	}

	public String getProductImageCoverFlowIphone() {
		if(sLocation!=null)productImageCoverFlowIphone=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY);
		return productImageCoverFlowIphone;
	}

	
	





public String getProductImageSmallIphone4() {
		if(sLocation!=null)productImageSmallIphone4=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_SMALL_IPHONE4_KEY);
		return productImageSmallIphone4;
	}

	public String getProductImageMediumIphone4() {
		if(sLocation!=null)productImageMediumIphone4=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_MEDIUM_IPHONE4_KEY);
		return productImageMediumIphone4;
	}

	public String getProductImageCoverFlowIphone4() {
		if(sLocation!=null)productImageCoverFlowIphone4=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE4_KEY);
		return productImageCoverFlowIphone4;
	}
	public String getProductImageDetail() {
		if(sLocation!=null)productImageDetail=FileUtils.stuffedFilename(sLocation, "_"+Constants.PRODUCT_IMAGE_DETAIL);
 
		return productImageDetail;
		
	}

	@Override
	public String toString() {
		return "ProductImage [id=" + id + ", productId=" + productId
				+ ", sTitle=" + sTitle + ", sLocation=" + sLocation + "]";
	}
	
	
}