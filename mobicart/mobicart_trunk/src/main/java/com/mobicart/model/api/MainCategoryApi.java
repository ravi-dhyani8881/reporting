package com.mobicart.model.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;
@JsonWriteNullProperties(false)
public class MainCategoryApi {

	private int size;
	private Long storeId;
	private Long departmentId;
	private List<CategoryApi> subDepartments;
	
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
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public List<CategoryApi> getSubDepartments() {
		return subDepartments;
	}
	public void setSubDepartments(List<CategoryApi> subDepartments) {
		this.subDepartments = subDepartments;
	}
	

}
