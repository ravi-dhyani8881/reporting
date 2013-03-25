package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabelKeysExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public LabelKeysExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected LabelKeysExample(LabelKeysExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
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
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table label_keys
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

		public Criteria andLabelKeyIsNull() {
			addCriterion("label_key is null");
			return this;
		}

		public Criteria andLabelKeyIsNotNull() {
			addCriterion("label_key is not null");
			return this;
		}

		public Criteria andLabelKeyEqualTo(String value) {
			addCriterion("label_key =", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyNotEqualTo(String value) {
			addCriterion("label_key <>", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyGreaterThan(String value) {
			addCriterion("label_key >", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyGreaterThanOrEqualTo(String value) {
			addCriterion("label_key >=", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyLessThan(String value) {
			addCriterion("label_key <", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyLessThanOrEqualTo(String value) {
			addCriterion("label_key <=", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyLike(String value) {
			addCriterion("label_key like", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyNotLike(String value) {
			addCriterion("label_key not like", value, "labelKey");
			return this;
		}

		public Criteria andLabelKeyIn(List values) {
			addCriterion("label_key in", values, "labelKey");
			return this;
		}

		public Criteria andLabelKeyNotIn(List values) {
			addCriterion("label_key not in", values, "labelKey");
			return this;
		}

		public Criteria andLabelKeyBetween(String value1, String value2) {
			addCriterion("label_key between", value1, value2, "labelKey");
			return this;
		}

		public Criteria andLabelKeyNotBetween(String value1, String value2) {
			addCriterion("label_key not between", value1, value2, "labelKey");
			return this;
		}

		public Criteria andLabelValueIsNull() {
			addCriterion("label_value is null");
			return this;
		}

		public Criteria andLabelValueIsNotNull() {
			addCriterion("label_value is not null");
			return this;
		}

		public Criteria andLabelValueEqualTo(String value) {
			addCriterion("label_value =", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueNotEqualTo(String value) {
			addCriterion("label_value <>", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueGreaterThan(String value) {
			addCriterion("label_value >", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueGreaterThanOrEqualTo(String value) {
			addCriterion("label_value >=", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueLessThan(String value) {
			addCriterion("label_value <", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueLessThanOrEqualTo(String value) {
			addCriterion("label_value <=", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueLike(String value) {
			addCriterion("label_value like", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueNotLike(String value) {
			addCriterion("label_value not like", value, "labelValue");
			return this;
		}

		public Criteria andLabelValueIn(List values) {
			addCriterion("label_value in", values, "labelValue");
			return this;
		}

		public Criteria andLabelValueNotIn(List values) {
			addCriterion("label_value not in", values, "labelValue");
			return this;
		}

		public Criteria andLabelValueBetween(String value1, String value2) {
			addCriterion("label_value between", value1, value2, "labelValue");
			return this;
		}

		public Criteria andLabelValueNotBetween(String value1, String value2) {
			addCriterion("label_value not between", value1, value2,
					"labelValue");
			return this;
		}

		public Criteria andTabTypeIsNull() {
			addCriterion("tab_type is null");
			return this;
		}

		public Criteria andTabTypeIsNotNull() {
			addCriterion("tab_type is not null");
			return this;
		}

		public Criteria andTabTypeEqualTo(String value) {
			addCriterion("tab_type =", value, "tabType");
			return this;
		}

		public Criteria andTabTypeNotEqualTo(String value) {
			addCriterion("tab_type <>", value, "tabType");
			return this;
		}

		public Criteria andTabTypeGreaterThan(String value) {
			addCriterion("tab_type >", value, "tabType");
			return this;
		}

		public Criteria andTabTypeGreaterThanOrEqualTo(String value) {
			addCriterion("tab_type >=", value, "tabType");
			return this;
		}

		public Criteria andTabTypeLessThan(String value) {
			addCriterion("tab_type <", value, "tabType");
			return this;
		}

		public Criteria andTabTypeLessThanOrEqualTo(String value) {
			addCriterion("tab_type <=", value, "tabType");
			return this;
		}

		public Criteria andTabTypeLike(String value) {
			addCriterion("tab_type like", value, "tabType");
			return this;
		}

		public Criteria andTabTypeNotLike(String value) {
			addCriterion("tab_type not like", value, "tabType");
			return this;
		}

		public Criteria andTabTypeIn(List values) {
			addCriterion("tab_type in", values, "tabType");
			return this;
		}

		public Criteria andTabTypeNotIn(List values) {
			addCriterion("tab_type not in", values, "tabType");
			return this;
		}

		public Criteria andTabTypeBetween(String value1, String value2) {
			addCriterion("tab_type between", value1, value2, "tabType");
			return this;
		}

		public Criteria andTabTypeNotBetween(String value1, String value2) {
			addCriterion("tab_type not between", value1, value2, "tabType");
			return this;
		}
	}
}