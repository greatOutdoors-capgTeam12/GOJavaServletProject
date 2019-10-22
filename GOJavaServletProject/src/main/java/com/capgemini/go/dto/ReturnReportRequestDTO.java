package com.capgemini.go.dto;

import java.util.Date;
import java.util.List;

public class ReturnReportRequestDTO {

	private int mode;
	private int reason;
	private List<Date> dateInterval;

	/**
	 * @param mode
	 * @param reason
	 * @param dateInterval
	 */
	public ReturnReportRequestDTO( List<Date> dateInterval,int mode, int reason) {
		super();
		this.mode = mode;
		this.reason = reason;
		this.dateInterval = dateInterval;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public List<Date> getDateInterval() {
		return dateInterval;
	}

	public void setDateInterval(List<Date> dateInterval) {
		this.dateInterval = dateInterval;
	}

}
