package com.mobicart.dto;

/**
 * Department Dto
 * @author jasdeep.singh
 *
 */
public class DepartmentDto  {
	
	private long id ;
	
	private String name;
	
	private int categoryCount;
	
	private int productCount;

	
	
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

	public int getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	
	
	

	
}
