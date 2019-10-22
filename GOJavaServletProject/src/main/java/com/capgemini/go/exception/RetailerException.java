/**
 * 
 * Exception Raised due to Operation of Retailer
 */

package com.capgemini.go.exception;

public class RetailerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RetailerException() {

	}

	public RetailerException(String message) {
		super(message);

	}

	public RetailerException(Throwable cause) {
		super(cause);

	}

	public RetailerException(String message, Throwable cause) {
		super(message, cause);

	}

	public RetailerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
