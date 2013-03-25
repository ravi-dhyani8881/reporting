package com.mobicart.util.exception;

public class CategoriesExistInDepartmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3667898365339702358L;

	
	

	String mistake;

	//----------------------------------------------
	// Default constructor - initializes instance variable to unknown

	  public CategoriesExistInDepartmentException()
	  {
	    super();             // call superclass constructor
	    mistake = "Categories exist in department";
	  }
	  

	//-----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance variable.

	  public CategoriesExistInDepartmentException(String err)
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
