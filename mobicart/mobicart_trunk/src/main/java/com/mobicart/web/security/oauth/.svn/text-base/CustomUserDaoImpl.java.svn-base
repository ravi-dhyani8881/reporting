package com.mobicart.web.security.oauth;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;
import com.mobicart.model.Authority;
import com.mobicart.model.User;

public class CustomUserDaoImpl extends SqlMapClientDaoSupport implements CustomUserDao {

	public User findByEmail(String username) {
		User user=null;
		user= (User) getSqlMapClientTemplate().queryForObject("users.selectByPrimaryKey",
				new User(username));
		
		if(user==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), username);
		}
		return  user;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public Authority findAuthority(String username){
		Authority authority=null; 
		authority= (Authority) getSqlMapClientTemplate().queryForObject("authorities.selectAuthorityByUsername", username);
		
		if(authority==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), username);
		}
		return authority;
}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Api findAPIByExample(ApiExample example) {
		List<Api> APIList=null;
		 APIList= getSqlMapClientTemplate().queryForList("api.selectByExample", example);
		 
		 if(APIList!=null&&APIList.size()>0){
				return APIList.get(0);
			}else{
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), example); 
			}
		 
	}
	
	

}
