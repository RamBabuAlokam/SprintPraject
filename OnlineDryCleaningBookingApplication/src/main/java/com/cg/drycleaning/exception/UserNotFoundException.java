package com.cg.drycleaning.exception;

/**************************************************************************
 * @author                 Shaik Abdul Rahiman
 * Description             It is a exception class that handles the exception occurs at 
 *                         service level
 * version                 1.0
 * created date            24-03-2021
 *
 ************************************************************************************/

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super(msg);
	}

}
