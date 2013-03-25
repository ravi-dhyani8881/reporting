package com.ytk.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@JsonWriteNullProperties(false)
public class HeaderFace {

	
	private String  ID; 
	private String	ProfileFileID;
	private String	Name;
	private String	ScreenName;
	private String	ProfileImage;
	private String	CategoryName;
	private String	City; 	
	private String	State; 
	private String	FirstName ;	
	private String	LastName ;
	private String ProfileFileJson;
	
	
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@JsonProperty("ProfileFileID")
	public String getProfileFileID() {
		return ProfileFileID;
	}
	public void setProfileFileID(String profileFileID) {
		ProfileFileID = profileFileID;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	
	@JsonProperty("City")
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	@JsonProperty("FirstName")
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	@JsonProperty("LastName")
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	@JsonProperty("ProfileFileJson")
	public String getProfileFileJson() {
		return ProfileFileJson;
	}
	public void setProfileFileJson(String profileFileJson) {
		ProfileFileJson = profileFileJson;
	}	
	
	
	
	
}
