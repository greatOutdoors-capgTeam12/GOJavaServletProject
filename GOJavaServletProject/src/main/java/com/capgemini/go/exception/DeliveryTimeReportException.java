package com.capgemini.go.exception;

public class DeliveryTimeReportException extends Exception {
private static final long serialVersionUID = 2L;
	
	public DeliveryTimeReportException() {
	}

	public DeliveryTimeReportException(String message) {
		super(message);
	}

	public DeliveryTimeReportException(Throwable cause) {
		super(cause);
	}

	public DeliveryTimeReportException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeliveryTimeReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}



