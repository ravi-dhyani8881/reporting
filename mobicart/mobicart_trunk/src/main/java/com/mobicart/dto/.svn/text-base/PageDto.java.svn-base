package com.mobicart.dto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
/**
 * Staic Page dto
 * @author jasdeep.singh
 *
 */
@JsonWriteNullProperties(false)
public class PageDto {
	
	private long id;
	private long userId;
	private long appId;
	private String name;
	private String title;
	private String description;
	private boolean removeBranding;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isRemoveBranding() {
		return removeBranding;
	}
	public void setRemoveBranding(boolean removeBranding) {
		this.removeBranding = removeBranding;
	}
	
	@JsonIgnore
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@JsonIgnore
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	
	
	
	
}
