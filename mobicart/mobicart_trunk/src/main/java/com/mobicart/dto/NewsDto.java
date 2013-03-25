package com.mobicart.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;
/**
 * News dto
 * @author jasdeep.singh
 *
 */
@JsonWriteNullProperties(true)
public class NewsDto{
	
	private long id ;
	private String title;
	private String image;
	private String shortDescription;
	private String longDescription;
	private Date date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
