package com.chinarewards.gwt.license.model;

import java.io.Serializable;

/**
 * The base criteria. It contains sortingDetail and pagingDetail.
 * 
 * @author yanxin
 * @since 0.2.0 2011-1-15
 */
public class BaseSearchCriteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278497523682823212L;
	/** pagination **/
	private PaginationDetailClient pagination;
	/** sorting **/
	private SortingDetailClient sorting;
	/** search key value -- eg.name **/
	private String key;

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
