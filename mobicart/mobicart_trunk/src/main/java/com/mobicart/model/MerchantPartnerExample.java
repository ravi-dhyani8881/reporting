package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantPartnerExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public MerchantPartnerExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    protected MerchantPartnerExample(MerchantPartnerExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table merchant_partner
     *
     * @ibatorgenerated Thu Jan 19 19:29:57 IST 2012
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
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

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;
        }

        public Criteria andPartnerEmailIsNull() {
            addCriterion("partner_email is null");
            return this;
        }

        public Criteria andPartnerEmailIsNotNull() {
            addCriterion("partner_email is not null");
            return this;
        }

        public Criteria andPartnerEmailEqualTo(String value) {
            addCriterion("partner_email =", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailNotEqualTo(String value) {
            addCriterion("partner_email <>", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailGreaterThan(String value) {
            addCriterion("partner_email >", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("partner_email >=", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailLessThan(String value) {
            addCriterion("partner_email <", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailLessThanOrEqualTo(String value) {
            addCriterion("partner_email <=", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailLike(String value) {
            addCriterion("partner_email like", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailNotLike(String value) {
            addCriterion("partner_email not like", value, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailIn(List values) {
            addCriterion("partner_email in", values, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailNotIn(List values) {
            addCriterion("partner_email not in", values, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailBetween(String value1, String value2) {
            addCriterion("partner_email between", value1, value2, "partnerEmail");
            return this;
        }

        public Criteria andPartnerEmailNotBetween(String value1, String value2) {
            addCriterion("partner_email not between", value1, value2, "partnerEmail");
            return this;
        }

        public Criteria andMerchantEmailIsNull() {
            addCriterion("merchant_email is null");
            return this;
        }

        public Criteria andMerchantEmailIsNotNull() {
            addCriterion("merchant_email is not null");
            return this;
        }

        public Criteria andMerchantEmailEqualTo(String value) {
            addCriterion("merchant_email =", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailNotEqualTo(String value) {
            addCriterion("merchant_email <>", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailGreaterThan(String value) {
            addCriterion("merchant_email >", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_email >=", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailLessThan(String value) {
            addCriterion("merchant_email <", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailLessThanOrEqualTo(String value) {
            addCriterion("merchant_email <=", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailLike(String value) {
            addCriterion("merchant_email like", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailNotLike(String value) {
            addCriterion("merchant_email not like", value, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailIn(List values) {
            addCriterion("merchant_email in", values, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailNotIn(List values) {
            addCriterion("merchant_email not in", values, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailBetween(String value1, String value2) {
            addCriterion("merchant_email between", value1, value2, "merchantEmail");
            return this;
        }

        public Criteria andMerchantEmailNotBetween(String value1, String value2) {
            addCriterion("merchant_email not between", value1, value2, "merchantEmail");
            return this;
        }
    }
}