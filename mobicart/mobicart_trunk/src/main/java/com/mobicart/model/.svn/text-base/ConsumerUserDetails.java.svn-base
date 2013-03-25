package com.mobicart.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ConsumerUserDetails for consumer key value pair 
 * @author jasdeep.singh
 *
 */
public class ConsumerUserDetails implements UserDetails {

	private static final long serialVersionUID = 4659095592414126919L;

	/**
	 * Collection of granted authorities
	 */
	private static Collection<GrantedAuthority> AUTHORITIES = null; 
	
	private final User user;

	

	 public ConsumerUserDetails(User user,Authority authority) {
		    this.user = user;
		    AUTHORITIES=new ArrayList<GrantedAuthority>();
			AUTHORITIES.add(new GrantedAuthorityImpl(authority.getAuthority()));
		  }
	 
	
	public Collection<GrantedAuthority> getAuthorities() {
		return AUTHORITIES;
	}

	public User getWrappedUser() {
	    return user;
	  }
	
	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
