package com.mobicart.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;

public class ApiDaoImpl extends SqlMapClientDaoSupport implements ApiDao  {
	/**
	 * {@inheritDoc}
	 */
	public Long create(Api newInstance) {
			return (Long) getSqlMapClientTemplate().insert("api.insertSelective", newInstance);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Api find(Long id) {
		Api api=null;
			 api=(Api) getSqlMapClientTemplate().queryForObject("api.selectByPrimaryKey", new Api(id));
			 	
		if(api==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
		}
		return  api;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Api> findAll() {
		List<Api> APIList=null;
			 APIList= getSqlMapClientTemplate().queryForList("api.selectAll", null);
			 if(APIList==null){
					throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
				}	 
		return APIList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Api transientObject) {
		 getSqlMapClientTemplate().update("api.updateByPrimaryKeySelective", transientObject);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Api persistentObject) {
		getSqlMapClientTemplate().delete("api.deleteByPrimaryKey", persistentObject);

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Api> findByExample(ApiExample apiExample) {
		List<Api> APIList=null;
			 APIList= getSqlMapClientTemplate().queryForList("api.selectByExample", apiExample);
			 if(APIList==null){
					throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
				}	
		return APIList;
	}

	@Override
	public String getAuthSecretByUserName(String userName) {
		 
		return (String)getSqlMapClientTemplate().queryForObject("api.getAuthSecretByUserName", userName);
	}

	@Override
	public String getAuthSecretByDomainUrl(String domainUrl) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("api.getAuthSecretByDomainUrl", domainUrl);
	}

	@Override
	public String getAuthSecretByUserId(Long userId) {
		// TODO Auto-generated method stub
		 return (String)getSqlMapClientTemplate().queryForObject("api.getAuthSecretByUserId", userId);
	}
	
}
