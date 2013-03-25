package com.mobicart.web.security.oauth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.token.OAuthAccessProviderToken;

public class ConsumerBasedAuthenticationHandler implements OAuthAuthenticationHandler {
	
	@Override
    public Authentication createAuthentication( HttpServletRequest request, ConsumerAuthentication authentication, OAuthAccessProviderToken authToken ) {
        return authentication;
    }
 
}