package com.chinarewards.gwt.license.model;

import java.io.Serializable;

public class SortingDetailClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2716843754916733856L;

	private String sort;
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
