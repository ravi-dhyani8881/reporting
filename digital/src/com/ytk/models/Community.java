package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


public class Community{
	
	@Field
	private String community_business_id;
	@Field
	private String community_business_name;
	@Field
	private String community_safe_name;
	@Field
	private String community_city;
	@Field
	private String community_city_id;
	@Field
	private String community_country;
	@Field
	private String community_member_id;
	
	public String getcommunity_Business_id(){
		return this.community_business_id;
	}
	
	public void setcommunity_Business_id(String community_business_id){
		this.community_business_id = community_business_id;
	}
	
	public String getcommunity_Business_name(){
		return this.community_business_name;
	}
	
	public void setcommunity_Business_name(String community_business_name){
		this.community_business_name = community_business_name;
	}
	
	public String getcommunity_Safe_name(){
		return this.community_safe_name;
	}
	
	public void setcommunity_Safe_name(String community_safe_name){
		this.community_safe_name = community_safe_name;
	}
	
	public String getcommunity_City(){
		return this.community_city;
	}
	
	public void setcommunity_City(String community_city){
		this.community_city = community_city;
	}
	
	public String getcommunity_City_id(){
		return this.community_business_id;
	}
	
	public void setcommunity_City_id(String community_city_id){
		this.community_city_id = community_city_id;
	}
	
	public String getcommunity_Country(){
		return this.community_country;
	}
	
	public void setcommunity_Country(String community_country){
		this.community_country = community_country;
	}
	
	public String getcommunity_Member_id(){
		return this.community_member_id;
	}
	
	public void setcommunity_Member_id(String community_member_id){
		this.community_member_id = community_member_id;
	}
}