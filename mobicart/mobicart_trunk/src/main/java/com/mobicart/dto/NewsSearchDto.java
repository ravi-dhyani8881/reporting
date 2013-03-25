package com.mobicart.dto;

/**
 * for searching news
 * @author jasdeep.singh
 *
 */
public class NewsSearchDto {

	 long storeId;
	 String  keyword;
 
 	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return super.toString();
	}
	 
	
	
}
