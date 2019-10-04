package com.capgemini.go.exception;

/**
 * 
 * Exception rise due to connection
 * @author agchandr
 *
 */
public class ConnectionException extends Exception {

	public ConnectionException() {
		
	}

	public ConnectionException(String message) {
		super(message);
		
	}

	public ConnectionException(Throwable cause) {
		super(cause);
		
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
