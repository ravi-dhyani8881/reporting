package com.mobicart.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApiPartnerExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    public ApiPartnerExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    protected ApiPartnerExample(ApiPartnerExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
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
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table api_partner
     *
     * @ibatorgenerated Wed Jan 18 18:43:27 IST 2012
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List dateList = new ArrayList();
            Iterator iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(((Date)iter.next()).getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andPartnerNameIsNull() {
            addCriterion("partner_name is null");
            return this;
        }

        public Criteria andPartnerNameIsNotNull() {
            addCriterion("partner_name is not null");
            return this;
        }

        public Criteria andPartnerNameEqualTo(String value) {
            addCriterion("partner_name =", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameNotEqualTo(String value) {
            addCriterion("partner_name <>", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameGreaterThan(String value) {
            addCriterion("partner_name >", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("partner_name >=", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameLessThan(String value) {
            addCriterion("partner_name <", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameLessThanOrEqualTo(String value) {
            addCriterion("partner_name <=", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameLike(String value) {
            addCriterion("partner_name like", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameNotLike(String value) {
            addCriterion("partner_name not like", value, "partnerName");
            return this;
        }

        public Criteria andPartnerNameIn(List values) {
            addCriterion("partner_name in", values, "partnerName");
            return this;
        }

        public Criteria andPartnerNameNotIn(List values) {
            addCriterion("partner_name not in", values, "partnerName");
            return this;
        }

        public Criteria andPartnerNameBetween(String value1, String value2) {
            addCriterion("partner_name between", value1, value2, "partnerName");
            return this;
        }

        public Criteria andPartnerNameNotBetween(String value1, String value2) {
            addCriterion("partner_name not between", value1, value2, "partnerName");
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
        
        
        
        
        
        
        
        

        public Criteria andApiKeyIsNull() {
            addCriterion("api_key is null");
            return this;
        }

        public Criteria andApiKeyIsNotNull() {
            addCriterion("api_key is not null");
            return this;
        }

        public Criteria andApiKeyEqualTo(String value) {
            addCriterion("api_key =", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyNotEqualTo(String value) {
            addCriterion("api_key <>", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyGreaterThan(String value) {
            addCriterion("api_key >", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyGreaterThanOrEqualTo(String value) {
            addCriterion("api_key >=", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyLessThan(String value) {
            addCriterion("api_key <", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyLessThanOrEqualTo(String value) {
            addCriterion("api_key <=", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyLike(String value) {
            addCriterion("api_key like", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyNotLike(String value) {
            addCriterion("api_key not like", value, "apiKey");
            return this;
        }

        public Criteria andApiKeyIn(List values) {
            addCriterion("api_key in", values, "apiKey");
            return this;
        }

        public Criteria andApiKeyNotIn(List values) {
            addCriterion("api_key not in", values, "apiKey");
            return this;
        }

        public Criteria andApiKeyBetween(String value1, String value2) {
            addCriterion("api_key between", value1, value2, "apiKey");
            return this;
        }

        public Criteria andApiKeyNotBetween(String value1, String value2) {
            addCriterion("api_key not between", value1, value2, "apiKey");
            return this;
        }

        public Criteria andDateOfJoiningIsNull() {
            addCriterion("date_of_joining is null");
            return this;
        }

        public Criteria andDateOfJoiningIsNotNull() {
            addCriterion("date_of_joining is not null");
            return this;
        }

        public Criteria andDateOfJoiningEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_joining =", value, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningNotEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_joining <>", value, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningGreaterThan(Date value) {
            addCriterionForJDBCDate("date_of_joining >", value, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_joining >=", value, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningLessThan(Date value) {
            addCriterionForJDBCDate("date_of_joining <", value, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_joining <=", value, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningIn(List values) {
            addCriterionForJDBCDate("date_of_joining in", values, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningNotIn(List values) {
            addCriterionForJDBCDate("date_of_joining not in", values, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date_of_joining between", value1, value2, "dateOfJoining");
            return this;
        }

        public Criteria andDateOfJoiningNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date_of_joining not between", value1, value2, "dateOfJoining");
            return this;
        }
    }
}