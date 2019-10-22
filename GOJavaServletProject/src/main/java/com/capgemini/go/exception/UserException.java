/**
 * 
 * User Exception
 */

package com.capgemini.go.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = 2L;

	public UserException() {

	}

	public UserException(String message) {
		super(message);

	}

	public UserException(Throwable arg0) {
		super(arg0);

	}

	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public UserException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
