package com.mobicart.util.exception;

public class ProductsExistInDepartmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	String mistake;

	//----------------------------------------------
	// Default constructor - initializes instance variable to unknown

	  public ProductsExistInDepartmentException()
	  {
	    super();             // call superclass constructor
	    mistake = "Products exist in department.";
	  }
	  

	//-----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance variable.

	  public ProductsExistInDepartmentException(String err)
	  {
	    super(err);     // call super class constructor
	    mistake = err;  // save message
	  }
	  

	//------------------------------------------------  
	// public method, callable by exception catcher. It returns the error message.

	  public String getError()
	  {
	    return mistake;
	  }

	
	
}
