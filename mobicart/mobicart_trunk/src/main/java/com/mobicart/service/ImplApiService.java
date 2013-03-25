package com.mobicart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobicart.dao.ApiDao;
import com.mobicart.model.ApiExample;

@Service
public class ImplApiService implements ApiService {
	
	@Autowired
	ApiDao apiDao;
	
	@Override
	public String getAuthSecretByUserName(String userName) {
	return apiDao.getAuthSecretByUserName(userName);
	}

	@Override
	public String getAuthSecretByDomainUrl(String domainUrl) {
	return  apiDao.getAuthSecretByDomainUrl(domainUrl);
	}

	@Override
	public String getAuthSecretByUserId(long userId) {
		return  apiDao.getAuthSecretByUserId(userId);
	}
	 
}
