package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.ProductReview;
import com.mobicart.model.ProductReviewExample;

public interface ProductReviewDAO  extends DAO<ProductReview, Long>{

	/**
	 * find product reviews by example
	 * @param productReviewExample
	 * @return
	 */
	List<ProductReview> findByExample(ProductReviewExample productReviewExample);
	
	/**
	 * find product by example
	 * @param productReviewExample
	 * @return
	 */
	ProductReview findObjectByExample(ProductReviewExample productReviewExample);
	
	/**
	 * find product rating
	 * @param productId
	 * @return integer of rating for an get product rating
	 */
	Integer getProductRating(long productId);
}
