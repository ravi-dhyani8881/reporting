package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    public DepartmentExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    protected DepartmentExample(DepartmentExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
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
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table departments
     *
     * @ibatorgenerated Thu Aug 12 11:31:32 IST 2010
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
        
        
        public Criteria andParentDepartmentIdIsNull() {
            addCriterion("parent_department_id is null");
            return this;
        }

        public Criteria andParentDepartmentIsNotNull() {
            addCriterion("parent_department_id is not null");
            return this;
        }

        public Criteria andParentDepartmentIdEqualTo(Long value) {
            addCriterion("parent_department_id =", value, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdNotEqualTo(Long value) {
            addCriterion("parent_department_id <>", value, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdGreaterThan(Long value) {
            addCriterion("parent_department_id >", value, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_department_id >=", value, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdLessThan(Long value) {
            addCriterion("parent_department_id <", value, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_department_id <=", value, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdIn(List values) {
            addCriterion("parent_department_id in", values, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdNotIn(List values) {
            addCriterion("parent_department_id not in", values, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdBetween(Long value1, Long value2) {
            addCriterion("parent_department_id between", value1, value2, "parentDepartmentId");
            return this;
        }

        public Criteria andParentDepartmentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_department_id not between", value1, value2, "parentDepartmentId");
            return this;
        }

        
    }
}