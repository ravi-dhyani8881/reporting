package com.ytk.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class HeaderThings {

	private String 	ID;
	private String  ScreenName="";
	private String ProfileImage="";
	private String CategoryName="";
	private String SubCategoryName="";
	private String 	Name="";
	
	private String ProfileFileJson="";
	
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@JsonProperty("ScreenName")
	public String getScreenName() {
		return ScreenName;
	}
	public void setScreenName(String screenName) {
		ScreenName = screenName;
	}
	
	@JsonProperty("ProfileImage")
	public String getProfileImage() {
		return ProfileImage;
	}
	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}
	
	@JsonProperty("CategoryName")
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	@JsonProperty("SubCategoryName")
	public String getSubCategoryName() {
		return SubCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		SubCategoryName = subCategoryName;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("ProfileFileJson")
	public String getProfileFileJson() {
		return ProfileFileJson;
	}
	public void setProfileFileJson(String profileFileJson) {
		ProfileFileJson = profileFileJson;
	}
	
}
