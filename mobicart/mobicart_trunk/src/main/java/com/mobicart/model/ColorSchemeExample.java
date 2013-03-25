package com.mobicart.model;

import java.util.ArrayList;
import java.util.List;

public class ColorSchemeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public ColorSchemeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSTitleIsNull() {
            addCriterion("s_title is null");
            return (Criteria) this;
        }

        public Criteria andSTitleIsNotNull() {
            addCriterion("s_title is not null");
            return (Criteria) this;
        }

        public Criteria andSTitleEqualTo(String value) {
            addCriterion("s_title =", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotEqualTo(String value) {
            addCriterion("s_title <>", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleGreaterThan(String value) {
            addCriterion("s_title >", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleGreaterThanOrEqualTo(String value) {
            addCriterion("s_title >=", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleLessThan(String value) {
            addCriterion("s_title <", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleLessThanOrEqualTo(String value) {
            addCriterion("s_title <=", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleLike(String value) {
            addCriterion("s_title like", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotLike(String value) {
            addCriterion("s_title not like", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleIn(List<String> values) {
            addCriterion("s_title in", values, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotIn(List<String> values) {
            addCriterion("s_title not in", values, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleBetween(String value1, String value2) {
            addCriterion("s_title between", value1, value2, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotBetween(String value1, String value2) {
            addCriterion("s_title not between", value1, value2, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTypeIsNull() {
            addCriterion("s_type is null");
            return (Criteria) this;
        }

        public Criteria andSTypeIsNotNull() {
            addCriterion("s_type is not null");
            return (Criteria) this;
        }

        public Criteria andSTypeEqualTo(String value) {
            addCriterion("s_type =", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotEqualTo(String value) {
            addCriterion("s_type <>", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeGreaterThan(String value) {
            addCriterion("s_type >", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeGreaterThanOrEqualTo(String value) {
            addCriterion("s_type >=", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLessThan(String value) {
            addCriterion("s_type <", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLessThanOrEqualTo(String value) {
            addCriterion("s_type <=", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLike(String value) {
            addCriterion("s_type like", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotLike(String value) {
            addCriterion("s_type not like", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeIn(List<String> values) {
            addCriterion("s_type in", values, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotIn(List<String> values) {
            addCriterion("s_type not in", values, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeBetween(String value1, String value2) {
            addCriterion("s_type between", value1, value2, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotBetween(String value1, String value2) {
            addCriterion("s_type not between", value1, value2, "sType");
            return (Criteria) this;
        }

        public Criteria andSBgColorIsNull() {
            addCriterion("s_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andSBgColorIsNotNull() {
            addCriterion("s_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andSBgColorEqualTo(String value) {
            addCriterion("s_bg_color =", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorNotEqualTo(String value) {
            addCriterion("s_bg_color <>", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorGreaterThan(String value) {
            addCriterion("s_bg_color >", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("s_bg_color >=", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorLessThan(String value) {
            addCriterion("s_bg_color <", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorLessThanOrEqualTo(String value) {
            addCriterion("s_bg_color <=", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorLike(String value) {
            addCriterion("s_bg_color like", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorNotLike(String value) {
            addCriterion("s_bg_color not like", value, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorIn(List<String> values) {
            addCriterion("s_bg_color in", values, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorNotIn(List<String> values) {
            addCriterion("s_bg_color not in", values, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorBetween(String value1, String value2) {
            addCriterion("s_bg_color between", value1, value2, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSBgColorNotBetween(String value1, String value2) {
            addCriterion("s_bg_color not between", value1, value2, "sBgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorIsNull() {
            addCriterion("s_fg_color is null");
            return (Criteria) this;
        }

        public Criteria andSFgColorIsNotNull() {
            addCriterion("s_fg_color is not null");
            return (Criteria) this;
        }

        public Criteria andSFgColorEqualTo(String value) {
            addCriterion("s_fg_color =", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorNotEqualTo(String value) {
            addCriterion("s_fg_color <>", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorGreaterThan(String value) {
            addCriterion("s_fg_color >", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorGreaterThanOrEqualTo(String value) {
            addCriterion("s_fg_color >=", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorLessThan(String value) {
            addCriterion("s_fg_color <", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorLessThanOrEqualTo(String value) {
            addCriterion("s_fg_color <=", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorLike(String value) {
            addCriterion("s_fg_color like", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorNotLike(String value) {
            addCriterion("s_fg_color not like", value, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorIn(List<String> values) {
            addCriterion("s_fg_color in", values, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorNotIn(List<String> values) {
            addCriterion("s_fg_color not in", values, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorBetween(String value1, String value2) {
            addCriterion("s_fg_color between", value1, value2, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSFgColorNotBetween(String value1, String value2) {
            addCriterion("s_fg_color not between", value1, value2, "sFgColor");
            return (Criteria) this;
        }

        public Criteria andSBgImageIsNull() {
            addCriterion("s_bg_image is null");
            return (Criteria) this;
        }

        public Criteria andSBgImageIsNotNull() {
            addCriterion("s_bg_image is not null");
            return (Criteria) this;
        }

        public Criteria andSBgImageEqualTo(String value) {
            addCriterion("s_bg_image =", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageNotEqualTo(String value) {
            addCriterion("s_bg_image <>", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageGreaterThan(String value) {
            addCriterion("s_bg_image >", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageGreaterThanOrEqualTo(String value) {
            addCriterion("s_bg_image >=", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageLessThan(String value) {
            addCriterion("s_bg_image <", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageLessThanOrEqualTo(String value) {
            addCriterion("s_bg_image <=", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageLike(String value) {
            addCriterion("s_bg_image like", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageNotLike(String value) {
            addCriterion("s_bg_image not like", value, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageIn(List<String> values) {
            addCriterion("s_bg_image in", values, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageNotIn(List<String> values) {
            addCriterion("s_bg_image not in", values, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageBetween(String value1, String value2) {
            addCriterion("s_bg_image between", value1, value2, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSBgImageNotBetween(String value1, String value2) {
            addCriterion("s_bg_image not between", value1, value2, "sBgImage");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorIsNull() {
            addCriterion("s_product_item_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorIsNotNull() {
            addCriterion("s_product_item_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorEqualTo(String value) {
            addCriterion("s_product_item_bg_color =", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorNotEqualTo(String value) {
            addCriterion("s_product_item_bg_color <>", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorGreaterThan(String value) {
            addCriterion("s_product_item_bg_color >", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("s_product_item_bg_color >=", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorLessThan(String value) {
            addCriterion("s_product_item_bg_color <", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorLessThanOrEqualTo(String value) {
            addCriterion("s_product_item_bg_color <=", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorLike(String value) {
            addCriterion("s_product_item_bg_color like", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorNotLike(String value) {
            addCriterion("s_product_item_bg_color not like", value, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorIn(List<String> values) {
            addCriterion("s_product_item_bg_color in", values, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorNotIn(List<String> values) {
            addCriterion("s_product_item_bg_color not in", values, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorBetween(String value1, String value2) {
            addCriterion("s_product_item_bg_color between", value1, value2, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSProductItemBgColorNotBetween(String value1, String value2) {
            addCriterion("s_product_item_bg_color not between", value1, value2, "sProductItemBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorIsNull() {
            addCriterion("s_search_button_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorIsNotNull() {
            addCriterion("s_search_button_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorEqualTo(String value) {
            addCriterion("s_search_button_bg_color =", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorNotEqualTo(String value) {
            addCriterion("s_search_button_bg_color <>", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorGreaterThan(String value) {
            addCriterion("s_search_button_bg_color >", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("s_search_button_bg_color >=", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorLessThan(String value) {
            addCriterion("s_search_button_bg_color <", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorLessThanOrEqualTo(String value) {
            addCriterion("s_search_button_bg_color <=", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorLike(String value) {
            addCriterion("s_search_button_bg_color like", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorNotLike(String value) {
            addCriterion("s_search_button_bg_color not like", value, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorIn(List<String> values) {
            addCriterion("s_search_button_bg_color in", values, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorNotIn(List<String> values) {
            addCriterion("s_search_button_bg_color not in", values, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorBetween(String value1, String value2) {
            addCriterion("s_search_button_bg_color between", value1, value2, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSSearchButtonBgColorNotBetween(String value1, String value2) {
            addCriterion("s_search_button_bg_color not between", value1, value2, "sSearchButtonBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorIsNull() {
            addCriterion("s_price_tag_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorIsNotNull() {
            addCriterion("s_price_tag_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorEqualTo(String value) {
            addCriterion("s_price_tag_bg_color =", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorNotEqualTo(String value) {
            addCriterion("s_price_tag_bg_color <>", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorGreaterThan(String value) {
            addCriterion("s_price_tag_bg_color >", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("s_price_tag_bg_color >=", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorLessThan(String value) {
            addCriterion("s_price_tag_bg_color <", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorLessThanOrEqualTo(String value) {
            addCriterion("s_price_tag_bg_color <=", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorLike(String value) {
            addCriterion("s_price_tag_bg_color like", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorNotLike(String value) {
            addCriterion("s_price_tag_bg_color not like", value, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorIn(List<String> values) {
            addCriterion("s_price_tag_bg_color in", values, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorNotIn(List<String> values) {
            addCriterion("s_price_tag_bg_color not in", values, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorBetween(String value1, String value2) {
            addCriterion("s_price_tag_bg_color between", value1, value2, "sPriceTagBgColor");
            return (Criteria) this;
        }

        public Criteria andSPriceTagBgColorNotBetween(String value1, String value2) {
            addCriterion("s_price_tag_bg_color not between", value1, value2, "sPriceTagBgColor");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table color_schemes
     *
     * @mbggenerated do_not_delete_during_merge Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table color_schemes
     *
     * @mbggenerated Sat Jul 16 16:53:34 GMT+05:30 2011
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}