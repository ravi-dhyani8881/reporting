package com.mobicart.model.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import com.mobicart.model.Category;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@JsonWriteNullProperties(false)
public class CategoryApi {

	private Long subDepartmentId;
	@XStreamOmitField
	private Long parentSubDepartmentId;
	private String subDepartmentName;
    private String subDepartmentStatus;
    private Integer productCount;
    private List<CategoryApi> subDepartments;
    
    
	public Long getSubDepartmentId() {
		return subDepartmentId;
	}
	public void setSubDepartmentId(Long subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}
	public String getSubDepartmentName() {
		return subDepartmentName;
	}
	public void setSubDepartmentName(String subDepartmentName) {
		this.subDepartmentName = subDepartmentName;
	}
	public String getSubDepartmentStatus() {
		return subDepartmentStatus;
	}
	public void setSubDepartmentStatus(String subDepartmentStatus) {
		this.subDepartmentStatus = subDepartmentStatus;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	@JsonIgnore
    public Long getParentSubDepartmentId() {
		return parentSubDepartmentId;
	}
	public void setParentSubDepartmentId(Long parentSubDepartmentId) {
		this.parentSubDepartmentId = parentSubDepartmentId;
	}
	
	public List<CategoryApi> getSubDepartments() {
		return subDepartments;
	}
	public void setSubDepartments(List<CategoryApi> subDepartments) {
		this.subDepartments = subDepartments;
	}

}
