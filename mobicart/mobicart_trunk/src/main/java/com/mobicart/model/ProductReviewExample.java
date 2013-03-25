package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductReviewExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public ProductReviewExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	protected ProductReviewExample(ProductReviewExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table product_reviews
	 * @ibatorgenerated  Mon Dec 20 16:44:10 IST 2010
	 */
	public static class Criteria {
		protected List criteriaWithoutValue;
		protected List criteriaWithSingleValue;
		protected List criteriaWithListValue;
		protected List criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList();
			criteriaWithSingleValue = new ArrayList();
			criteriaWithListValue = new ArrayList();
			criteriaWithBetweenValue = new ArrayList();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List values,
				String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return this;
		}

		public Criteria andIdIn(List values) {
			addCriterion("id in", values, "id");
			return this;
		}

		public Criteria andIdNotIn(List values) {
			addCriterion("id not in", values, "id");
			return this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return this;
		}

		public Criteria andProductIdIsNull() {
			addCriterion("product_id is null");
			return this;
		}

		public Criteria andProductIdIsNotNull() {
			addCriterion("product_id is not null");
			return this;
		}

		public Criteria andProductIdEqualTo(Long value) {
			addCriterion("product_id =", value, "productId");
			return this;
		}

		public Criteria andProductIdNotEqualTo(Long value) {
			addCriterion("product_id <>", value, "productId");
			return this;
		}

		public Criteria andProductIdGreaterThan(Long value) {
			addCriterion("product_id >", value, "productId");
			return this;
		}

		public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
			addCriterion("product_id >=", value, "productId");
			return this;
		}

		public Criteria andProductIdLessThan(Long value) {
			addCriterion("product_id <", value, "productId");
			return this;
		}

		public Criteria andProductIdLessThanOrEqualTo(Long value) {
			addCriterion("product_id <=", value, "productId");
			return this;
		}

		public Criteria andProductIdIn(List values) {
			addCriterion("product_id in", values, "productId");
			return this;
		}

		public Criteria andProductIdNotIn(List values) {
			addCriterion("product_id not in", values, "productId");
			return this;
		}

		public Criteria andProductIdBetween(Long value1, Long value2) {
			addCriterion("product_id between", value1, value2, "productId");
			return this;
		}

		public Criteria andProductIdNotBetween(Long value1, Long value2) {
			addCriterion("product_id not between", value1, value2, "productId");
			return this;
		}

		public Criteria andSReveiwerNameIsNull() {
			addCriterion("s_reveiwer_name is null");
			return this;
		}

		public Criteria andSReveiwerNameIsNotNull() {
			addCriterion("s_reveiwer_name is not null");
			return this;
		}

		public Criteria andSReveiwerNameEqualTo(String value) {
			addCriterion("s_reveiwer_name =", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameNotEqualTo(String value) {
			addCriterion("s_reveiwer_name <>", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameGreaterThan(String value) {
			addCriterion("s_reveiwer_name >", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameGreaterThanOrEqualTo(String value) {
			addCriterion("s_reveiwer_name >=", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameLessThan(String value) {
			addCriterion("s_reveiwer_name <", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameLessThanOrEqualTo(String value) {
			addCriterion("s_reveiwer_name <=", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameLike(String value) {
			addCriterion("s_reveiwer_name like", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameNotLike(String value) {
			addCriterion("s_reveiwer_name not like", value, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameIn(List values) {
			addCriterion("s_reveiwer_name in", values, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameNotIn(List values) {
			addCriterion("s_reveiwer_name not in", values, "sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameBetween(String value1, String value2) {
			addCriterion("s_reveiwer_name between", value1, value2,
					"sReveiwerName");
			return this;
		}

		public Criteria andSReveiwerNameNotBetween(String value1, String value2) {
			addCriterion("s_reveiwer_name not between", value1, value2,
					"sReveiwerName");
			return this;
		}

		public Criteria andSReviewerEmailIsNull() {
			addCriterion("s_reviewer_email is null");
			return this;
		}

		public Criteria andSReviewerEmailIsNotNull() {
			addCriterion("s_reviewer_email is not null");
			return this;
		}

		public Criteria andSReviewerEmailEqualTo(String value) {
			addCriterion("s_reviewer_email =", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailNotEqualTo(String value) {
			addCriterion("s_reviewer_email <>", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailGreaterThan(String value) {
			addCriterion("s_reviewer_email >", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailGreaterThanOrEqualTo(String value) {
			addCriterion("s_reviewer_email >=", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailLessThan(String value) {
			addCriterion("s_reviewer_email <", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailLessThanOrEqualTo(String value) {
			addCriterion("s_reviewer_email <=", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailLike(String value) {
			addCriterion("s_reviewer_email like", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailNotLike(String value) {
			addCriterion("s_reviewer_email not like", value, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailIn(List values) {
			addCriterion("s_reviewer_email in", values, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailNotIn(List values) {
			addCriterion("s_reviewer_email not in", values, "sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailBetween(String value1, String value2) {
			addCriterion("s_reviewer_email between", value1, value2,
					"sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerEmailNotBetween(String value1, String value2) {
			addCriterion("s_reviewer_email not between", value1, value2,
					"sReviewerEmail");
			return this;
		}

		public Criteria andSReviewerLocationIsNull() {
			addCriterion("s_reviewer_location is null");
			return this;
		}

		public Criteria andSReviewerLocationIsNotNull() {
			addCriterion("s_reviewer_location is not null");
			return this;
		}

		public Criteria andSReviewerLocationEqualTo(String value) {
			addCriterion("s_reviewer_location =", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationNotEqualTo(String value) {
			addCriterion("s_reviewer_location <>", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationGreaterThan(String value) {
			addCriterion("s_reviewer_location >", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationGreaterThanOrEqualTo(String value) {
			addCriterion("s_reviewer_location >=", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationLessThan(String value) {
			addCriterion("s_reviewer_location <", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationLessThanOrEqualTo(String value) {
			addCriterion("s_reviewer_location <=", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationLike(String value) {
			addCriterion("s_reviewer_location like", value, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationNotLike(String value) {
			addCriterion("s_reviewer_location not like", value,
					"sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationIn(List values) {
			addCriterion("s_reviewer_location in", values, "sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationNotIn(List values) {
			addCriterion("s_reviewer_location not in", values,
					"sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationBetween(String value1, String value2) {
			addCriterion("s_reviewer_location between", value1, value2,
					"sReviewerLocation");
			return this;
		}

		public Criteria andSReviewerLocationNotBetween(String value1,
				String value2) {
			addCriterion("s_reviewer_location not between", value1, value2,
					"sReviewerLocation");
			return this;
		}

		public Criteria andIRatingIsNull() {
			addCriterion("i_rating is null");
			return this;
		}

		public Criteria andIRatingIsNotNull() {
			addCriterion("i_rating is not null");
			return this;
		}

		public Criteria andIRatingEqualTo(Integer value) {
			addCriterion("i_rating =", value, "iRating");
			return this;
		}

		public Criteria andIRatingNotEqualTo(Integer value) {
			addCriterion("i_rating <>", value, "iRating");
			return this;
		}

		public Criteria andIRatingGreaterThan(Integer value) {
			addCriterion("i_rating >", value, "iRating");
			return this;
		}

		public Criteria andIRatingGreaterThanOrEqualTo(Integer value) {
			addCriterion("i_rating >=", value, "iRating");
			return this;
		}

		public Criteria andIRatingLessThan(Integer value) {
			addCriterion("i_rating <", value, "iRating");
			return this;
		}

		public Criteria andIRatingLessThanOrEqualTo(Integer value) {
			addCriterion("i_rating <=", value, "iRating");
			return this;
		}

		public Criteria andIRatingIn(List values) {
			addCriterion("i_rating in", values, "iRating");
			return this;
		}

		public Criteria andIRatingNotIn(List values) {
			addCriterion("i_rating not in", values, "iRating");
			return this;
		}

		public Criteria andIRatingBetween(Integer value1, Integer value2) {
			addCriterion("i_rating between", value1, value2, "iRating");
			return this;
		}

		public Criteria andIRatingNotBetween(Integer value1, Integer value2) {
			addCriterion("i_rating not between", value1, value2, "iRating");
			return this;
		}
	}
}