/**
 * 
 * Exception raised if Order not Found
 */
package com.capgemini.go.exception;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -563781369114628754L;

	public OrderNotFoundException() {

	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
