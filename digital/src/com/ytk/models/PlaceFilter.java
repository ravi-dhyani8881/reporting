package com.ytk.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@JsonWriteNullProperties(false)
public class PlaceFilter {
	
	
	private String  ID;
	private String Name; 
	private String Category1Name ;
	private String Category1ID;
	private String ScreenName; 
	private String ProfileFileJson;
	
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JsonProperty("Category1Name")
	public String getCategory1Name() {
		return Category1Name;
	}
	public void setCategory1Name(String category1Name) {
		Category1Name = category1Name;
	}
	@JsonProperty("Category1ID")
	public String getCategory1ID() {
		return Category1ID;
	}
	public void setCategory1ID(String category1id) {
		Category1ID = category1id;
	}
	@JsonProperty("ScreenName")
	public String getScreenName() {
		return ScreenName;
	}
	public void setScreenName(String screenName) {
		ScreenName = screenName;
	}
	@JsonProperty("ProfileFileJson")
	public String getProfileFileJson() {
		return ProfileFileJson;
	}
	public void setProfileFileJson(String profileFileJson) {
		ProfileFileJson = profileFileJson;
	}
	
	
	
	
	
	
	
}
