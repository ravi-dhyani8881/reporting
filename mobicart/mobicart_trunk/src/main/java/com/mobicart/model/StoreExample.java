package com.mobicart.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public StoreExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	protected StoreExample(StoreExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table stores
	 * @ibatorgenerated  Fri Dec 24 11:46:45 IST 2010
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

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return this;
		}

		public Criteria andUserIdEqualTo(Long value) {
			addCriterion("user_id =", value, "userId");
			return this;
		}

		public Criteria andUserIdNotEqualTo(Long value) {
			addCriterion("user_id <>", value, "userId");
			return this;
		}

		public Criteria andUserIdGreaterThan(Long value) {
			addCriterion("user_id >", value, "userId");
			return this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("user_id >=", value, "userId");
			return this;
		}

		public Criteria andUserIdLessThan(Long value) {
			addCriterion("user_id <", value, "userId");
			return this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Long value) {
			addCriterion("user_id <=", value, "userId");
			return this;
		}

		public Criteria andUserIdIn(List values) {
			addCriterion("user_id in", values, "userId");
			return this;
		}

		public Criteria andUserIdNotIn(List values) {
			addCriterion("user_id not in", values, "userId");
			return this;
		}

		public Criteria andUserIdBetween(Long value1, Long value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return this;
		}

		public Criteria andUserIdNotBetween(Long value1, Long value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return this;
		}

		public Criteria andSNameIsNull() {
			addCriterion("s_name is null");
			return this;
		}

		public Criteria andSNameIsNotNull() {
			addCriterion("s_name is not null");
			return this;
		}

		public Criteria andSNameEqualTo(String value) {
			addCriterion("s_name =", value, "sName");
			return this;
		}

		public Criteria andSNameNotEqualTo(String value) {
			addCriterion("s_name <>", value, "sName");
			return this;
		}

		public Criteria andSNameGreaterThan(String value) {
			addCriterion("s_name >", value, "sName");
			return this;
		}

		public Criteria andSNameGreaterThanOrEqualTo(String value) {
			addCriterion("s_name >=", value, "sName");
			return this;
		}

		public Criteria andSNameLessThan(String value) {
			addCriterion("s_name <", value, "sName");
			return this;
		}

		public Criteria andSNameLessThanOrEqualTo(String value) {
			addCriterion("s_name <=", value, "sName");
			return this;
		}

		public Criteria andSNameLike(String value) {
			addCriterion("s_name like", value, "sName");
			return this;
		}

		public Criteria andSNameNotLike(String value) {
			addCriterion("s_name not like", value, "sName");
			return this;
		}

		public Criteria andSNameIn(List values) {
			addCriterion("s_name in", values, "sName");
			return this;
		}

		public Criteria andSNameNotIn(List values) {
			addCriterion("s_name not in", values, "sName");
			return this;
		}

		public Criteria andSNameBetween(String value1, String value2) {
			addCriterion("s_name between", value1, value2, "sName");
			return this;
		}

		public Criteria andSNameNotBetween(String value1, String value2) {
			addCriterion("s_name not between", value1, value2, "sName");
			return this;
		}

		public Criteria andSDescriptionIsNull() {
			addCriterion("s_description is null");
			return this;
		}

		public Criteria andSDescriptionIsNotNull() {
			addCriterion("s_description is not null");
			return this;
		}

		public Criteria andSDescriptionEqualTo(String value) {
			addCriterion("s_description =", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionNotEqualTo(String value) {
			addCriterion("s_description <>", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionGreaterThan(String value) {
			addCriterion("s_description >", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("s_description >=", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionLessThan(String value) {
			addCriterion("s_description <", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionLessThanOrEqualTo(String value) {
			addCriterion("s_description <=", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionLike(String value) {
			addCriterion("s_description like", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionNotLike(String value) {
			addCriterion("s_description not like", value, "sDescription");
			return this;
		}

		public Criteria andSDescriptionIn(List values) {
			addCriterion("s_description in", values, "sDescription");
			return this;
		}

		public Criteria andSDescriptionNotIn(List values) {
			addCriterion("s_description not in", values, "sDescription");
			return this;
		}

		public Criteria andSDescriptionBetween(String value1, String value2) {
			addCriterion("s_description between", value1, value2,
					"sDescription");
			return this;
		}

		public Criteria andSDescriptionNotBetween(String value1, String value2) {
			addCriterion("s_description not between", value1, value2,
					"sDescription");
			return this;
		}

		public Criteria andSOrderEmailIsNull() {
			addCriterion("s_order_email is null");
			return this;
		}

		public Criteria andSOrderEmailIsNotNull() {
			addCriterion("s_order_email is not null");
			return this;
		}

		public Criteria andSOrderEmailEqualTo(String value) {
			addCriterion("s_order_email =", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailNotEqualTo(String value) {
			addCriterion("s_order_email <>", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailGreaterThan(String value) {
			addCriterion("s_order_email >", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailGreaterThanOrEqualTo(String value) {
			addCriterion("s_order_email >=", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailLessThan(String value) {
			addCriterion("s_order_email <", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailLessThanOrEqualTo(String value) {
			addCriterion("s_order_email <=", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailLike(String value) {
			addCriterion("s_order_email like", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailNotLike(String value) {
			addCriterion("s_order_email not like", value, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailIn(List values) {
			addCriterion("s_order_email in", values, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailNotIn(List values) {
			addCriterion("s_order_email not in", values, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailBetween(String value1, String value2) {
			addCriterion("s_order_email between", value1, value2, "sOrderEmail");
			return this;
		}

		public Criteria andSOrderEmailNotBetween(String value1, String value2) {
			addCriterion("s_order_email not between", value1, value2,
					"sOrderEmail");
			return this;
		}

		public Criteria andSPaypalEmailIsNull() {
			addCriterion("s_paypal_email is null");
			return this;
		}

		public Criteria andSPaypalEmailIsNotNull() {
			addCriterion("s_paypal_email is not null");
			return this;
		}

		public Criteria andSPaypalEmailEqualTo(String value) {
			addCriterion("s_paypal_email =", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailNotEqualTo(String value) {
			addCriterion("s_paypal_email <>", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailGreaterThan(String value) {
			addCriterion("s_paypal_email >", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailGreaterThanOrEqualTo(String value) {
			addCriterion("s_paypal_email >=", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailLessThan(String value) {
			addCriterion("s_paypal_email <", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailLessThanOrEqualTo(String value) {
			addCriterion("s_paypal_email <=", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailLike(String value) {
			addCriterion("s_paypal_email like", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailNotLike(String value) {
			addCriterion("s_paypal_email not like", value, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailIn(List values) {
			addCriterion("s_paypal_email in", values, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailNotIn(List values) {
			addCriterion("s_paypal_email not in", values, "sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailBetween(String value1, String value2) {
			addCriterion("s_paypal_email between", value1, value2,
					"sPaypalEmail");
			return this;
		}

		public Criteria andSPaypalEmailNotBetween(String value1, String value2) {
			addCriterion("s_paypal_email not between", value1, value2,
					"sPaypalEmail");
			return this;
		}

		public Criteria andSCurrencyIsNull() {
			addCriterion("s_currency is null");
			return this;
		}

		public Criteria andSCurrencyIsNotNull() {
			addCriterion("s_currency is not null");
			return this;
		}

		public Criteria andSCurrencyEqualTo(String value) {
			addCriterion("s_currency =", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyNotEqualTo(String value) {
			addCriterion("s_currency <>", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyGreaterThan(String value) {
			addCriterion("s_currency >", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyGreaterThanOrEqualTo(String value) {
			addCriterion("s_currency >=", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyLessThan(String value) {
			addCriterion("s_currency <", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyLessThanOrEqualTo(String value) {
			addCriterion("s_currency <=", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyLike(String value) {
			addCriterion("s_currency like", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyNotLike(String value) {
			addCriterion("s_currency not like", value, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyIn(List values) {
			addCriterion("s_currency in", values, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyNotIn(List values) {
			addCriterion("s_currency not in", values, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyBetween(String value1, String value2) {
			addCriterion("s_currency between", value1, value2, "sCurrency");
			return this;
		}

		public Criteria andSCurrencyNotBetween(String value1, String value2) {
			addCriterion("s_currency not between", value1, value2, "sCurrency");
			return this;
		}

		public Criteria andDCreatedOnIsNull() {
			addCriterion("d_created_on is null");
			return this;
		}

		public Criteria andDCreatedOnIsNotNull() {
			addCriterion("d_created_on is not null");
			return this;
		}

		public Criteria andDCreatedOnEqualTo(Date value) {
			addCriterion("d_created_on =", value, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnNotEqualTo(Date value) {
			addCriterion("d_created_on <>", value, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnGreaterThan(Date value) {
			addCriterion("d_created_on >", value, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnGreaterThanOrEqualTo(Date value) {
			addCriterion("d_created_on >=", value, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnLessThan(Date value) {
			addCriterion("d_created_on <", value, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnLessThanOrEqualTo(Date value) {
			addCriterion("d_created_on <=", value, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnIn(List values) {
			addCriterion("d_created_on in", values, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnNotIn(List values) {
			addCriterion("d_created_on not in", values, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnBetween(Date value1, Date value2) {
			addCriterion("d_created_on between", value1, value2, "dCreatedOn");
			return this;
		}

		public Criteria andDCreatedOnNotBetween(Date value1, Date value2) {
			addCriterion("d_created_on not between", value1, value2,
					"dCreatedOn");
			return this;
		}

		public Criteria andDUpdatedOnIsNull() {
			addCriterion("d_updated_on is null");
			return this;
		}

		public Criteria andDUpdatedOnIsNotNull() {
			addCriterion("d_updated_on is not null");
			return this;
		}

		public Criteria andDUpdatedOnEqualTo(Date value) {
			addCriterion("d_updated_on =", value, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnNotEqualTo(Date value) {
			addCriterion("d_updated_on <>", value, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnGreaterThan(Date value) {
			addCriterion("d_updated_on >", value, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnGreaterThanOrEqualTo(Date value) {
			addCriterion("d_updated_on >=", value, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnLessThan(Date value) {
			addCriterion("d_updated_on <", value, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnLessThanOrEqualTo(Date value) {
			addCriterion("d_updated_on <=", value, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnIn(List values) {
			addCriterion("d_updated_on in", values, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnNotIn(List values) {
			addCriterion("d_updated_on not in", values, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnBetween(Date value1, Date value2) {
			addCriterion("d_updated_on between", value1, value2, "dUpdatedOn");
			return this;
		}

		public Criteria andDUpdatedOnNotBetween(Date value1, Date value2) {
			addCriterion("d_updated_on not between", value1, value2,
					"dUpdatedOn");
			return this;
		}

		public Criteria andBIncludeTaxIsNull() {
			addCriterion("b_include_tax is null");
			return this;
		}

		public Criteria andBIncludeTaxIsNotNull() {
			addCriterion("b_include_tax is not null");
			return this;
		}

		public Criteria andBIncludeTaxEqualTo(Boolean value) {
			addCriterion("b_include_tax =", value, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxNotEqualTo(Boolean value) {
			addCriterion("b_include_tax <>", value, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxGreaterThan(Boolean value) {
			addCriterion("b_include_tax >", value, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxGreaterThanOrEqualTo(Boolean value) {
			addCriterion("b_include_tax >=", value, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxLessThan(Boolean value) {
			addCriterion("b_include_tax <", value, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxLessThanOrEqualTo(Boolean value) {
			addCriterion("b_include_tax <=", value, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxIn(List values) {
			addCriterion("b_include_tax in", values, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxNotIn(List values) {
			addCriterion("b_include_tax not in", values, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxBetween(Boolean value1, Boolean value2) {
			addCriterion("b_include_tax between", value1, value2, "bIncludeTax");
			return this;
		}

		public Criteria andBIncludeTaxNotBetween(Boolean value1, Boolean value2) {
			addCriterion("b_include_tax not between", value1, value2,
					"bIncludeTax");
			return this;
		}

		public Criteria andBTaxShippingIsNull() {
			addCriterion("b_tax_shipping is null");
			return this;
		}

		public Criteria andBTaxShippingIsNotNull() {
			addCriterion("b_tax_shipping is not null");
			return this;
		}

		public Criteria andBTaxShippingEqualTo(Boolean value) {
			addCriterion("b_tax_shipping =", value, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingNotEqualTo(Boolean value) {
			addCriterion("b_tax_shipping <>", value, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingGreaterThan(Boolean value) {
			addCriterion("b_tax_shipping >", value, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingGreaterThanOrEqualTo(Boolean value) {
			addCriterion("b_tax_shipping >=", value, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingLessThan(Boolean value) {
			addCriterion("b_tax_shipping <", value, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingLessThanOrEqualTo(Boolean value) {
			addCriterion("b_tax_shipping <=", value, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingIn(List values) {
			addCriterion("b_tax_shipping in", values, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingNotIn(List values) {
			addCriterion("b_tax_shipping not in", values, "bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingBetween(Boolean value1, Boolean value2) {
			addCriterion("b_tax_shipping between", value1, value2,
					"bTaxShipping");
			return this;
		}

		public Criteria andBTaxShippingNotBetween(Boolean value1, Boolean value2) {
			addCriterion("b_tax_shipping not between", value1, value2,
					"bTaxShipping");
			return this;
		}
		
		public Criteria andBEnabledIsNull() {
			addCriterion("b_enabled is null");
			return this;
		}

		public Criteria andBEnabledIsNotNull() {
			addCriterion("b_enabled is not null");
			return this;
		}

		public Criteria andBEnabledEqualTo(Boolean value) {
			addCriterion("b_enabled =", value, "bEnabled");
			return this;
		}

		public Criteria andBEnabledNotEqualTo(Boolean value) {
			addCriterion("b_enabled <>", value, "bEnabled");
			return this;
		}

		public Criteria andBEnabledGreaterThan(Boolean value) {
			addCriterion("b_enabled >", value, "bEnabled");
			return this;
		}

		public Criteria andBEnabledGreaterThanOrEqualTo(Boolean value) {
			addCriterion("b_enabled >=", value, "bEnabled");
			return this;
		}

		public Criteria andBEnabledLessThan(Boolean value) {
			addCriterion("b_enabled <", value, "bEnabled");
			return this;
		}

		public Criteria andBEnabledLessThanOrEqualTo(Boolean value) {
			addCriterion("b_enabled <=", value, "bEnabled");
			return this;
		}

		public Criteria andBEnabledIn(List values) {
			addCriterion("b_enabled in", values, "bEnabled");
			return this;
		}

		public Criteria andBEnabledNotIn(List values) {
			addCriterion("b_enabled not in", values, "bEnabled");
			return this;
		}

		public Criteria andBEnabledBetween(Boolean value1, Boolean value2) {
			addCriterion("b_enabled between", value1, value2, "bEnabled");
			return this;
		}

		public Criteria andBEnabledNotBetween(Boolean value1, Boolean value2) {
			addCriterion("b_enabled not between", value1, value2, "bEnabled");
			return this;
		}
	}
}