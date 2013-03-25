package com.mobicart.web.security.oauth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth.provider.ConsumerDetails;

import com.mobicart.model.Authority;
import com.mobicart.model.User;

public interface CustomUserService {
	
	/**
	 * Finds a user by their username e.g email.
	 * @param username the user's username used to login
	 * @return User a populated user object
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *         exception thrown when user not found
	 */
	public User findByEmail(String username) throws UsernameNotFoundException;
	
	
	
	/**
	 * Finds an Authority instance for a user an email
	 * @param username {@link User} username
	 * @return {@link Authority} instance
	 */
	public Authority findAuthority(String username);
	
	
	/**
	 * Finds ConsumerDetails by their key.
	 * @param username the user's username used to login
	 * @return User a populated user object
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *         exception thrown when user not found
	 */
	public ConsumerDetails findConsumerByKey(String key) throws ConsumerDetailsNotFoundException;
}
