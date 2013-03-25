package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


public class ForumEvent{
	
	@Field
	private String forumEvent_TopicID;
	@Field
	private String forumEvent_SectionID;
	@Field
	private String forumEvent_CreatedBy;
	@Field
	private String forumEvent_CreatedFor;
	@Field
	private String forumEvent_Event;
	@Field
	private String forumEvent_TimeStamp;
	
	
	public String getforumEvent_TopicID(){
		return this.forumEvent_TopicID;
	}
	public void setforumEvent_TopicID(String forumEvent_TopicID){
		this.forumEvent_TopicID = forumEvent_TopicID;
	}
	
	public String getforumEvent_SectionID(){
		return this.forumEvent_SectionID;
	}
	public void setforumEvent_SectionID(String forumEvent_SectionID){
		this.forumEvent_SectionID = forumEvent_SectionID;
	}
	
	public String getforumEvent_CreatedBy(){
		return this.forumEvent_CreatedBy;
	}
	public void setforumEvent_CreatedBy(String forumEvent_CreatedBy){
		this.forumEvent_CreatedBy = forumEvent_CreatedBy;
	}
	
	public String getforumEvent_CreatedFor(){
		return this.forumEvent_CreatedFor;
	}
	public void setforumEvent_CreatedFor(String forumEvent_CreatedFor){
		this.forumEvent_CreatedFor = forumEvent_CreatedFor;
	}
	
	public String getforumEvent_Event(){
		return this.forumEvent_Event;
	}
	public void setforumEvent_Event(String forumEvent_Event){
		this.forumEvent_Event = forumEvent_Event;
	}
	
	public String getforumEvent_TimeStamp(){
		return this.forumEvent_TimeStamp;
	}
	public void setforumEvent_TimeStamp(String forumEvent_TimeStamp){
		this.forumEvent_TimeStamp = forumEvent_TimeStamp;
	}
	
	
}