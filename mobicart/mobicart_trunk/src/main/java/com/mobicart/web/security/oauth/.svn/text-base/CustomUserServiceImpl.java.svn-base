package com.mobicart.web.security.oauth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;

import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;
import com.mobicart.model.Authority;
import com.mobicart.model.User;

public class CustomUserServiceImpl implements CustomUserService {

	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	CustomUserDao customUserDao;
	
	
	public CustomUserDao getCustomUserDao() {
		return customUserDao;
	}



	public void setCustomUserDao(CustomUserDao customUserDao) {
		this.customUserDao = customUserDao;
	}


	/**
	 * {@inheritDoc}
	 */

	public User findByEmail(String username) throws UsernameNotFoundException {
		User user=null;
		try{ 
			user=customUserDao.findByEmail(username);
			
			 
		}catch (ObjectRetrievalFailureException e) {
			logger.warn(e.getMessage());
			 throw new UsernameNotFoundException("user '" + username + "' not found...");
		}catch (Exception e) {
			logger.warn(e.getMessage());
			 throw new UsernameNotFoundException("user '" + username + "' not found...");
		}
		
		/*if(user==null){
			 throw new UsernameNotFoundException("user '" + username + "' not found...");
		}*/
		
		return user;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	public Authority findAuthority(String username) {
		return customUserDao.findAuthority(username);
	}



	/**
	 * {@inheritDoc}
	 */

	public ConsumerDetails findConsumerByKey(String key) throws ConsumerDetailsNotFoundException{
		
		ConsumerDetails consumerDetails=null;
		try{
		//get user 
		 User user= customUserDao.findByEmail(key);
		 
		 ApiExample example=new ApiExample();
		 example.createCriteria().andUserIdEqualTo(user.getId());
		 Api api=customUserDao.findAPIByExample(example);
		 String consumerSecret=api.getOauthSecret();
		 
		 consumerDetails=createConsumerDetails(user.getUsername(), user.getUsername(), consumerSecret);
		}catch (ObjectRetrievalFailureException e) {
			logger.warn(e.getMessage());
			 throw new ConsumerDetailsNotFoundException("consumer  '" + key + "' not found...");
		}catch (DataAccessException e) {
			logger.warn(e.getMessage());
			 throw new ConsumerDetailsNotFoundException("consumer  '" + key + "' not found...");
		}catch (Exception e) {
			logger.warn(e.getMessage());
			 throw new ConsumerDetailsNotFoundException("consumer  '" + key + "' not found...");
		}
		 if(consumerDetails==null){
			 throw new ConsumerDetailsNotFoundException("consumer  '" + key + "' not found...");
		 }
		
		 return consumerDetails;
	}
	
	/**
	 * Create Consumer details
	 * @param consumerKey
	 * @param consumerName
	 * @param consumerSecret
	 * @return {@link ConsumerDetails} instance
	 */
	
	 private ConsumerDetails createConsumerDetails( String consumerKey, String consumerName, String consumerSecret ) {
	       
	    	SharedConsumerSecret secret = new SharedConsumerSecret( consumerSecret );
	 
	    	List<GrantedAuthority> authorities=  new ArrayList<GrantedAuthority>();
	    	authorities.add(new GrantedAuthorityImpl("ROLE_OAUTH_USER")) ;
	    	
	        BaseConsumerDetails bcd = new BaseConsumerDetails();
	        bcd.setConsumerKey( consumerKey );
	        bcd.setConsumerName( consumerName );
	        bcd.setSignatureSecret( secret );
	        bcd.setAuthorities( authorities );
	 
	    
	        bcd.setRequiredToObtainAuthenticatedToken( false );
	 
	        return bcd;
	    }

	
	
}
