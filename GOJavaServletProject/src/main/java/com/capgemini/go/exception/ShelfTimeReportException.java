package com.capgemini.go.exception;

public class ShelfTimeReportException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ShelfTimeReportException() {
	}

	public ShelfTimeReportException(String message) {
		super(message);
	}

	public ShelfTimeReportException(Throwable cause) {
		super(cause);
	}

	public ShelfTimeReportException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShelfTimeReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
