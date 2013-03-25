package com.mobicart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobicart.model.ProductExample.Criteria;

public class ProductOptionExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public ProductOptionExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	protected ProductOptionExample(ProductOptionExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table product_options
	 * @ibatorgenerated  Fri Oct 01 21:21:35 IST 2010
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

		
		public Criteria andSSaleLabelIsNull() {
			addCriterion("s_sale_label is null");
			return this;
		}

		public Criteria andSSaleLabelIsNotNull() {
			addCriterion("s_sale_label is not null");
			return this;
		}

		public Criteria andSSaleLabelEqualTo(String value) {
			addCriterion("s_sale_label =", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelNotEqualTo(String value) {
			addCriterion("s_sale_label <>", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelGreaterThan(String value) {
			addCriterion("s_sale_label >", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelGreaterThanOrEqualTo(String value) {
			addCriterion("s_sale_label >=", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelLessThan(String value) {
			addCriterion("s_sale_label <", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelLessThanOrEqualTo(String value) {
			addCriterion("s_sale_label <=", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelLike(String value) {
			addCriterion("s_sale_label like", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelNotLike(String value) {
			addCriterion("s_sale_label not like", value, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelIn(List values) {
			addCriterion("s_sale_label in", values, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelNotIn(List values) {
			addCriterion("s_sale_label not in", values, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelBetween(String value1, String value2) {
			addCriterion("s_sale_label between", value1, value2, "sSaleLabel");
			return this;
		}

		public Criteria andSSaleLabelNotBetween(String value1, String value2) {
			addCriterion("s_sale_label not between", value1, value2,
					"sSaleLabel");
			return this;
		}
		
		public Criteria andsTitleIsNull() {
			addCriterion("s_title is null");
			return this;
		}

		public Criteria andsTitleIsNotNull() {
			addCriterion("s_title is not null");
			return this;
		}

		public Criteria andsTitleEqualTo(String value) {
			addCriterion("s_title =", value, "sTitle");
			return this;
		}

		public Criteria andsTitleNotEqualTo(String value) {
			addCriterion("s_title <>", value, "sTitle");
			return this;
		}

		public Criteria andsTitleGreaterThan(String value) {
			addCriterion("s_title >", value, "sTitle");
			return this;
		}

		public Criteria andsTitleGreaterThanOrEqualTo(String value) {
			addCriterion("s_title >=", value, "sTitle");
			return this;
		}

		public Criteria andsTitleLessThan(String value) {
			addCriterion("s_title <", value, "sTitle");
			return this;
		}

		public Criteria andsTitleLessThanOrEqualTo(String value) {
			addCriterion("s_title <=", value, "sTitle");
			return this;
		}

		public Criteria andsTitleLike(String value) {
			addCriterion("s_title like", value, "sTitle");
			return this;
		}

		public Criteria andsTitleNotLike(String value) {
			addCriterion("s_title not like", value, "sTitle");
			return this;
		}

		public Criteria andsTitleIn(List values) {
			addCriterion("s_title in", values, "sTitle");
			return this;
		}

		public Criteria andsTitleNotIn(List values) {
			addCriterion("s_title not in", values, "sTitle");
			return this;
		}

		public Criteria andsTitleBetween(String value1, String value2) {
			addCriterion("s_title between", value1, value2, "sTitle");
			return this;
		}

		public Criteria andsTitleNotBetween(String value1, String value2) {
			addCriterion("s_title not between", value1, value2, "sTitle");
			return this;
		}

		
		
		public Criteria andFQuantityIsNull() {
			addCriterion("f_quantity is null");
			return this;
		}

		public Criteria andFQuantityIsNotNull() {
			addCriterion("f_quantity is not null");
			return this;
		}

		public Criteria andFQuantityEqualTo(BigDecimal value) {
			addCriterion("f_quantity =", value, "fQuantity");
			return this;
		}

		public Criteria andFQuantityNotEqualTo(BigDecimal value) {
			addCriterion("f_quantity <>", value, "fQuantity");
			return this;
		}

		public Criteria andFQuantityGreaterThan(BigDecimal value) {
			addCriterion("f_quantity >", value, "fQuantity");
			return this;
		}

		public Criteria andFQuantityGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("f_quantity >=", value, "fQuantity");
			return this;
		}

		public Criteria andFQuantityLessThan(BigDecimal value) {
			addCriterion("f_quantity <", value, "fQuantity");
			return this;
		}

		public Criteria andFQuantityLessThanOrEqualTo(BigDecimal value) {
			addCriterion("f_quantity <=", value, "fQuantity");
			return this;
		}

		public Criteria andFQuantityIn(List values) {
			addCriterion("f_quantity in", values, "fQuantity");
			return this;
		}

		public Criteria andFQuantityNotIn(List values) {
			addCriterion("f_quantity not in", values, "fQuantity");
			return this;
		}

		public Criteria andFQuantityBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("f_quantity between", value1, value2, "fQuantity");
			return this;
		}

		public Criteria andFQuantityNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("f_quantity not between", value1, value2, "fQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityIsNull() {
			addCriterion("i_available_quantity is null");
			return this;
		}

		public Criteria andIAvailableQuantityIsNotNull() {
			addCriterion("i_available_quantity is not null");
			return this;
		}

		public Criteria andIAvailableQuantityEqualTo(Integer value) {
			addCriterion("i_available_quantity =", value, "iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityNotEqualTo(Integer value) {
			addCriterion("i_available_quantity <>", value, "iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityGreaterThan(Integer value) {
			addCriterion("i_available_quantity >", value, "iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityGreaterThanOrEqualTo(Integer value) {
			addCriterion("i_available_quantity >=", value, "iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityLessThan(Integer value) {
			addCriterion("i_available_quantity <", value, "iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityLessThanOrEqualTo(Integer value) {
			addCriterion("i_available_quantity <=", value, "iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityIn(List values) {
			addCriterion("i_available_quantity in", values,
					"iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityNotIn(List values) {
			addCriterion("i_available_quantity not in", values,
					"iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityBetween(Integer value1,
				Integer value2) {
			addCriterion("i_available_quantity between", value1, value2,
					"iAvailableQuantity");
			return this;
		}

		public Criteria andIAvailableQuantityNotBetween(Integer value1,
				Integer value2) {
			addCriterion("i_available_quantity not between", value1, value2,
					"iAvailableQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityIsNull() {
			addCriterion("i_to_be_shipped_quantity is null");
			return this;
		}

		public Criteria andIToBeShippedQuantityIsNotNull() {
			addCriterion("i_to_be_shipped_quantity is not null");
			return this;
		}

		public Criteria andIToBeShippedQuantityEqualTo(Integer value) {
			addCriterion("i_to_be_shipped_quantity =", value,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityNotEqualTo(Integer value) {
			addCriterion("i_to_be_shipped_quantity <>", value,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityGreaterThan(Integer value) {
			addCriterion("i_to_be_shipped_quantity >", value,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityGreaterThanOrEqualTo(
				Integer value) {
			addCriterion("i_to_be_shipped_quantity >=", value,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityLessThan(Integer value) {
			addCriterion("i_to_be_shipped_quantity <", value,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityLessThanOrEqualTo(Integer value) {
			addCriterion("i_to_be_shipped_quantity <=", value,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityIn(List values) {
			addCriterion("i_to_be_shipped_quantity in", values,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityNotIn(List values) {
			addCriterion("i_to_be_shipped_quantity not in", values,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityBetween(Integer value1,
				Integer value2) {
			addCriterion("i_to_be_shipped_quantity between", value1, value2,
					"iToBeShippedQuantity");
			return this;
		}

		public Criteria andIToBeShippedQuantityNotBetween(Integer value1,
				Integer value2) {
			addCriterion("i_to_be_shipped_quantity not between", value1,
					value2, "iToBeShippedQuantity");
			return this;
		}
	}
}