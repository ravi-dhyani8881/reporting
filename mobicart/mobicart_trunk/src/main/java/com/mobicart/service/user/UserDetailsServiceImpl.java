/**
 * 
 */
package com.mobicart.service.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobicart.dao.UserDAO;
import com.mobicart.model.User;
import com.mobicart.web.account.CustomerUserDetails;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired 
  	private UserDAO userDAO;
 

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		if (logger.isDebugEnabled()) {
			logger.debug(" custom user login - start"); //$NON-NLS-1$
		}
		User user=null;
		try{
		 user = userDAO.findByEmail(username);
		}catch (Exception e) {
			logger.debug("sql exception",e); //$NON-NLS-1$
		}
		if (user == null)
			throw new UsernameNotFoundException("user not found");

		UserDetails returnUserDetails = new CustomerUserDetails(user);
		if (logger.isDebugEnabled()) {
			logger.debug("loadUserByUsername(String) - end"); //$NON-NLS-1$
		}
		return returnUserDetails;
	}
}
