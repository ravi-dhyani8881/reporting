package com.mobicart.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.ProductReview;
import com.mobicart.model.ProductReviewExample;

public class ProductReviewDAOImpl extends SqlMapClientDaoSupport implements ProductReviewDAO {

	/**
	 * {@inheritDoc}
	 */
	public Long create(ProductReview newInstance)  {
		Long id=null;
			id=(Long) getSqlMapClientTemplate().insert("product_reviews.insert", newInstance);
		return id;
	}

	public ProductReview find(Long id) {
		ProductReviewExample example=new ProductReviewExample();
		example.createCriteria().andIdEqualTo(id);
		ProductReview instance=null;
			instance= (ProductReview) getSqlMapClientTemplate().queryForObject("product_reviews.selectByExample", example);
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<ProductReview> findAll() {
		List<ProductReview>  productReviews=null;
		ProductReviewExample example=new ProductReviewExample();
			productReviews=getSqlMapClientTemplate().queryForList("product_reviews.selectByExample", example);
		return productReviews;
	}

	public void update(ProductReview transientInstance) {
		getSqlMapClientTemplate().update("product_reviews.updateByPrimaryKeySelective", transientInstance);
	}

	public void delete(ProductReview persistentInstance) {
		getSqlMapClientTemplate().update("product_reviews.deleteByPrimaryKeySelective", persistentInstance);
	}

	@SuppressWarnings("unchecked")
	public List<ProductReview> findByExample(
			ProductReviewExample productReviewExample) {
		List<ProductReview>  productReviews=null;
			productReviews=getSqlMapClientTemplate().queryForList("product_reviews.selectByExample",productReviewExample);
		return productReviews;
	}

	public ProductReview findObjectByExample(
			ProductReviewExample productReviewExample) {
		ProductReview instance=null;
			instance= (ProductReview) getSqlMapClientTemplate().queryForObject("product_reviews.selectByExample",productReviewExample );
		return instance;
	}

	public Integer getProductRating(long productId) {
		
		return (Integer) getSqlMapClientTemplate().queryForObject("product_reviews.selectProductRating",productId );
		
	}
	
	

}
