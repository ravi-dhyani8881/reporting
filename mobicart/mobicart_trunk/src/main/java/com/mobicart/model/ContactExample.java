package com.mobicart.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    public ContactExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    protected ContactExample(ContactExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
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
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table contacts
     *
     * @ibatorgenerated Fri Oct 01 20:29:55 IST 2010
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

        public Criteria andSEmailIsNull() {
            addCriterion("s_email is null");
            return this;
        }

        public Criteria andSEmailIsNotNull() {
            addCriterion("s_email is not null");
            return this;
        }

        public Criteria andSEmailEqualTo(String value) {
            addCriterion("s_email =", value, "sEmail");
            return this;
        }

        public Criteria andSEmailNotEqualTo(String value) {
            addCriterion("s_email <>", value, "sEmail");
            return this;
        }

        public Criteria andSEmailGreaterThan(String value) {
            addCriterion("s_email >", value, "sEmail");
            return this;
        }

        public Criteria andSEmailGreaterThanOrEqualTo(String value) {
            addCriterion("s_email >=", value, "sEmail");
            return this;
        }

        public Criteria andSEmailLessThan(String value) {
            addCriterion("s_email <", value, "sEmail");
            return this;
        }

        public Criteria andSEmailLessThanOrEqualTo(String value) {
            addCriterion("s_email <=", value, "sEmail");
            return this;
        }

        public Criteria andSEmailLike(String value) {
            addCriterion("s_email like", value, "sEmail");
            return this;
        }

        public Criteria andSEmailNotLike(String value) {
            addCriterion("s_email not like", value, "sEmail");
            return this;
        }

        public Criteria andSEmailIn(List values) {
            addCriterion("s_email in", values, "sEmail");
            return this;
        }

        public Criteria andSEmailNotIn(List values) {
            addCriterion("s_email not in", values, "sEmail");
            return this;
        }

        public Criteria andSEmailBetween(String value1, String value2) {
            addCriterion("s_email between", value1, value2, "sEmail");
            return this;
        }

        public Criteria andSEmailNotBetween(String value1, String value2) {
            addCriterion("s_email not between", value1, value2, "sEmail");
            return this;
        }

        public Criteria andSPhoneIsNull() {
            addCriterion("s_phone is null");
            return this;
        }

        public Criteria andSPhoneIsNotNull() {
            addCriterion("s_phone is not null");
            return this;
        }

        public Criteria andSPhoneEqualTo(Integer value) {
            addCriterion("s_phone =", value, "sPhone");
            return this;
        }

        public Criteria andSPhoneNotEqualTo(Integer value) {
            addCriterion("s_phone <>", value, "sPhone");
            return this;
        }

        public Criteria andSPhoneGreaterThan(Integer value) {
            addCriterion("s_phone >", value, "sPhone");
            return this;
        }

        public Criteria andSPhoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("s_phone >=", value, "sPhone");
            return this;
        }

        public Criteria andSPhoneLessThan(Integer value) {
            addCriterion("s_phone <", value, "sPhone");
            return this;
        }

        public Criteria andSPhoneLessThanOrEqualTo(Integer value) {
            addCriterion("s_phone <=", value, "sPhone");
            return this;
        }

        public Criteria andSPhoneIn(List values) {
            addCriterion("s_phone in", values, "sPhone");
            return this;
        }

        public Criteria andSPhoneNotIn(List values) {
            addCriterion("s_phone not in", values, "sPhone");
            return this;
        }

        public Criteria andSPhoneBetween(Integer value1, Integer value2) {
            addCriterion("s_phone between", value1, value2, "sPhone");
            return this;
        }

        public Criteria andSPhoneNotBetween(Integer value1, Integer value2) {
            addCriterion("s_phone not between", value1, value2, "sPhone");
            return this;
        }

        public Criteria andSHeardFromIsNull() {
            addCriterion("s_heard_from is null");
            return this;
        }

        public Criteria andSHeardFromIsNotNull() {
            addCriterion("s_heard_from is not null");
            return this;
        }

        public Criteria andSHeardFromEqualTo(String value) {
            addCriterion("s_heard_from =", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromNotEqualTo(String value) {
            addCriterion("s_heard_from <>", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromGreaterThan(String value) {
            addCriterion("s_heard_from >", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromGreaterThanOrEqualTo(String value) {
            addCriterion("s_heard_from >=", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromLessThan(String value) {
            addCriterion("s_heard_from <", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromLessThanOrEqualTo(String value) {
            addCriterion("s_heard_from <=", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromLike(String value) {
            addCriterion("s_heard_from like", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromNotLike(String value) {
            addCriterion("s_heard_from not like", value, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromIn(List values) {
            addCriterion("s_heard_from in", values, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromNotIn(List values) {
            addCriterion("s_heard_from not in", values, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromBetween(String value1, String value2) {
            addCriterion("s_heard_from between", value1, value2, "sHeardFrom");
            return this;
        }

        public Criteria andSHeardFromNotBetween(String value1, String value2) {
            addCriterion("s_heard_from not between", value1, value2, "sHeardFrom");
            return this;
        }

        public Criteria andDSentOnIsNull() {
            addCriterion("d_sent_on is null");
            return this;
        }

        public Criteria andDSentOnIsNotNull() {
            addCriterion("d_sent_on is not null");
            return this;
        }

        public Criteria andDSentOnEqualTo(Date value) {
            addCriterion("d_sent_on =", value, "dSentOn");
            return this;
        }

        public Criteria andDSentOnNotEqualTo(Date value) {
            addCriterion("d_sent_on <>", value, "dSentOn");
            return this;
        }

        public Criteria andDSentOnGreaterThan(Date value) {
            addCriterion("d_sent_on >", value, "dSentOn");
            return this;
        }

        public Criteria andDSentOnGreaterThanOrEqualTo(Date value) {
            addCriterion("d_sent_on >=", value, "dSentOn");
            return this;
        }

        public Criteria andDSentOnLessThan(Date value) {
            addCriterion("d_sent_on <", value, "dSentOn");
            return this;
        }

        public Criteria andDSentOnLessThanOrEqualTo(Date value) {
            addCriterion("d_sent_on <=", value, "dSentOn");
            return this;
        }

        public Criteria andDSentOnIn(List values) {
            addCriterion("d_sent_on in", values, "dSentOn");
            return this;
        }

        public Criteria andDSentOnNotIn(List values) {
            addCriterion("d_sent_on not in", values, "dSentOn");
            return this;
        }

        public Criteria andDSentOnBetween(Date value1, Date value2) {
            addCriterion("d_sent_on between", value1, value2, "dSentOn");
            return this;
        }

        public Criteria andDSentOnNotBetween(Date value1, Date value2) {
            addCriterion("d_sent_on not between", value1, value2, "dSentOn");
            return this;
        }
    }
}