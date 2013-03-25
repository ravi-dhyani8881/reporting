package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


public class Member implements Comparable{
	
	@Field
	private String member_id;
	@Field
	private String email;
	@Field
	private String first_name;
	@Field
	private String middle_name;
	@Field
	private String last_name;
	@Field
	private String display_name;
	@Field
	private String screen_name;
	@Field
	private String profile_image;
	@Field
	private String city;
	@Field
	private String state;
	@Field
	private String country;
	@Field
	private String updateDate;
	
	
	
	public Member(){}
	
	public Member(String member_id, String email, String first_name,
			String middle_name, String last_name, String display_name,
			String screen_name, String profile_image, String city,
			String state, String country, String updateDate){
		
		
		this.city = city;
		this.country = country;
		this.display_name = display_name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.member_id = member_id;
		this.middle_name = middle_name;
		this.profile_image = profile_image;
		this.screen_name = screen_name;
		this.state = state;
		this.updateDate = updateDate;
		
	}
	
	public Member(String member_id,  String first_name,String last_name){
		
		this.first_name = first_name;
		this.last_name = last_name;
		this.member_id = member_id;
		
	}

	
	public void setMember_id(String member_id){
		this.member_id = member_id;
	}
	
	public String getMember_id(){
		return this.member_id;
	}
    
	public void setMember_email(String email){
		this.email = email;
	}
	
	public String getMember_email(){
		return this.email;
	}
	
	public void setFirst_name(String first_name){
		this.first_name = first_name;
	}
	
	public String getFirst_name(){
		return this.first_name;
	}
	
	public void setMiddle_name(String middle_name){
		this.middle_name = middle_name;
	}
	
	public String getMiddle_name(){
		return this.middle_name;
	}
	
	public void setLast_name(String last_name){
		this.last_name = last_name;
	}
	
	public String getLast_name(){
		return this.last_name;
	}
	
	public void setDisplay_name(String display_name){
		this.display_name = display_name;
	}
	
	public String getDisplay_name(){
		return this.display_name;
	}
	
	public void setScreen_name(String screen_name){
		this.screen_name = screen_name;
	}
	
	public String getScreen_name(){
		return this.screen_name;
	}
	
	public void setProfile_image(String profile_image){
		this.profile_image = profile_image;
	}
	
	public String getProfile_image(){
		return this.profile_image;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}
	
	public String getUpdateDate(){
		return this.updateDate;
	}
	
	
	public int compareTo(Object obj){
		if(obj instanceof Member){
			Member mem = (Member)obj;
			return this.getFirst_name().compareTo(mem.getFirst_name());
		}else return -1;
	}
}