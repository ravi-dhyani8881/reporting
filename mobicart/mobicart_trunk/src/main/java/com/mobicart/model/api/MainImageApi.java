package com.mobicart.model.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;
@JsonWriteNullProperties(false)
public class MainImageApi {

	private int size;
	private Long storeId;
	private List<ImageApi> images;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public List<ImageApi> getImages() {
		return images;
	}
	public void setImages(List<ImageApi> images) {
		this.images = images;
	}
	

}
