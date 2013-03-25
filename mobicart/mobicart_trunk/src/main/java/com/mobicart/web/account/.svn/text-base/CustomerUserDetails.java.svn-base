/**
 * 
 */
package com.mobicart.web.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.mobicart.model.User;



/**
 * @author jasdeep.singh
 *
 */
public class CustomerUserDetails implements UserDetails {

	
	private static final long serialVersionUID = 7628200009614516513L;
	  
    private final User user;

    private List<GrantedAuthority> AUTHORITIES=new ArrayList<GrantedAuthority>();
    
    
	public CustomerUserDetails(User user) {
		super();
		this.user = user;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		AUTHORITIES.add( new GrantedAuthorityImpl("ROLE_USER"));
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
		return user.isEnabled();
	}

}
