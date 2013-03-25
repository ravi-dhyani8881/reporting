package com.mobicart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOrderWebappExample {
    /**

     * This field corresponds to the database table product_orders_webapp
     *
     *
     */
    protected String orderByClause;

    /**

     * This field corresponds to the database table product_orders_webapp
     *
     *
     */
    protected List oredCriteria;

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    public ProductOrderWebappExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    protected ProductOrderWebappExample(ProductOrderWebappExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
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
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 
     * This method corresponds to the database table product_orders_webapp
     *
     *
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     *
     * This class corresponds to the database table product_orders_webapp
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
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return this;
        }

        public Criteria andStoreIdIn(List values) {
            addCriterion("store_id in", values, "storeId");
            return this;
        }

        public Criteria andStoreIdNotIn(List values) {
            addCriterion("store_id not in", values, "storeId");
            return this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return this;
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

        public Criteria andSMerchantPaypalEmailIsNull() {
            addCriterion("s_merchant_paypal_email is null");
            return this;
        }

        public Criteria andSMerchantPaypalEmailIsNotNull() {
            addCriterion("s_merchant_paypal_email is not null");
            return this;
        }

        public Criteria andSMerchantPaypalEmailEqualTo(String value) {
            addCriterion("s_merchant_paypal_email =", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailNotEqualTo(String value) {
            addCriterion("s_merchant_paypal_email <>", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailGreaterThan(String value) {
            addCriterion("s_merchant_paypal_email >", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailGreaterThanOrEqualTo(String value) {
            addCriterion("s_merchant_paypal_email >=", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailLessThan(String value) {
            addCriterion("s_merchant_paypal_email <", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailLessThanOrEqualTo(String value) {
            addCriterion("s_merchant_paypal_email <=", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailLike(String value) {
            addCriterion("s_merchant_paypal_email like", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailNotLike(String value) {
            addCriterion("s_merchant_paypal_email not like", value, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailIn(List values) {
            addCriterion("s_merchant_paypal_email in", values, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailNotIn(List values) {
            addCriterion("s_merchant_paypal_email not in", values, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailBetween(String value1, String value2) {
            addCriterion("s_merchant_paypal_email between", value1, value2, "sMerchantPaypalEmail");
            return this;
        }

        public Criteria andSMerchantPaypalEmailNotBetween(String value1, String value2) {
            addCriterion("s_merchant_paypal_email not between", value1, value2, "sMerchantPaypalEmail");
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

        public Criteria andFShippingAmountIsNull() {
            addCriterion("f_shipping_amount is null");
            return this;
        }

        public Criteria andFShippingAmountIsNotNull() {
            addCriterion("f_shipping_amount is not null");
            return this;
        }

        public Criteria andFShippingAmountEqualTo(BigDecimal value) {
            addCriterion("f_shipping_amount =", value, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountNotEqualTo(BigDecimal value) {
            addCriterion("f_shipping_amount <>", value, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountGreaterThan(BigDecimal value) {
            addCriterion("f_shipping_amount >", value, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("f_shipping_amount >=", value, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountLessThan(BigDecimal value) {
            addCriterion("f_shipping_amount <", value, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("f_shipping_amount <=", value, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountIn(List values) {
            addCriterion("f_shipping_amount in", values, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountNotIn(List values) {
            addCriterion("f_shipping_amount not in", values, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_shipping_amount between", value1, value2, "fShippingAmount");
            return this;
        }

        public Criteria andFShippingAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_shipping_amount not between", value1, value2, "fShippingAmount");
            return this;
        }

        public Criteria andFTaxAmountIsNull() {
            addCriterion("f_tax_amount is null");
            return this;
        }

        public Criteria andFTaxAmountIsNotNull() {
            addCriterion("f_tax_amount is not null");
            return this;
        }

        public Criteria andFTaxAmountEqualTo(BigDecimal value) {
            addCriterion("f_tax_amount =", value, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountNotEqualTo(BigDecimal value) {
            addCriterion("f_tax_amount <>", value, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountGreaterThan(BigDecimal value) {
            addCriterion("f_tax_amount >", value, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("f_tax_amount >=", value, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountLessThan(BigDecimal value) {
            addCriterion("f_tax_amount <", value, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("f_tax_amount <=", value, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountIn(List values) {
            addCriterion("f_tax_amount in", values, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountNotIn(List values) {
            addCriterion("f_tax_amount not in", values, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_tax_amount between", value1, value2, "fTaxAmount");
            return this;
        }

        public Criteria andFTaxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_tax_amount not between", value1, value2, "fTaxAmount");
            return this;
        }

        public Criteria andFTotalAmountIsNull() {
            addCriterion("f_total_amount is null");
            return this;
        }

        public Criteria andFTotalAmountIsNotNull() {
            addCriterion("f_total_amount is not null");
            return this;
        }

        public Criteria andFTotalAmountEqualTo(BigDecimal value) {
            addCriterion("f_total_amount =", value, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("f_total_amount <>", value, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("f_total_amount >", value, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("f_total_amount >=", value, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountLessThan(BigDecimal value) {
            addCriterion("f_total_amount <", value, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("f_total_amount <=", value, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountIn(List values) {
            addCriterion("f_total_amount in", values, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountNotIn(List values) {
            addCriterion("f_total_amount not in", values, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_total_amount between", value1, value2, "fTotalAmount");
            return this;
        }

        public Criteria andFTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("f_total_amount not between", value1, value2, "fTotalAmount");
            return this;
        }

        public Criteria andSBuyerNameIsNull() {
            addCriterion("s_buyer_name is null");
            return this;
        }

        public Criteria andSBuyerNameIsNotNull() {
            addCriterion("s_buyer_name is not null");
            return this;
        }

        public Criteria andSBuyerNameEqualTo(String value) {
            addCriterion("s_buyer_name =", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameNotEqualTo(String value) {
            addCriterion("s_buyer_name <>", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameGreaterThan(String value) {
            addCriterion("s_buyer_name >", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameGreaterThanOrEqualTo(String value) {
            addCriterion("s_buyer_name >=", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameLessThan(String value) {
            addCriterion("s_buyer_name <", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameLessThanOrEqualTo(String value) {
            addCriterion("s_buyer_name <=", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameLike(String value) {
            addCriterion("s_buyer_name like", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameNotLike(String value) {
            addCriterion("s_buyer_name not like", value, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameIn(List values) {
            addCriterion("s_buyer_name in", values, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameNotIn(List values) {
            addCriterion("s_buyer_name not in", values, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameBetween(String value1, String value2) {
            addCriterion("s_buyer_name between", value1, value2, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerNameNotBetween(String value1, String value2) {
            addCriterion("s_buyer_name not between", value1, value2, "sBuyerName");
            return this;
        }

        public Criteria andSBuyerEmailIsNull() {
            addCriterion("s_buyer_email is null");
            return this;
        }

        public Criteria andSBuyerEmailIsNotNull() {
            addCriterion("s_buyer_email is not null");
            return this;
        }

        public Criteria andSBuyerEmailEqualTo(String value) {
            addCriterion("s_buyer_email =", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailNotEqualTo(String value) {
            addCriterion("s_buyer_email <>", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailGreaterThan(String value) {
            addCriterion("s_buyer_email >", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("s_buyer_email >=", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailLessThan(String value) {
            addCriterion("s_buyer_email <", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailLessThanOrEqualTo(String value) {
            addCriterion("s_buyer_email <=", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailLike(String value) {
            addCriterion("s_buyer_email like", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailNotLike(String value) {
            addCriterion("s_buyer_email not like", value, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailIn(List values) {
            addCriterion("s_buyer_email in", values, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailNotIn(List values) {
            addCriterion("s_buyer_email not in", values, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailBetween(String value1, String value2) {
            addCriterion("s_buyer_email between", value1, value2, "sBuyerEmail");
            return this;
        }

        public Criteria andSBuyerEmailNotBetween(String value1, String value2) {
            addCriterion("s_buyer_email not between", value1, value2, "sBuyerEmail");
            return this;
        }

        public Criteria andIBuyerPhoneIsNull() {
            addCriterion("i_buyer_phone is null");
            return this;
        }

        public Criteria andIBuyerPhoneIsNotNull() {
            addCriterion("i_buyer_phone is not null");
            return this;
        }

        public Criteria andIBuyerPhoneEqualTo(Long value) {
            addCriterion("i_buyer_phone =", value, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneNotEqualTo(Long value) {
            addCriterion("i_buyer_phone <>", value, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneGreaterThan(Long value) {
            addCriterion("i_buyer_phone >", value, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneGreaterThanOrEqualTo(Long value) {
            addCriterion("i_buyer_phone >=", value, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneLessThan(Long value) {
            addCriterion("i_buyer_phone <", value, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneLessThanOrEqualTo(Long value) {
            addCriterion("i_buyer_phone <=", value, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneIn(List values) {
            addCriterion("i_buyer_phone in", values, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneNotIn(List values) {
            addCriterion("i_buyer_phone not in", values, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneBetween(Long value1, Long value2) {
            addCriterion("i_buyer_phone between", value1, value2, "iBuyerPhone");
            return this;
        }

        public Criteria andIBuyerPhoneNotBetween(Long value1, Long value2) {
            addCriterion("i_buyer_phone not between", value1, value2, "iBuyerPhone");
            return this;
        }

        public Criteria andSShippingStreetIsNull() {
            addCriterion("s_shipping_street is null");
            return this;
        }

        public Criteria andSShippingStreetIsNotNull() {
            addCriterion("s_shipping_street is not null");
            return this;
        }

        public Criteria andSShippingStreetEqualTo(String value) {
            addCriterion("s_shipping_street =", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetNotEqualTo(String value) {
            addCriterion("s_shipping_street <>", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetGreaterThan(String value) {
            addCriterion("s_shipping_street >", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetGreaterThanOrEqualTo(String value) {
            addCriterion("s_shipping_street >=", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetLessThan(String value) {
            addCriterion("s_shipping_street <", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetLessThanOrEqualTo(String value) {
            addCriterion("s_shipping_street <=", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetLike(String value) {
            addCriterion("s_shipping_street like", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetNotLike(String value) {
            addCriterion("s_shipping_street not like", value, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetIn(List values) {
            addCriterion("s_shipping_street in", values, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetNotIn(List values) {
            addCriterion("s_shipping_street not in", values, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetBetween(String value1, String value2) {
            addCriterion("s_shipping_street between", value1, value2, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingStreetNotBetween(String value1, String value2) {
            addCriterion("s_shipping_street not between", value1, value2, "sShippingStreet");
            return this;
        }

        public Criteria andSShippingCityIsNull() {
            addCriterion("s_shipping_city is null");
            return this;
        }

        public Criteria andSShippingCityIsNotNull() {
            addCriterion("s_shipping_city is not null");
            return this;
        }

        public Criteria andSShippingCityEqualTo(String value) {
            addCriterion("s_shipping_city =", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityNotEqualTo(String value) {
            addCriterion("s_shipping_city <>", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityGreaterThan(String value) {
            addCriterion("s_shipping_city >", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityGreaterThanOrEqualTo(String value) {
            addCriterion("s_shipping_city >=", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityLessThan(String value) {
            addCriterion("s_shipping_city <", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityLessThanOrEqualTo(String value) {
            addCriterion("s_shipping_city <=", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityLike(String value) {
            addCriterion("s_shipping_city like", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityNotLike(String value) {
            addCriterion("s_shipping_city not like", value, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityIn(List values) {
            addCriterion("s_shipping_city in", values, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityNotIn(List values) {
            addCriterion("s_shipping_city not in", values, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityBetween(String value1, String value2) {
            addCriterion("s_shipping_city between", value1, value2, "sShippingCity");
            return this;
        }

        public Criteria andSShippingCityNotBetween(String value1, String value2) {
            addCriterion("s_shipping_city not between", value1, value2, "sShippingCity");
            return this;
        }

        public Criteria andSShippingStateIsNull() {
            addCriterion("s_shipping_state is null");
            return this;
        }

        public Criteria andSShippingStateIsNotNull() {
            addCriterion("s_shipping_state is not null");
            return this;
        }

        public Criteria andSShippingStateEqualTo(String value) {
            addCriterion("s_shipping_state =", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateNotEqualTo(String value) {
            addCriterion("s_shipping_state <>", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateGreaterThan(String value) {
            addCriterion("s_shipping_state >", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateGreaterThanOrEqualTo(String value) {
            addCriterion("s_shipping_state >=", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateLessThan(String value) {
            addCriterion("s_shipping_state <", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateLessThanOrEqualTo(String value) {
            addCriterion("s_shipping_state <=", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateLike(String value) {
            addCriterion("s_shipping_state like", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateNotLike(String value) {
            addCriterion("s_shipping_state not like", value, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateIn(List values) {
            addCriterion("s_shipping_state in", values, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateNotIn(List values) {
            addCriterion("s_shipping_state not in", values, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateBetween(String value1, String value2) {
            addCriterion("s_shipping_state between", value1, value2, "sShippingState");
            return this;
        }

        public Criteria andSShippingStateNotBetween(String value1, String value2) {
            addCriterion("s_shipping_state not between", value1, value2, "sShippingState");
            return this;
        }

        public Criteria andSShippingPostalCodeIsNull() {
            addCriterion("s_shipping_postal_code is null");
            return this;
        }

        public Criteria andSShippingPostalCodeIsNotNull() {
            addCriterion("s_shipping_postal_code is not null");
            return this;
        }

        public Criteria andSShippingPostalCodeEqualTo(String value) {
            addCriterion("s_shipping_postal_code =", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeNotEqualTo(String value) {
            addCriterion("s_shipping_postal_code <>", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeGreaterThan(String value) {
            addCriterion("s_shipping_postal_code >", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("s_shipping_postal_code >=", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeLessThan(String value) {
            addCriterion("s_shipping_postal_code <", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeLessThanOrEqualTo(String value) {
            addCriterion("s_shipping_postal_code <=", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeLike(String value) {
            addCriterion("s_shipping_postal_code like", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeNotLike(String value) {
            addCriterion("s_shipping_postal_code not like", value, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeIn(List values) {
            addCriterion("s_shipping_postal_code in", values, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeNotIn(List values) {
            addCriterion("s_shipping_postal_code not in", values, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeBetween(String value1, String value2) {
            addCriterion("s_shipping_postal_code between", value1, value2, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingPostalCodeNotBetween(String value1, String value2) {
            addCriterion("s_shipping_postal_code not between", value1, value2, "sShippingPostalCode");
            return this;
        }

        public Criteria andSShippingCountryIsNull() {
            addCriterion("s_shipping_country is null");
            return this;
        }

        public Criteria andSShippingCountryIsNotNull() {
            addCriterion("s_shipping_country is not null");
            return this;
        }

        public Criteria andSShippingCountryEqualTo(String value) {
            addCriterion("s_shipping_country =", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryNotEqualTo(String value) {
            addCriterion("s_shipping_country <>", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryGreaterThan(String value) {
            addCriterion("s_shipping_country >", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryGreaterThanOrEqualTo(String value) {
            addCriterion("s_shipping_country >=", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryLessThan(String value) {
            addCriterion("s_shipping_country <", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryLessThanOrEqualTo(String value) {
            addCriterion("s_shipping_country <=", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryLike(String value) {
            addCriterion("s_shipping_country like", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryNotLike(String value) {
            addCriterion("s_shipping_country not like", value, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryIn(List values) {
            addCriterion("s_shipping_country in", values, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryNotIn(List values) {
            addCriterion("s_shipping_country not in", values, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryBetween(String value1, String value2) {
            addCriterion("s_shipping_country between", value1, value2, "sShippingCountry");
            return this;
        }

        public Criteria andSShippingCountryNotBetween(String value1, String value2) {
            addCriterion("s_shipping_country not between", value1, value2, "sShippingCountry");
            return this;
        }

        public Criteria andSBillingStreetIsNull() {
            addCriterion("s_billing_street is null");
            return this;
        }

        public Criteria andSBillingStreetIsNotNull() {
            addCriterion("s_billing_street is not null");
            return this;
        }

        public Criteria andSBillingStreetEqualTo(String value) {
            addCriterion("s_billing_street =", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetNotEqualTo(String value) {
            addCriterion("s_billing_street <>", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetGreaterThan(String value) {
            addCriterion("s_billing_street >", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetGreaterThanOrEqualTo(String value) {
            addCriterion("s_billing_street >=", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetLessThan(String value) {
            addCriterion("s_billing_street <", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetLessThanOrEqualTo(String value) {
            addCriterion("s_billing_street <=", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetLike(String value) {
            addCriterion("s_billing_street like", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetNotLike(String value) {
            addCriterion("s_billing_street not like", value, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetIn(List values) {
            addCriterion("s_billing_street in", values, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetNotIn(List values) {
            addCriterion("s_billing_street not in", values, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetBetween(String value1, String value2) {
            addCriterion("s_billing_street between", value1, value2, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingStreetNotBetween(String value1, String value2) {
            addCriterion("s_billing_street not between", value1, value2, "sBillingStreet");
            return this;
        }

        public Criteria andSBillingCityIsNull() {
            addCriterion("s_billing_city is null");
            return this;
        }

        public Criteria andSBillingCityIsNotNull() {
            addCriterion("s_billing_city is not null");
            return this;
        }

        public Criteria andSBillingCityEqualTo(String value) {
            addCriterion("s_billing_city =", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityNotEqualTo(String value) {
            addCriterion("s_billing_city <>", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityGreaterThan(String value) {
            addCriterion("s_billing_city >", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityGreaterThanOrEqualTo(String value) {
            addCriterion("s_billing_city >=", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityLessThan(String value) {
            addCriterion("s_billing_city <", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityLessThanOrEqualTo(String value) {
            addCriterion("s_billing_city <=", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityLike(String value) {
            addCriterion("s_billing_city like", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityNotLike(String value) {
            addCriterion("s_billing_city not like", value, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityIn(List values) {
            addCriterion("s_billing_city in", values, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityNotIn(List values) {
            addCriterion("s_billing_city not in", values, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityBetween(String value1, String value2) {
            addCriterion("s_billing_city between", value1, value2, "sBillingCity");
            return this;
        }

        public Criteria andSBillingCityNotBetween(String value1, String value2) {
            addCriterion("s_billing_city not between", value1, value2, "sBillingCity");
            return this;
        }

        public Criteria andSBillingStateIsNull() {
            addCriterion("s_billing_state is null");
            return this;
        }

        public Criteria andSBillingStateIsNotNull() {
            addCriterion("s_billing_state is not null");
            return this;
        }

        public Criteria andSBillingStateEqualTo(String value) {
            addCriterion("s_billing_state =", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateNotEqualTo(String value) {
            addCriterion("s_billing_state <>", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateGreaterThan(String value) {
            addCriterion("s_billing_state >", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateGreaterThanOrEqualTo(String value) {
            addCriterion("s_billing_state >=", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateLessThan(String value) {
            addCriterion("s_billing_state <", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateLessThanOrEqualTo(String value) {
            addCriterion("s_billing_state <=", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateLike(String value) {
            addCriterion("s_billing_state like", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateNotLike(String value) {
            addCriterion("s_billing_state not like", value, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateIn(List values) {
            addCriterion("s_billing_state in", values, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateNotIn(List values) {
            addCriterion("s_billing_state not in", values, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateBetween(String value1, String value2) {
            addCriterion("s_billing_state between", value1, value2, "sBillingState");
            return this;
        }

        public Criteria andSBillingStateNotBetween(String value1, String value2) {
            addCriterion("s_billing_state not between", value1, value2, "sBillingState");
            return this;
        }

        public Criteria andSBillingPostalCodeIsNull() {
            addCriterion("s_billing_postal_code is null");
            return this;
        }

        public Criteria andSBillingPostalCodeIsNotNull() {
            addCriterion("s_billing_postal_code is not null");
            return this;
        }

        public Criteria andSBillingPostalCodeEqualTo(String value) {
            addCriterion("s_billing_postal_code =", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeNotEqualTo(String value) {
            addCriterion("s_billing_postal_code <>", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeGreaterThan(String value) {
            addCriterion("s_billing_postal_code >", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("s_billing_postal_code >=", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeLessThan(String value) {
            addCriterion("s_billing_postal_code <", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeLessThanOrEqualTo(String value) {
            addCriterion("s_billing_postal_code <=", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeLike(String value) {
            addCriterion("s_billing_postal_code like", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeNotLike(String value) {
            addCriterion("s_billing_postal_code not like", value, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeIn(List values) {
            addCriterion("s_billing_postal_code in", values, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeNotIn(List values) {
            addCriterion("s_billing_postal_code not in", values, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeBetween(String value1, String value2) {
            addCriterion("s_billing_postal_code between", value1, value2, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingPostalCodeNotBetween(String value1, String value2) {
            addCriterion("s_billing_postal_code not between", value1, value2, "sBillingPostalCode");
            return this;
        }

        public Criteria andSBillingCountryIsNull() {
            addCriterion("s_billing_country is null");
            return this;
        }

        public Criteria andSBillingCountryIsNotNull() {
            addCriterion("s_billing_country is not null");
            return this;
        }

        public Criteria andSBillingCountryEqualTo(String value) {
            addCriterion("s_billing_country =", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryNotEqualTo(String value) {
            addCriterion("s_billing_country <>", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryGreaterThan(String value) {
            addCriterion("s_billing_country >", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryGreaterThanOrEqualTo(String value) {
            addCriterion("s_billing_country >=", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryLessThan(String value) {
            addCriterion("s_billing_country <", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryLessThanOrEqualTo(String value) {
            addCriterion("s_billing_country <=", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryLike(String value) {
            addCriterion("s_billing_country like", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryNotLike(String value) {
            addCriterion("s_billing_country not like", value, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryIn(List values) {
            addCriterion("s_billing_country in", values, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryNotIn(List values) {
            addCriterion("s_billing_country not in", values, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryBetween(String value1, String value2) {
            addCriterion("s_billing_country between", value1, value2, "sBillingCountry");
            return this;
        }

        public Criteria andSBillingCountryNotBetween(String value1, String value2) {
            addCriterion("s_billing_country not between", value1, value2, "sBillingCountry");
            return this;
        }

        public Criteria andSStatusIsNull() {
            addCriterion("s_status is null");
            return this;
        }

        public Criteria andSStatusIsNotNull() {
            addCriterion("s_status is not null");
            return this;
        }

        public Criteria andSStatusEqualTo(String value) {
            addCriterion("s_status =", value, "sStatus");
            return this;
        }

        public Criteria andSStatusNotEqualTo(String value) {
            addCriterion("s_status <>", value, "sStatus");
            return this;
        }

        public Criteria andSStatusGreaterThan(String value) {
            addCriterion("s_status >", value, "sStatus");
            return this;
        }

        public Criteria andSStatusGreaterThanOrEqualTo(String value) {
            addCriterion("s_status >=", value, "sStatus");
            return this;
        }

        public Criteria andSStatusLessThan(String value) {
            addCriterion("s_status <", value, "sStatus");
            return this;
        }

        public Criteria andSStatusLessThanOrEqualTo(String value) {
            addCriterion("s_status <=", value, "sStatus");
            return this;
        }

        public Criteria andSStatusLike(String value) {
            addCriterion("s_status like", value, "sStatus");
            return this;
        }

        public Criteria andSStatusNotLike(String value) {
            addCriterion("s_status not like", value, "sStatus");
            return this;
        }

        public Criteria andSStatusIn(List values) {
            addCriterion("s_status in", values, "sStatus");
            return this;
        }

        public Criteria andSStatusNotIn(List values) {
            addCriterion("s_status not in", values, "sStatus");
            return this;
        }

        public Criteria andSStatusBetween(String value1, String value2) {
            addCriterion("s_status between", value1, value2, "sStatus");
            return this;
        }

        public Criteria andSStatusNotBetween(String value1, String value2) {
            addCriterion("s_status not between", value1, value2, "sStatus");
            return this;
        }

        public Criteria andDOrderDateIsNull() {
            addCriterion("d_order_date is null");
            return this;
        }

        public Criteria andDOrderDateIsNotNull() {
            addCriterion("d_order_date is not null");
            return this;
        }

        public Criteria andDOrderDateEqualTo(Date value) {
            addCriterion("d_order_date =", value, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateNotEqualTo(Date value) {
            addCriterion("d_order_date <>", value, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateGreaterThan(Date value) {
            addCriterion("d_order_date >", value, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateGreaterThanOrEqualTo(Date value) {
            addCriterion("d_order_date >=", value, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateLessThan(Date value) {
            addCriterion("d_order_date <", value, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateLessThanOrEqualTo(Date value) {
            addCriterion("d_order_date <=", value, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateIn(List values) {
            addCriterion("d_order_date in", values, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateNotIn(List values) {
            addCriterion("d_order_date not in", values, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateBetween(Date value1, Date value2) {
            addCriterion("d_order_date between", value1, value2, "dOrderDate");
            return this;
        }

        public Criteria andDOrderDateNotBetween(Date value1, Date value2) {
            addCriterion("d_order_date not between", value1, value2, "dOrderDate");
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
    }
}