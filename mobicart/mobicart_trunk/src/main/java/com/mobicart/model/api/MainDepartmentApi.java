package com.mobicart.model.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;
@JsonWriteNullProperties(false)
public class MainDepartmentApi {

	private int size;
	private Long storeId;
	private List<DepartmentApi> departments;
	
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
	public List<DepartmentApi> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentApi> departments) {
		this.departments = departments;
	}
	

}
