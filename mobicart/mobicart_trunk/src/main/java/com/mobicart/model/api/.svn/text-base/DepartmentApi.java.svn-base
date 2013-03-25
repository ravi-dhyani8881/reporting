package com.mobicart.model.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("department")
@JsonWriteNullProperties(false)
public class DepartmentApi {
	@XStreamAlias("departmentId")
	private long id;
	@XStreamOmitField
	private long storeId;
	@XStreamOmitField
	private long userId;
	@XStreamOmitField
	private long parentDepartmentId;
    private String departmentName;
    private String departmentStatus;
    private List<DepartmentApi> subDepartments;
    
	
    
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentStatus() {
		return departmentStatus;
	}
	public void setDepartmentStatus(String departmentStatus) {
		this.departmentStatus = departmentStatus;
	}
	
	@JsonIgnore
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
	@JsonIgnore
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@JsonIgnore
	public long getParentDepartmentId() {
		return parentDepartmentId;
	}
	public void setParentDepartmentId(long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}
	
	@JsonProperty("departmentId")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<DepartmentApi> getSubDepartments() {
		return subDepartments;
	}
	public void setSubDepartments(List<DepartmentApi> subDepartments) {
		this.subDepartments = subDepartments;
	}
	

	
	
}