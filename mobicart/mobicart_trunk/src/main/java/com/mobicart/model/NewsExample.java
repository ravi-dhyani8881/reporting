package com.mobicart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

	/**
	 *  This field corresponds to the database table news
	 * @author jasdeep.singh
	 */
public class NewsExample {

	/**
	 *  This field corresponds to the database table news
	 * 
	 */
	protected String orderByClause;
	/**
	 *  This field corresponds to the database table news
	 * 
	 */
	protected List oredCriteria;

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	public NewsExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	protected NewsExample(NewsExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 *  This method corresponds to the database table news
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
	 *  This method corresponds to the database table news
	 * 
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 *  This method corresponds to the database table news
	 * 
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 *  This class corresponds to the database table news
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

		public Criteria andSTitleIsNull() {
			addCriterion("s_title is null");
			return this;
		}

		public Criteria andSTitleIsNotNull() {
			addCriterion("s_title is not null");
			return this;
		}

		public Criteria andSTitleEqualTo(String value) {
			addCriterion("s_title =", value, "sTitle");
			return this;
		}

		public Criteria andSTitleNotEqualTo(String value) {
			addCriterion("s_title <>", value, "sTitle");
			return this;
		}

		public Criteria andSTitleGreaterThan(String value) {
			addCriterion("s_title >", value, "sTitle");
			return this;
		}

		public Criteria andSTitleGreaterThanOrEqualTo(String value) {
			addCriterion("s_title >=", value, "sTitle");
			return this;
		}

		public Criteria andSTitleLessThan(String value) {
			addCriterion("s_title <", value, "sTitle");
			return this;
		}

		public Criteria andSTitleLessThanOrEqualTo(String value) {
			addCriterion("s_title <=", value, "sTitle");
			return this;
		}

		public Criteria andSTitleLike(String value) {
			addCriterion("s_title like", value, "sTitle");
			return this;
		}

		public Criteria andSTitleNotLike(String value) {
			addCriterion("s_title not like", value, "sTitle");
			return this;
		}

		public Criteria andSTitleIn(List values) {
			addCriterion("s_title in", values, "sTitle");
			return this;
		}

		public Criteria andSTitleNotIn(List values) {
			addCriterion("s_title not in", values, "sTitle");
			return this;
		}

		public Criteria andSTitleBetween(String value1, String value2) {
			addCriterion("s_title between", value1, value2, "sTitle");
			return this;
		}

		public Criteria andSTitleNotBetween(String value1, String value2) {
			addCriterion("s_title not between", value1, value2, "sTitle");
			return this;
		}

		public Criteria andSImageIsNull() {
			addCriterion("s_image is null");
			return this;
		}

		public Criteria andSImageIsNotNull() {
			addCriterion("s_image is not null");
			return this;
		}

		public Criteria andSImageEqualTo(String value) {
			addCriterion("s_image =", value, "sImage");
			return this;
		}

		public Criteria andSImageNotEqualTo(String value) {
			addCriterion("s_image <>", value, "sImage");
			return this;
		}

		public Criteria andSImageGreaterThan(String value) {
			addCriterion("s_image >", value, "sImage");
			return this;
		}

		public Criteria andSImageGreaterThanOrEqualTo(String value) {
			addCriterion("s_image >=", value, "sImage");
			return this;
		}

		public Criteria andSImageLessThan(String value) {
			addCriterion("s_image <", value, "sImage");
			return this;
		}

		public Criteria andSImageLessThanOrEqualTo(String value) {
			addCriterion("s_image <=", value, "sImage");
			return this;
		}

		public Criteria andSImageLike(String value) {
			addCriterion("s_image like", value, "sImage");
			return this;
		}

		public Criteria andSImageNotLike(String value) {
			addCriterion("s_image not like", value, "sImage");
			return this;
		}

		public Criteria andSImageIn(List values) {
			addCriterion("s_image in", values, "sImage");
			return this;
		}

		public Criteria andSImageNotIn(List values) {
			addCriterion("s_image not in", values, "sImage");
			return this;
		}

		public Criteria andSImageBetween(String value1, String value2) {
			addCriterion("s_image between", value1, value2, "sImage");
			return this;
		}

		public Criteria andSImageNotBetween(String value1, String value2) {
			addCriterion("s_image not between", value1, value2, "sImage");
			return this;
		}

		public Criteria andSFeedUrlIsNull() {
			addCriterion("s_feed_url is null");
			return this;
		}

		public Criteria andSFeedUrlIsNotNull() {
			addCriterion("s_feed_url is not null");
			return this;
		}

		public Criteria andSFeedUrlEqualTo(String value) {
			addCriterion("s_feed_url =", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlNotEqualTo(String value) {
			addCriterion("s_feed_url <>", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlGreaterThan(String value) {
			addCriterion("s_feed_url >", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlGreaterThanOrEqualTo(String value) {
			addCriterion("s_feed_url >=", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlLessThan(String value) {
			addCriterion("s_feed_url <", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlLessThanOrEqualTo(String value) {
			addCriterion("s_feed_url <=", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlLike(String value) {
			addCriterion("s_feed_url like", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlNotLike(String value) {
			addCriterion("s_feed_url not like", value, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlIn(List values) {
			addCriterion("s_feed_url in", values, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlNotIn(List values) {
			addCriterion("s_feed_url not in", values, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlBetween(String value1, String value2) {
			addCriterion("s_feed_url between", value1, value2, "sFeedUrl");
			return this;
		}

		public Criteria andSFeedUrlNotBetween(String value1, String value2) {
			addCriterion("s_feed_url not between", value1, value2, "sFeedUrl");
			return this;
		}

		public Criteria andSTwitterUsernameIsNull() {
			addCriterion("s_twitter_username is null");
			return this;
		}

		public Criteria andSTwitterUsernameIsNotNull() {
			addCriterion("s_twitter_username is not null");
			return this;
		}

		public Criteria andSTwitterUsernameEqualTo(String value) {
			addCriterion("s_twitter_username =", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameNotEqualTo(String value) {
			addCriterion("s_twitter_username <>", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameGreaterThan(String value) {
			addCriterion("s_twitter_username >", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("s_twitter_username >=", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameLessThan(String value) {
			addCriterion("s_twitter_username <", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameLessThanOrEqualTo(String value) {
			addCriterion("s_twitter_username <=", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameLike(String value) {
			addCriterion("s_twitter_username like", value, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameNotLike(String value) {
			addCriterion("s_twitter_username not like", value,
					"sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameIn(List values) {
			addCriterion("s_twitter_username in", values, "sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameNotIn(List values) {
			addCriterion("s_twitter_username not in", values,
					"sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameBetween(String value1, String value2) {
			addCriterion("s_twitter_username between", value1, value2,
					"sTwitterUsername");
			return this;
		}

		public Criteria andSTwitterUsernameNotBetween(String value1,
				String value2) {
			addCriterion("s_twitter_username not between", value1, value2,
					"sTwitterUsername");
			return this;
		}

		public Criteria andBFeedStatusIsNull() {
			addCriterion("b_feed_status is null");
			return this;
		}

		public Criteria andBFeedStatusIsNotNull() {
			addCriterion("b_feed_status is not null");
			return this;
		}

		public Criteria andBFeedStatusEqualTo(Boolean value) {
			addCriterion("b_feed_status =", value, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusNotEqualTo(Boolean value) {
			addCriterion("b_feed_status <>", value, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusGreaterThan(Boolean value) {
			addCriterion("b_feed_status >", value, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusGreaterThanOrEqualTo(Boolean value) {
			addCriterion("b_feed_status >=", value, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusLessThan(Boolean value) {
			addCriterion("b_feed_status <", value, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusLessThanOrEqualTo(Boolean value) {
			addCriterion("b_feed_status <=", value, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusIn(List values) {
			addCriterion("b_feed_status in", values, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusNotIn(List values) {
			addCriterion("b_feed_status not in", values, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusBetween(Boolean value1, Boolean value2) {
			addCriterion("b_feed_status between", value1, value2, "bFeedStatus");
			return this;
		}

		public Criteria andBFeedStatusNotBetween(Boolean value1, Boolean value2) {
			addCriterion("b_feed_status not between", value1, value2,
					"bFeedStatus");
			return this;
		}

		public Criteria andBTwitterStatusIsNull() {
			addCriterion("b_twitter_status is null");
			return this;
		}

		public Criteria andBTwitterStatusIsNotNull() {
			addCriterion("b_twitter_status is not null");
			return this;
		}

		public Criteria andBTwitterStatusEqualTo(Boolean value) {
			addCriterion("b_twitter_status =", value, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusNotEqualTo(Boolean value) {
			addCriterion("b_twitter_status <>", value, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusGreaterThan(Boolean value) {
			addCriterion("b_twitter_status >", value, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusGreaterThanOrEqualTo(Boolean value) {
			addCriterion("b_twitter_status >=", value, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusLessThan(Boolean value) {
			addCriterion("b_twitter_status <", value, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusLessThanOrEqualTo(Boolean value) {
			addCriterion("b_twitter_status <=", value, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusIn(List values) {
			addCriterion("b_twitter_status in", values, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusNotIn(List values) {
			addCriterion("b_twitter_status not in", values, "bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusBetween(Boolean value1, Boolean value2) {
			addCriterion("b_twitter_status between", value1, value2,
					"bTwitterStatus");
			return this;
		}

		public Criteria andBTwitterStatusNotBetween(Boolean value1,
				Boolean value2) {
			addCriterion("b_twitter_status not between", value1, value2,
					"bTwitterStatus");
			return this;
		}

		public Criteria andSTypeIsNull() {
			addCriterion("s_type is null");
			return this;
		}

		public Criteria andSTypeIsNotNull() {
			addCriterion("s_type is not null");
			return this;
		}

		public Criteria andSTypeEqualTo(String value) {
			addCriterion("s_type =", value, "sType");
			return this;
		}

		public Criteria andSTypeNotEqualTo(String value) {
			addCriterion("s_type <>", value, "sType");
			return this;
		}

		public Criteria andSTypeGreaterThan(String value) {
			addCriterion("s_type >", value, "sType");
			return this;
		}

		public Criteria andSTypeGreaterThanOrEqualTo(String value) {
			addCriterion("s_type >=", value, "sType");
			return this;
		}

		public Criteria andSTypeLessThan(String value) {
			addCriterion("s_type <", value, "sType");
			return this;
		}

		public Criteria andSTypeLessThanOrEqualTo(String value) {
			addCriterion("s_type <=", value, "sType");
			return this;
		}

		public Criteria andSTypeLike(String value) {
			addCriterion("s_type like", value, "sType");
			return this;
		}

		public Criteria andSTypeNotLike(String value) {
			addCriterion("s_type not like", value, "sType");
			return this;
		}

		public Criteria andSTypeIn(List values) {
			addCriterion("s_type in", values, "sType");
			return this;
		}

		public Criteria andSTypeNotIn(List values) {
			addCriterion("s_type not in", values, "sType");
			return this;
		}

		public Criteria andSTypeBetween(String value1, String value2) {
			addCriterion("s_type between", value1, value2, "sType");
			return this;
		}

		public Criteria andSTypeNotBetween(String value1, String value2) {
			addCriterion("s_type not between", value1, value2, "sType");
			return this;
		}

		public Criteria andDDateIsNull() {
			addCriterion("d_date is null");
			return this;
		}

		public Criteria andDDateIsNotNull() {
			addCriterion("d_date is not null");
			return this;
		}

		public Criteria andDDateEqualTo(Date value) {
			addCriterion("d_date =", value, "dDate");
			return this;
		}

		public Criteria andDDateNotEqualTo(Date value) {
			addCriterion("d_date <>", value, "dDate");
			return this;
		}

		public Criteria andDDateGreaterThan(Date value) {
			addCriterion("d_date >", value, "dDate");
			return this;
		}

		public Criteria andDDateGreaterThanOrEqualTo(Date value) {
			addCriterion("d_date >=", value, "dDate");
			return this;
		}

		public Criteria andDDateLessThan(Date value) {
			addCriterion("d_date <", value, "dDate");
			return this;
		}

		public Criteria andDDateLessThanOrEqualTo(Date value) {
			addCriterion("d_date <=", value, "dDate");
			return this;
		}

		public Criteria andDDateIn(List values) {
			addCriterion("d_date in", values, "dDate");
			return this;
		}

		public Criteria andDDateNotIn(List values) {
			addCriterion("d_date not in", values, "dDate");
			return this;
		}

		public Criteria andDDateBetween(Date value1, Date value2) {
			addCriterion("d_date between", value1, value2, "dDate");
			return this;
		}

		public Criteria andDDateNotBetween(Date value1, Date value2) {
			addCriterion("d_date not between", value1, value2, "dDate");
			return this;
		}
		
		public Criteria andsBodyIsNull() {
			addCriterion("s_body is null");
			return this;
		}

		public Criteria andsBodyIsNotNull() {
			addCriterion("s_body is not null");
			return this;
		}

		public Criteria andsBodyEqualTo(String value) {
			addCriterion("s_body =", value, "sBody");
			return this;
		}

		public Criteria andsBodyNotEqualTo(String value) {
			addCriterion("s_body <>", value, "sBody");
			return this;
		}

		public Criteria andsBodyGreaterThan(String value) {
			addCriterion("s_body >", value, "sBody");
			return this;
		}

		public Criteria andsBodyGreaterThanOrEqualTo(String value) {
			addCriterion("s_body >=", value, "sBody");
			return this;
		}

		public Criteria andsBodyLessThan(String value) {
			addCriterion("s_body <", value, "sBody");
			return this;
		}

		public Criteria andsBodyLessThanOrEqualTo(String value) {
			addCriterion("s_body <=", value, "sBody");
			return this;
		}

		public Criteria andsBodyLike(String value) {
			addCriterion("s_body like", value, "sBody");
			return this;
		}

		public Criteria andsBodyNotLike(String value) {
			addCriterion("s_body not like", value, "sBody");
			return this;
		}

		public Criteria andsBodyIn(List values) {
			addCriterion("s_body in", values, "sBody");
			return this;
		}

		public Criteria andsBodyNotIn(List values) {
			addCriterion("s_body not in", values, "sBody");
			return this;
		}

		public Criteria andsBodyBetween(String value1, String value2) {
			addCriterion("s_body between", value1, value2, "sBody");
			return this;
		}

		public Criteria andsBodyNotBetween(String value1, String value2) {
			addCriterion("s_body not between", value1, value2, "sBody");
			return this;
		}
	}
}