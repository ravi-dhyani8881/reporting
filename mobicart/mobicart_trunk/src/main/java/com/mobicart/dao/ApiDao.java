package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;

public interface ApiDao extends GenericDAO<Api, Long>  {
	
	/**
	 * Find Api instance by example
	 * @param apiExample
	 * @return List of {@link Api} instances 
	 */
	List<Api> findByExample(ApiExample apiExample);
	 /**
	 * Get auth secret key by user name
	 * @param userName
	 * @return   AuthSecretKey
	 */
    public String getAuthSecretByUserName(String userName);
    /**
	 * Get auth secret key by domain url
	 * @param domainUrl
	 * @return   AuthSecretKey
	 */
    public String getAuthSecretByDomainUrl(String domainUrl);
    /**
	 * Get auth secret key by domain user id
	 * @param domainUrl
	 * @return   AuthSecretKey
	 */
    public String getAuthSecretByUserId(Long userId);
}