/**
 * 
 */
package com.mobicart.util;

/**
 * @author jasdeep.singh
 *
 */
public class Pager {

	private int page=0;
    private int results=50;
    private String sortOrder="DESC";
    private String sortBy="id";
    private String keyword;
    private Long orderNo;
	private String orderByClause;
	private int lowerLimit=0;
	private int upperLimit;
	private int defaultLimit=results;
	public Pager() {
		super();
	}
	public Pager(int page, int results, String sortOrder, String sortBy) {
		super();
		this.page = page;
		this.results = results;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
	}
	
	public Pager(int page, int results, String sortOrder, String sortBy,
			String keyword) {
		super();
		this.page = page;
		this.results = results;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
		this.keyword = keyword;
	}
	
	
	
	public Pager(int page, int results, String sortOrder, String sortBy,
			String keyword, Long orderNo) {
		super();
		this.page = page;
		this.results = results;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
		this.keyword = keyword;
		this.orderNo = orderNo;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderByClause() {
		this.orderByClause=this.sortBy+" "+this.sortOrder;
		return this.orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public int getLowerLimit() {
		if(this.lowerLimit > 0)
			return this.lowerLimit;
		else{
			return (((this.page * this.defaultLimit)));
		}
		
	}
	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public int getUpperLimit() {
		this.upperLimit=this.results;
		return this.upperLimit;
	}
	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}
	public int getDefaultLimit() {
		return defaultLimit;
	}
	public void setDefaultLimit(int defaultLimit) {
		this.defaultLimit = defaultLimit;
	}
	 
		
	
}
