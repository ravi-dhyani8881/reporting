package com.mobicart.web.security.oauth;

public class ConsumerSecretGenerationException extends Throwable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1782650514215462243L;
	
	
	private String mistake;

	// ----------------------------------------------
	// Default constructor - initializes instance variable to unknown

	public ConsumerSecretGenerationException() {
		super(); // call superclass constructor
		mistake = "Categories exist in department";
	}

	// -----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance
	// variable.

	public ConsumerSecretGenerationException(String err) {
		super(err); // call super class constructor
		mistake = err; // save message
	}

	// -----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance
	// variable.

	public ConsumerSecretGenerationException(Throwable e) {
		super(e); // call super class constructor
		mistake = e.getMessage(); // save message
	}

	// ------------------------------------------------
	// public method, callable by exception catcher. It returns the error
	// message.

	public String getError() {
		return mistake;
	}
}
