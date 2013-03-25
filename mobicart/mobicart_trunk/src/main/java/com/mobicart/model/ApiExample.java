package com.mobicart.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiExample {

	/**
	 *  This field corresponds to the database table api
	 * 
	 */
	protected String orderByClause;
	/**
	 *  This field corresponds to the database table api
	 * 
	 */
	protected List oredCriteria;

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	public ApiExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	protected ApiExample(ApiExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 *  This method corresponds to the database table api
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
	 *  This method corresponds to the database table api
	 * 
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 *  This method corresponds to the database table api
	 * 
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 *  This class corresponds to the database table api
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

		public Criteria andOauthSecretIsNull() {
			addCriterion("oauth_secret is null");
			return this;
		}

		public Criteria andOauthSecretIsNotNull() {
			addCriterion("oauth_secret is not null");
			return this;
		}

		public Criteria andOauthSecretEqualTo(String value) {
			addCriterion("oauth_secret =", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretNotEqualTo(String value) {
			addCriterion("oauth_secret <>", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretGreaterThan(String value) {
			addCriterion("oauth_secret >", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretGreaterThanOrEqualTo(String value) {
			addCriterion("oauth_secret >=", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretLessThan(String value) {
			addCriterion("oauth_secret <", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretLessThanOrEqualTo(String value) {
			addCriterion("oauth_secret <=", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretLike(String value) {
			addCriterion("oauth_secret like", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretNotLike(String value) {
			addCriterion("oauth_secret not like", value, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretIn(List values) {
			addCriterion("oauth_secret in", values, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretNotIn(List values) {
			addCriterion("oauth_secret not in", values, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretBetween(String value1, String value2) {
			addCriterion("oauth_secret between", value1, value2, "oauthSecret");
			return this;
		}

		public Criteria andOauthSecretNotBetween(String value1, String value2) {
			addCriterion("oauth_secret not between", value1, value2,
					"oauthSecret");
			return this;
		}

		public Criteria andThresholdGeneralCountIsNull() {
			addCriterion("threshold_general_count is null");
			return this;
		}

		public Criteria andThresholdGeneralCountIsNotNull() {
			addCriterion("threshold_general_count is not null");
			return this;
		}

		public Criteria andThresholdGeneralCountEqualTo(Integer value) {
			addCriterion("threshold_general_count =", value,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountNotEqualTo(Integer value) {
			addCriterion("threshold_general_count <>", value,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountGreaterThan(Integer value) {
			addCriterion("threshold_general_count >", value,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountGreaterThanOrEqualTo(
				Integer value) {
			addCriterion("threshold_general_count >=", value,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountLessThan(Integer value) {
			addCriterion("threshold_general_count <", value,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountLessThanOrEqualTo(Integer value) {
			addCriterion("threshold_general_count <=", value,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountIn(List values) {
			addCriterion("threshold_general_count in", values,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountNotIn(List values) {
			addCriterion("threshold_general_count not in", values,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountBetween(Integer value1,
				Integer value2) {
			addCriterion("threshold_general_count between", value1, value2,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdGeneralCountNotBetween(Integer value1,
				Integer value2) {
			addCriterion("threshold_general_count not between", value1, value2,
					"thresholdGeneralCount");
			return this;
		}

		public Criteria andThresholdStoreCountIsNull() {
			addCriterion("threshold_store_count is null");
			return this;
		}

		public Criteria andThresholdStoreCountIsNotNull() {
			addCriterion("threshold_store_count is not null");
			return this;
		}

		public Criteria andThresholdStoreCountEqualTo(Integer value) {
			addCriterion("threshold_store_count =", value,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountNotEqualTo(Integer value) {
			addCriterion("threshold_store_count <>", value,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountGreaterThan(Integer value) {
			addCriterion("threshold_store_count >", value,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("threshold_store_count >=", value,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountLessThan(Integer value) {
			addCriterion("threshold_store_count <", value,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountLessThanOrEqualTo(Integer value) {
			addCriterion("threshold_store_count <=", value,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountIn(List values) {
			addCriterion("threshold_store_count in", values,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountNotIn(List values) {
			addCriterion("threshold_store_count not in", values,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountBetween(Integer value1,
				Integer value2) {
			addCriterion("threshold_store_count between", value1, value2,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdStoreCountNotBetween(Integer value1,
				Integer value2) {
			addCriterion("threshold_store_count not between", value1, value2,
					"thresholdStoreCount");
			return this;
		}

		public Criteria andThresholdRefreshCountIsNull() {
			addCriterion("threshold_refresh_count is null");
			return this;
		}

		public Criteria andThresholdRefreshCountIsNotNull() {
			addCriterion("threshold_refresh_count is not null");
			return this;
		}

		public Criteria andThresholdRefreshCountEqualTo(Integer value) {
			addCriterion("threshold_refresh_count =", value,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountNotEqualTo(Integer value) {
			addCriterion("threshold_refresh_count <>", value,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountGreaterThan(Integer value) {
			addCriterion("threshold_refresh_count >", value,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountGreaterThanOrEqualTo(
				Integer value) {
			addCriterion("threshold_refresh_count >=", value,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountLessThan(Integer value) {
			addCriterion("threshold_refresh_count <", value,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountLessThanOrEqualTo(Integer value) {
			addCriterion("threshold_refresh_count <=", value,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountIn(List values) {
			addCriterion("threshold_refresh_count in", values,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountNotIn(List values) {
			addCriterion("threshold_refresh_count not in", values,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountBetween(Integer value1,
				Integer value2) {
			addCriterion("threshold_refresh_count between", value1, value2,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andThresholdRefreshCountNotBetween(Integer value1,
				Integer value2) {
			addCriterion("threshold_refresh_count not between", value1, value2,
					"thresholdRefreshCount");
			return this;
		}

		public Criteria andGeneralCounterIsNull() {
			addCriterion("general_counter is null");
			return this;
		}

		public Criteria andGeneralCounterIsNotNull() {
			addCriterion("general_counter is not null");
			return this;
		}

		public Criteria andGeneralCounterEqualTo(Integer value) {
			addCriterion("general_counter =", value, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterNotEqualTo(Integer value) {
			addCriterion("general_counter <>", value, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterGreaterThan(Integer value) {
			addCriterion("general_counter >", value, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterGreaterThanOrEqualTo(Integer value) {
			addCriterion("general_counter >=", value, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterLessThan(Integer value) {
			addCriterion("general_counter <", value, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterLessThanOrEqualTo(Integer value) {
			addCriterion("general_counter <=", value, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterIn(List values) {
			addCriterion("general_counter in", values, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterNotIn(List values) {
			addCriterion("general_counter not in", values, "generalCounter");
			return this;
		}

		public Criteria andGeneralCounterBetween(Integer value1, Integer value2) {
			addCriterion("general_counter between", value1, value2,
					"generalCounter");
			return this;
		}

		public Criteria andGeneralCounterNotBetween(Integer value1,
				Integer value2) {
			addCriterion("general_counter not between", value1, value2,
					"generalCounter");
			return this;
		}

		public Criteria andStoreCounterIsNull() {
			addCriterion("store_counter is null");
			return this;
		}

		public Criteria andStoreCounterIsNotNull() {
			addCriterion("store_counter is not null");
			return this;
		}

		public Criteria andStoreCounterEqualTo(Integer value) {
			addCriterion("store_counter =", value, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterNotEqualTo(Integer value) {
			addCriterion("store_counter <>", value, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterGreaterThan(Integer value) {
			addCriterion("store_counter >", value, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterGreaterThanOrEqualTo(Integer value) {
			addCriterion("store_counter >=", value, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterLessThan(Integer value) {
			addCriterion("store_counter <", value, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterLessThanOrEqualTo(Integer value) {
			addCriterion("store_counter <=", value, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterIn(List values) {
			addCriterion("store_counter in", values, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterNotIn(List values) {
			addCriterion("store_counter not in", values, "storeCounter");
			return this;
		}

		public Criteria andStoreCounterBetween(Integer value1, Integer value2) {
			addCriterion("store_counter between", value1, value2,
					"storeCounter");
			return this;
		}

		public Criteria andStoreCounterNotBetween(Integer value1, Integer value2) {
			addCriterion("store_counter not between", value1, value2,
					"storeCounter");
			return this;
		}

		public Criteria andLastRefreshTimeIsNull() {
			addCriterion("last_refresh_time is null");
			return this;
		}

		public Criteria andLastRefreshTimeIsNotNull() {
			addCriterion("last_refresh_time is not null");
			return this;
		}

		public Criteria andLastRefreshTimeEqualTo(Date value) {
			addCriterion("last_refresh_time =", value, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeNotEqualTo(Date value) {
			addCriterion("last_refresh_time <>", value, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeGreaterThan(Date value) {
			addCriterion("last_refresh_time >", value, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("last_refresh_time >=", value, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeLessThan(Date value) {
			addCriterion("last_refresh_time <", value, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeLessThanOrEqualTo(Date value) {
			addCriterion("last_refresh_time <=", value, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeIn(List values) {
			addCriterion("last_refresh_time in", values, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeNotIn(List values) {
			addCriterion("last_refresh_time not in", values, "lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeBetween(Date value1, Date value2) {
			addCriterion("last_refresh_time between", value1, value2,
					"lastRefreshTime");
			return this;
		}

		public Criteria andLastRefreshTimeNotBetween(Date value1, Date value2) {
			addCriterion("last_refresh_time not between", value1, value2,
					"lastRefreshTime");
			return this;
		}
	}
}