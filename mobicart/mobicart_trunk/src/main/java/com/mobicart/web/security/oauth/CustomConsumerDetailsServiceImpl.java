package com.mobicart.web.security.oauth;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Service;

import com.mobicart.model.Authority;
import com.mobicart.model.ConsumerUserDetails;
import com.mobicart.model.User;

@Service
public class CustomConsumerDetailsServiceImpl implements ConsumerDetailsService, UserDetailsService {
	 
    private CustomUserService customUserService; 
    
	public CustomUserService getCustomUserService() {
		return customUserService;
	}

	public void setCustomUserService(CustomUserService customUserService) {
		this.customUserService = customUserService;
	}

	public CustomConsumerDetailsServiceImpl() {
       
    }
 
    
    /**
     * {@inheritDoc}
     */
    public ConsumerDetails loadConsumerByConsumerKey( String key ) throws ConsumerDetailsNotFoundException,OAuthException {
        ConsumerDetails consumer = customUserService.findConsumerByKey(key);
      
        if ( consumer == null ) {
            throw new OAuthException( "No consumer found for key " + key );
        }
        return consumer;
    }
    
    
 /**
  * {@inheritDoc}
  */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	User user=null;
		user= customUserService.findByEmail(username);
		Authority authority=customUserService.findAuthority(username); 
		if (user == null) {
		      throw new UsernameNotFoundException("no such user: " + username);
	   }
	    return new ConsumerUserDetails(user,authority);
    }
 
}