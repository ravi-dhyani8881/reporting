package com.mobicart.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Department {

	@XStreamAlias("departmentId")
	private long id;
	@XStreamOmitField
    private long storeId;
	@XStreamOmitField
    private long userId;
	@XStreamOmitField
    private long parentDepartmentId;
	@XStreamAlias("departmentName")
	private String sName;
	@XStreamAlias("departmentStatus")
    private String sStatus;
	@XStreamOmitField
    private List<Category> categories;
	@XStreamOmitField
    private int iCategoryCount;
	@XStreamOmitField
    private int iProductCount;
    
    private List<Department> subDepartments;


	

	public Department() {
		super();
	}



	public Department(long id) {
		super();
		this.id = id;
	}

    
    
	public Department(long storeId, long userId, String sName, String sStatus) {
		super();
		this.storeId = storeId;
		this.userId = userId;
		this.sName = sName;
		this.sStatus = sStatus;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getStoreId() {
		return storeId;
	}



	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}



	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}



	public String getsName() {
		return sName;
	}



	public void setsName(String sName) {
		this.sName = sName;
	}



	public String getsStatus() {
		return sStatus;
	}



	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}



	public List<Category> getCategories() {
		return categories;
	}



	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}



	public int getiCategoryCount() {
		return iCategoryCount;
	}



	public void setiCategoryCount(Integer iCategoryCount) {
		this.iCategoryCount = iCategoryCount;
	}



	public int getiProductCount() {
		return iProductCount;
	}



	public void setiProductCount(int iProductCount) {
		this.iProductCount = iProductCount;
	}



	
	
	public List<Department> getSubDepartments() {
		return subDepartments;
	}



	public void setSubDepartments(List<Department> subDepartments) {
		this.subDepartments = subDepartments;
	}



	@Override
	public String toString() {
		return "Department [id=" + id + ", storeId=" + storeId + ", userId="
				+ userId + ", parentDepartmentId=" + parentDepartmentId
				+ ", sName=" + sName + ", sStatus=" + sStatus + ", categories="
				+ categories + ", iCategoryCount=" + iCategoryCount
				+ ", iProductCount=" + iProductCount + ", subDepartments="
				+ subDepartments + "]";
	}



	

	
}
