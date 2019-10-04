/***
 * 
 * Product Not Found Exception
 */

package com.capgemini.go.exception;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -6641585315654856422L;

	public ProductNotFoundException() {

	}

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
