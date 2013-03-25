package com.mobicart.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabelsExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public LabelsExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected LabelsExample(LabelsExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table labels
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
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

		public Criteria andLabelIdIsNull() {
			addCriterion("label_id is null");
			return this;
		}

		public Criteria andLabelIdIsNotNull() {
			addCriterion("label_id is not null");
			return this;
		}

		public Criteria andLabelIdEqualTo(Long value) {
			addCriterion("label_id =", value, "labelId");
			return this;
		}

		public Criteria andLabelIdNotEqualTo(Long value) {
			addCriterion("label_id <>", value, "labelId");
			return this;
		}

		public Criteria andLabelIdGreaterThan(Long value) {
			addCriterion("label_id >", value, "labelId");
			return this;
		}

		public Criteria andLabelIdGreaterThanOrEqualTo(Long value) {
			addCriterion("label_id >=", value, "labelId");
			return this;
		}

		public Criteria andLabelIdLessThan(Long value) {
			addCriterion("label_id <", value, "labelId");
			return this;
		}

		public Criteria andLabelIdLessThanOrEqualTo(Long value) {
			addCriterion("label_id <=", value, "labelId");
			return this;
		}

		public Criteria andLabelIdIn(List values) {
			addCriterion("label_id in", values, "labelId");
			return this;
		}

		public Criteria andLabelIdNotIn(List values) {
			addCriterion("label_id not in", values, "labelId");
			return this;
		}

		public Criteria andLabelIdBetween(Long value1, Long value2) {
			addCriterion("label_id between", value1, value2, "labelId");
			return this;
		}

		public Criteria andLabelIdNotBetween(Long value1, Long value2) {
			addCriterion("label_id not between", value1, value2, "labelId");
			return this;
		}

		public Criteria andLabelKeyIdIsNull() {
			addCriterion("label_key_id is null");
			return this;
		}

		public Criteria andLabelKeyIdIsNotNull() {
			addCriterion("label_key_id is not null");
			return this;
		}

		public Criteria andLabelKeyIdEqualTo(Integer value) {
			addCriterion("label_key_id =", value, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdNotEqualTo(Integer value) {
			addCriterion("label_key_id <>", value, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdGreaterThan(Integer value) {
			addCriterion("label_key_id >", value, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("label_key_id >=", value, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdLessThan(Integer value) {
			addCriterion("label_key_id <", value, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdLessThanOrEqualTo(Integer value) {
			addCriterion("label_key_id <=", value, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdIn(List values) {
			addCriterion("label_key_id in", values, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdNotIn(List values) {
			addCriterion("label_key_id not in", values, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdBetween(Integer value1, Integer value2) {
			addCriterion("label_key_id between", value1, value2, "labelKeyId");
			return this;
		}

		public Criteria andLabelKeyIdNotBetween(Integer value1, Integer value2) {
			addCriterion("label_key_id not between", value1, value2,
					"labelKeyId");
			return this;
		}

		public Criteria andMerchantIdIsNull() {
			addCriterion("merchant_id is null");
			return this;
		}

		public Criteria andMerchantIdIsNotNull() {
			addCriterion("merchant_id is not null");
			return this;
		}

		public Criteria andMerchantIdEqualTo(Long value) {
			addCriterion("merchant_id =", value, "merchantId");
			return this;
		}

		public Criteria andMerchantIdNotEqualTo(Long value) {
			addCriterion("merchant_id <>", value, "merchantId");
			return this;
		}

		public Criteria andMerchantIdGreaterThan(Long value) {
			addCriterion("merchant_id >", value, "merchantId");
			return this;
		}

		public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
			addCriterion("merchant_id >=", value, "merchantId");
			return this;
		}

		public Criteria andMerchantIdLessThan(Long value) {
			addCriterion("merchant_id <", value, "merchantId");
			return this;
		}

		public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
			addCriterion("merchant_id <=", value, "merchantId");
			return this;
		}

		public Criteria andMerchantIdIn(List values) {
			addCriterion("merchant_id in", values, "merchantId");
			return this;
		}

		public Criteria andMerchantIdNotIn(List values) {
			addCriterion("merchant_id not in", values, "merchantId");
			return this;
		}

		public Criteria andMerchantIdBetween(Long value1, Long value2) {
			addCriterion("merchant_id between", value1, value2, "merchantId");
			return this;
		}

		public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
			addCriterion("merchant_id not between", value1, value2,
					"merchantId");
			return this;
		}

		public Criteria andDateAddedIsNull() {
			addCriterion("date_added is null");
			return this;
		}

		public Criteria andDateAddedIsNotNull() {
			addCriterion("date_added is not null");
			return this;
		}

		public Criteria andDateAddedEqualTo(Date value) {
			addCriterion("date_added =", value, "dateAdded");
			return this;
		}

		public Criteria andDateAddedNotEqualTo(Date value) {
			addCriterion("date_added <>", value, "dateAdded");
			return this;
		}

		public Criteria andDateAddedGreaterThan(Date value) {
			addCriterion("date_added >", value, "dateAdded");
			return this;
		}

		public Criteria andDateAddedGreaterThanOrEqualTo(Date value) {
			addCriterion("date_added >=", value, "dateAdded");
			return this;
		}

		public Criteria andDateAddedLessThan(Date value) {
			addCriterion("date_added <", value, "dateAdded");
			return this;
		}

		public Criteria andDateAddedLessThanOrEqualTo(Date value) {
			addCriterion("date_added <=", value, "dateAdded");
			return this;
		}

		public Criteria andDateAddedIn(List values) {
			addCriterion("date_added in", values, "dateAdded");
			return this;
		}

		public Criteria andDateAddedNotIn(List values) {
			addCriterion("date_added not in", values, "dateAdded");
			return this;
		}

		public Criteria andDateAddedBetween(Date value1, Date value2) {
			addCriterion("date_added between", value1, value2, "dateAdded");
			return this;
		}

		public Criteria andDateAddedNotBetween(Date value1, Date value2) {
			addCriterion("date_added not between", value1, value2, "dateAdded");
			return this;
		}
	}
}