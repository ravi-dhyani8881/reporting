package com.mobicart.web.security.oauth;

import org.springframework.security.core.AuthenticationException;


/**
 * Thrown if an {@link CustomUserDetailsService} implementation cannot locate a {@link ConsumerDeatils} by its key.
 *
 * @author Jasdeep Singh
 */
public class ConsumerDetailsNotFoundException extends AuthenticationException {

   

	/**
	 * 
	 */
	private static final long serialVersionUID = -3765139976692209504L;

	/**
     * Constructs a <code>ConsumerDetailsNotFoundException</code> with the specified
     * message.
     *
     * @param msg the detail message.
     */
    public ConsumerDetailsNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>ConsumerDetailsNotFoundException</code>, making use of the <tt>extraInformation</tt>
     * property of the superclass.
     *
     * @param msg the detail message
     * @param extraInformation additional information such as the username.
     */
    public ConsumerDetailsNotFoundException(String msg, Object extraInformation) {
        super(msg, extraInformation);
    }

    /**
     * Constructs a <code>ConsumerDetailsNotFoundException</code> with the specified
     * message and root cause.
     *
     * @param msg the detail message.
     * @param t root cause
     */
    public ConsumerDetailsNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    
    
}
