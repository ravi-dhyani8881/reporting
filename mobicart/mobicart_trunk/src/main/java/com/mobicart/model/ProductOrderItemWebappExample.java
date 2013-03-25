package com.mobicart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOrderItemWebappExample {
    /**
     
     * This field corresponds to the database table product_order_items_webapp
     *
     * 
     */
    protected String orderByClause;

    /**
     * 
     * This field corresponds to the database table product_order_items_webapp
     *
     * 
     */
    protected List oredCriteria;

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public ProductOrderItemWebappExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    protected ProductOrderItemWebappExample(ProductOrderItemWebappExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 
     * This method corresponds to the database table product_order_items_webapp
     *
     * 
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * 
     * This class corresponds to the database table product_order_items_webapp
     *
     * 
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return this;
        }

        public Criteria andOrderIdIn(List values) {
            addCriterion("order_id in", values, "orderId");
            return this;
        }

        public Criteria andOrderIdNotIn(List values) {
            addCriterion("order_id not in", values, "orderId");
            return this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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

        public Criteria andProductOptionIdIsNull() {
            addCriterion("product_option_id is null");
            return this;
        }

        public Criteria andProductOptionIdIsNotNull() {
            addCriterion("product_option_id is not null");
            return this;
        }

        public Criteria andProductOptionIdEqualTo(Long value) {
            addCriterion("product_option_id =", value, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdNotEqualTo(Long value) {
            addCriterion("product_option_id <>", value, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdGreaterThan(Long value) {
            addCriterion("product_option_id >", value, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_option_id >=", value, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdLessThan(Long value) {
            addCriterion("product_option_id <", value, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdLessThanOrEqualTo(Long value) {
            addCriterion("product_option_id <=", value, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdIn(List values) {
            addCriterion("product_option_id in", values, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdNotIn(List values) {
            addCriterion("product_option_id not in", values, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdBetween(Long value1, Long value2) {
            addCriterion("product_option_id between", value1, value2, "productOptionId");
            return this;
        }

        public Criteria andProductOptionIdNotBetween(Long value1, Long value2) {
            addCriterion("product_option_id not between", value1, value2, "productOptionId");
            return this;
        }

        public Criteria andIQuantityIsNull() {
            addCriterion("i_quantity is null");
            return this;
        }

        public Criteria andIQuantityIsNotNull() {
            addCriterion("i_quantity is not null");
            return this;
        }

        public Criteria andIQuantityEqualTo(Integer value) {
            addCriterion("i_quantity =", value, "iQuantity");
            return this;
        }

        public Criteria andIQuantityNotEqualTo(Integer value) {
            addCriterion("i_quantity <>", value, "iQuantity");
            return this;
        }

        public Criteria andIQuantityGreaterThan(Integer value) {
            addCriterion("i_quantity >", value, "iQuantity");
            return this;
        }

        public Criteria andIQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("i_quantity >=", value, "iQuantity");
            return this;
        }

        public Criteria andIQuantityLessThan(Integer value) {
            addCriterion("i_quantity <", value, "iQuantity");
            return this;
        }

        public Criteria andIQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("i_quantity <=", value, "iQuantity");
            return this;
        }

        public Criteria andIQuantityIn(List values) {
            addCriterion("i_quantity in", values, "iQuantity");
            return this;
        }

        public Criteria andIQuantityNotIn(List values) {
            addCriterion("i_quantity not in", values, "iQuantity");
            return this;
        }

        public Criteria andIQuantityBetween(Integer value1, Integer value2) {
            addCriterion("i_quantity between", value1, value2, "iQuantity");
            return this;
        }

        public Criteria andIQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("i_quantity not between", value1, value2, "iQuantity");
            return this;
        }

        public Criteria andSOrderSourceIsNull() {
            addCriterion("s_order_source is null");
            return this;
        }

        public Criteria andSOrderSourceIsNotNull() {
            addCriterion("s_order_source is not null");
            return this;
        }

        public Criteria andSOrderSourceEqualTo(String value) {
            addCriterion("s_order_source =", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceNotEqualTo(String value) {
            addCriterion("s_order_source <>", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceGreaterThan(String value) {
            addCriterion("s_order_source >", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceGreaterThanOrEqualTo(String value) {
            addCriterion("s_order_source >=", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceLessThan(String value) {
            addCriterion("s_order_source <", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceLessThanOrEqualTo(String value) {
            addCriterion("s_order_source <=", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceLike(String value) {
            addCriterion("s_order_source like", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceNotLike(String value) {
            addCriterion("s_order_source not like", value, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceIn(List values) {
            addCriterion("s_order_source in", values, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceNotIn(List values) {
            addCriterion("s_order_source not in", values, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceBetween(String value1, String value2) {
            addCriterion("s_order_source between", value1, value2, "sOrderSource");
            return this;
        }

        public Criteria andSOrderSourceNotBetween(String value1, String value2) {
            addCriterion("s_order_source not between", value1, value2, "sOrderSource");
            return this;
        }

        public Criteria andFAmountIsNull() {
            addCriterion("f_amount is null");
            return this;
        }

        public Criteria andFAmountIsNotNull() {
            addCriterion("f_amount is not null");
            return this;
        }

        public Criteria andFAmountEqualTo(BigDecimal value) {
            addCriterion("f_amount =", value, "fAmount");
            return this;
        }

        public Criteria andFAmountNotEqualTo(BigDecimal value) {
            addCriterion("f_amount <>", value, "fAmount");
            return this;
        }

        public Criteria andFAmountGreaterThan(BigDecimal value) {
            addCriterion("f_amount >", value, "fAmount");
            return this;
        }

        public Criteria andFAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("f_amount >=", value, "fAmount");
            return this;
        }

        public Criteria andFAmountLessThan(BigDecimal value) {
            addCriterion("f_amount <", value, "fAmount");
            return this;
        }

        public Criteria andFAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("f_amount <=", value, "fAmount");
            return this;
        }

        public Criteria andFAmountIn(List values) {
            addCriterion("f_amount in", values, "fAmount");
            return this;
        }

        public Criteria andFAmountNotIn(List values) {
            addCriterion("f_amount not in", values, "fAmount");
            return this;
        }

        public Criteria andFAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_amount between", value1, value2, "fAmount");
            return this;
        }

        public Criteria andFAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_amount not between", value1, value2, "fAmount");
            return this;
        }
    }
}