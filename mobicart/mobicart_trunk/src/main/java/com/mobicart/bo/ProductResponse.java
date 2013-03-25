package com.mobicart.bo;

import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import com.mobicart.model.Product;

/**
 * product response
 * @author jasdeep.singh
 *
 */
@JsonAutoDetect
public class ProductResponse {
	
	Integer start;
	Integer maxLimit;
	Integer totalCount;
	List<Product> productList;
	
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(Integer maxLimit) {
		this.maxLimit = maxLimit;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
	
	

}
