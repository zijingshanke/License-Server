package com.chinarewards.license.model.common;

import java.util.Date;

public class DateRangeModel {

	private Date from;

	private Date to;

	public DateRangeModel() {
	}

	public DateRangeModel(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "DateRangeModel [from=" + from + ", to=" + to + "]";
	}

}
