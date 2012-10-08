package com.chinarewards.gwt.license.model;

import java.io.Serializable;

public class PaginationDetailClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3722778217059725747L;
	private int start;
	private int limit;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
