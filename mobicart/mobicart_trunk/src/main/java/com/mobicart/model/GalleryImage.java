package com.mobicart.model;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mobicart.util.Constants;
import com.mobicart.util.FileUtils;

public class GalleryImage {

	
	private Long id;
	private String sGalleryImagePath;
	private CommonsMultipartFile fGalleryImage;
	private Long userId;
	private Long appId;
	private Long storeId;
	private String sTitle;
	private String sDescription;
	private String sThumbnail;
	private String sPreview;
	private String sOriginal;
	private Date dCreatedOn;
	private Date dUpdatedOn;

	private String galleryImageAndroid3;	
	private String galleryImageAndroid4;	
	private String galleryImageAndroid6;	
	private String galleryImageIpad;	
	private String galleryImageIphone;
	private String galleryImageIphone4;
	 
   
	
	 
	

	public GalleryImage() {
		super();
	}

	public GalleryImage(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getsTitle() {
		sTitle=FilenameUtils.getBaseName(sThumbnail);
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle == null ? null : sTitle.trim();
	}

	public String getsDescription() {
		return sDescription;
	}

	public void setsDescription(String sDescription) {
		this.sDescription = sDescription == null ? null : sDescription.trim();
	}

	public String getsThumbnail() {
		return sThumbnail;
	}

	public void setsThumbnail(String sThumbnail) {
		this.sThumbnail = sThumbnail == null ? null : sThumbnail.trim();
	}

	public String getsPreview() {
		return sPreview;
	}

	public void setsPreview(String sPreview) {
		this.sPreview = sPreview == null ? null : sPreview.trim();
	}

	public String getsOriginal() {
		return sOriginal;
	}

	
	public void setsOriginal(String sOriginal) {
		this.sOriginal = sOriginal == null ? null : sOriginal.trim();
	}

	
	public Date getdCreatedOn() {
		return dCreatedOn;
	}

	
	public void setdCreatedOn(Date dCreatedOn) {
		this.dCreatedOn = dCreatedOn;
	}

	
	public Date getdUpdatedOn() {
		return dUpdatedOn;
	}

	
	public void setdUpdatedOn(Date dUpdatedOn) {
		this.dUpdatedOn = dUpdatedOn;
	}

	public void setfGalleryImage(CommonsMultipartFile fGalleryImage) {
		this.fGalleryImage = fGalleryImage;
	}

	public CommonsMultipartFile getfGalleryImage() {
		return fGalleryImage;
	}

	public void setsGalleryImagePath(String sGalleryImagePath) {
		this.sGalleryImagePath = sGalleryImagePath;
	}

	public String getsGalleryImagePath() {
		
			return sGalleryImagePath;
		
	}

	public void setGalleryImageAndroid4(String galleryImageAndroid4) {
		this.galleryImageAndroid4 = galleryImageAndroid4;
	}
	

	public void setGalleryImageAndroid3(String galleryImageAndroid3) {
		this.galleryImageAndroid3 = galleryImageAndroid3;
	}
	public void setGalleryImageAndroid6(String galleryImageAndroid6) {
		this.galleryImageAndroid6 = galleryImageAndroid6;
	}
	public void setGalleryImageIpad(String galleryImageIpad) {
		this.galleryImageIpad = galleryImageIpad;
	}
	
	public void setGalleryImageIphone(String galleryImageIphone) {
		this.galleryImageIphone = galleryImageIphone;
	}
	public void setGalleryImageIphone4(String galleryImageIphone4) {
		 
		this.galleryImageIphone4 = galleryImageIphone4;
	}
	
	
	public String getGalleryImageAndroid3() {
		
		if(sThumbnail!=null)galleryImageAndroid3=FileUtils.stuffedFilename(sThumbnail, "_"+Constants.GALLERY_IMAGE_ANDROID3_KEY);
 
		return galleryImageAndroid3;
	}
	
	public String getGalleryImageAndroid4() {
		
		if(sThumbnail!=null)galleryImageAndroid4=FileUtils.stuffedFilename(sThumbnail, "_"+Constants.GALLERY_IMAGE_ANDROID4_KEY);
		
		return galleryImageAndroid4;
	}

	public String getGalleryImageAndroid6() {
		if(sThumbnail!=null)galleryImageAndroid6=FileUtils.stuffedFilename(sThumbnail, "_"+Constants.GALLERY_IMAGE_ANDROID6_KEY);
		return galleryImageAndroid6;
	}

	public String getGalleryImageIpad() {
		if(sThumbnail!=null)galleryImageIpad=FileUtils.stuffedFilename(sThumbnail, "_"+Constants.GALLERY_IMAGE_IPAD_KEY);
		return galleryImageIpad;
	}

	public String getGalleryImageIphone() {
		if(sThumbnail!=null)galleryImageIphone=FileUtils.stuffedFilename(sThumbnail, "_"+Constants.GALLERY_IMAGE_IPHONE_KEY);
		return galleryImageIphone;
	}
	public String  getGalleryImageIphone4() {
		if(sThumbnail!=null)galleryImageIphone4=FileUtils.stuffedFilename(sThumbnail, "_"+Constants.GALLERY_IMAGE_IPHONE4_KEY);
		return  galleryImageIphone4;
	}
	
}