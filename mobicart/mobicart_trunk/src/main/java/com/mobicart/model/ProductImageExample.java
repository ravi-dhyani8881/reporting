package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductImageExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public ProductImageExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	protected ProductImageExample(ProductImageExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table product_images
	 * @ibatorgenerated  Fri Aug 20 18:18:08 IST 2010
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

		public Criteria andSTitleIsNull() {
			addCriterion("s_title is null");
			return this;
		}

		public Criteria andSTitleIsNotNull() {
			addCriterion("s_title is not null");
			return this;
		}

		public Criteria andSTitleEqualTo(String value) {
			addCriterion("s_title =", value, "sTitle");
			return this;
		}

		public Criteria andSTitleNotEqualTo(String value) {
			addCriterion("s_title <>", value, "sTitle");
			return this;
		}

		public Criteria andSTitleGreaterThan(String value) {
			addCriterion("s_title >", value, "sTitle");
			return this;
		}

		public Criteria andSTitleGreaterThanOrEqualTo(String value) {
			addCriterion("s_title >=", value, "sTitle");
			return this;
		}

		public Criteria andSTitleLessThan(String value) {
			addCriterion("s_title <", value, "sTitle");
			return this;
		}

		public Criteria andSTitleLessThanOrEqualTo(String value) {
			addCriterion("s_title <=", value, "sTitle");
			return this;
		}

		public Criteria andSTitleLike(String value) {
			addCriterion("s_title like", value, "sTitle");
			return this;
		}

		public Criteria andSTitleNotLike(String value) {
			addCriterion("s_title not like", value, "sTitle");
			return this;
		}

		public Criteria andSTitleIn(List values) {
			addCriterion("s_title in", values, "sTitle");
			return this;
		}

		public Criteria andSTitleNotIn(List values) {
			addCriterion("s_title not in", values, "sTitle");
			return this;
		}

		public Criteria andSTitleBetween(String value1, String value2) {
			addCriterion("s_title between", value1, value2, "sTitle");
			return this;
		}

		public Criteria andSTitleNotBetween(String value1, String value2) {
			addCriterion("s_title not between", value1, value2, "sTitle");
			return this;
		}

		public Criteria andSLocationIsNull() {
			addCriterion("s_location is null");
			return this;
		}

		public Criteria andSLocationIsNotNull() {
			addCriterion("s_location is not null");
			return this;
		}

		public Criteria andSLocationEqualTo(String value) {
			addCriterion("s_location =", value, "sLocation");
			return this;
		}

		public Criteria andSLocationNotEqualTo(String value) {
			addCriterion("s_location <>", value, "sLocation");
			return this;
		}

		public Criteria andSLocationGreaterThan(String value) {
			addCriterion("s_location >", value, "sLocation");
			return this;
		}

		public Criteria andSLocationGreaterThanOrEqualTo(String value) {
			addCriterion("s_location >=", value, "sLocation");
			return this;
		}

		public Criteria andSLocationLessThan(String value) {
			addCriterion("s_location <", value, "sLocation");
			return this;
		}

		public Criteria andSLocationLessThanOrEqualTo(String value) {
			addCriterion("s_location <=", value, "sLocation");
			return this;
		}

		public Criteria andSLocationLike(String value) {
			addCriterion("s_location like", value, "sLocation");
			return this;
		}

		public Criteria andSLocationNotLike(String value) {
			addCriterion("s_location not like", value, "sLocation");
			return this;
		}

		public Criteria andSLocationIn(List values) {
			addCriterion("s_location in", values, "sLocation");
			return this;
		}

		public Criteria andSLocationNotIn(List values) {
			addCriterion("s_location not in", values, "sLocation");
			return this;
		}

		public Criteria andSLocationBetween(String value1, String value2) {
			addCriterion("s_location between", value1, value2, "sLocation");
			return this;
		}

		public Criteria andSLocationNotBetween(String value1, String value2) {
			addCriterion("s_location not between", value1, value2, "sLocation");
			return this;
		}
	}
}