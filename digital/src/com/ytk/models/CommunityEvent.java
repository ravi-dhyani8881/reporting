package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


public class CommunityEvent{
	@Field
	private String communityEvent_business_id;
	@Field
	private String communityEvent_SectionID;
	@Field
	private String communityEvent_CreatedBy;
	@Field
	private String communityEvent_CreatedFor;
	@Field
	private String communityEvent_Event;
	@Field
	private String communityEvent_TimeStamp;
	
	public String getcommunityEvent_Business_id(){
		return this.communityEvent_business_id;
	}
	public void setcommunityEvent_Business_id(String communityEvent_business_id){
		this.communityEvent_business_id = communityEvent_business_id;
	}
	
	public String getcommunityEvent_SectionID(){
		return this.communityEvent_SectionID;
	}
	public void setcommunityEvent_SectionID(String communityEvent_SectionID){
		this.communityEvent_SectionID = communityEvent_SectionID;
	}
	
	public String getcommunityEvent_CreatedBy(){
		return this.communityEvent_CreatedBy;
	}
	public void setcommunityEvent_CreatedBy(String communityEvent_CreatedBy){
		this.communityEvent_CreatedBy = communityEvent_CreatedBy;
	}
	
	public String getcommunityEvent_CreatedFor(){
		return this.communityEvent_CreatedFor;
	}
	public void setcommunityEvent_CreatedFor(String communityEvent_CreatedFor){
		this.communityEvent_CreatedFor = communityEvent_CreatedFor;
	}
	
	public String getcommunityEvent_Event(){
		return this.communityEvent_Event;
	}
	public void setcommunityEvent_Event(String communityEvent_Event){
		this.communityEvent_Event = communityEvent_Event;
	}
	
	public String getcommunityEvent_TimeStamp(){
		return this.communityEvent_TimeStamp;
	}
	public void setcommunityEvent_TimeStamp(String communityEvent_TimeStamp){
		this.communityEvent_TimeStamp = communityEvent_TimeStamp;
	}
}