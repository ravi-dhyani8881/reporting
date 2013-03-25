package com.mobicart.web.security.oauth;

import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;
import com.mobicart.model.Authority;
import com.mobicart.model.User;

public interface CustomUserDao {
	/**
	 * Find User by Email
	 * @param username
	 * @return {@link User} instance
	 */
	User findByEmail(String username);
	
	/**
	 * Find Authority by Email
	 * @param username
	 * @return {@link Authority} instance
	 */
	Authority findAuthority(String username);
	
	/**
	 * Find Api details for auth by example
	 * @param example
	 * @return {@link Api} instance
	 */
	Api findAPIByExample(ApiExample example);
}
