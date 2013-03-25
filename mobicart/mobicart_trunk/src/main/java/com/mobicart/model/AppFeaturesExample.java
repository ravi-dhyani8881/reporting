package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppFeaturesExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public AppFeaturesExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	protected AppFeaturesExample(AppFeaturesExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table app_features
	 * @ibatorgenerated  Tue Aug 03 16:33:41 IST 2010
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

		public Criteria andAppIdIsNull() {
			addCriterion("app_id is null");
			return this;
		}

		public Criteria andAppIdIsNotNull() {
			addCriterion("app_id is not null");
			return this;
		}

		public Criteria andAppIdEqualTo(Long value) {
			addCriterion("app_id =", value, "appId");
			return this;
		}

		public Criteria andAppIdNotEqualTo(Long value) {
			addCriterion("app_id <>", value, "appId");
			return this;
		}

		public Criteria andAppIdGreaterThan(Long value) {
			addCriterion("app_id >", value, "appId");
			return this;
		}

		public Criteria andAppIdGreaterThanOrEqualTo(Long value) {
			addCriterion("app_id >=", value, "appId");
			return this;
		}

		public Criteria andAppIdLessThan(Long value) {
			addCriterion("app_id <", value, "appId");
			return this;
		}

		public Criteria andAppIdLessThanOrEqualTo(Long value) {
			addCriterion("app_id <=", value, "appId");
			return this;
		}

		public Criteria andAppIdIn(List values) {
			addCriterion("app_id in", values, "appId");
			return this;
		}

		public Criteria andAppIdNotIn(List values) {
			addCriterion("app_id not in", values, "appId");
			return this;
		}

		public Criteria andAppIdBetween(Long value1, Long value2) {
			addCriterion("app_id between", value1, value2, "appId");
			return this;
		}

		public Criteria andAppIdNotBetween(Long value1, Long value2) {
			addCriterion("app_id not between", value1, value2, "appId");
			return this;
		}

		public Criteria andFeatureIdIsNull() {
			addCriterion("feature_id is null");
			return this;
		}

		public Criteria andFeatureIdIsNotNull() {
			addCriterion("feature_id is not null");
			return this;
		}

		public Criteria andFeatureIdEqualTo(Long value) {
			addCriterion("feature_id =", value, "featureId");
			return this;
		}

		public Criteria andFeatureIdNotEqualTo(Long value) {
			addCriterion("feature_id <>", value, "featureId");
			return this;
		}

		public Criteria andFeatureIdGreaterThan(Long value) {
			addCriterion("feature_id >", value, "featureId");
			return this;
		}

		public Criteria andFeatureIdGreaterThanOrEqualTo(Long value) {
			addCriterion("feature_id >=", value, "featureId");
			return this;
		}

		public Criteria andFeatureIdLessThan(Long value) {
			addCriterion("feature_id <", value, "featureId");
			return this;
		}

		public Criteria andFeatureIdLessThanOrEqualTo(Long value) {
			addCriterion("feature_id <=", value, "featureId");
			return this;
		}

		public Criteria andFeatureIdIn(List values) {
			addCriterion("feature_id in", values, "featureId");
			return this;
		}

		public Criteria andFeatureIdNotIn(List values) {
			addCriterion("feature_id not in", values, "featureId");
			return this;
		}

		public Criteria andFeatureIdBetween(Long value1, Long value2) {
			addCriterion("feature_id between", value1, value2, "featureId");
			return this;
		}

		public Criteria andFeatureIdNotBetween(Long value1, Long value2) {
			addCriterion("feature_id not between", value1, value2, "featureId");
			return this;
		}
	}
}