package com.chinarewards.gwt.license.client.license.model;

import com.chinarewards.gwt.license.model.PaginationDetailClient;
import com.chinarewards.gwt.license.model.SortingDetailClient;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author yanrui
 */
public class LicenseCriteria implements IsSerializable {
	public LicenseCriteria() {
	}

	public static enum LicenseStatus {

		NORMAL("正常");

		private final String displayName;

		LicenseStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private String id;

	private String corporationName;

	public LicenseStatus getStatus() {
		return status;
	}

	public void setStatus(LicenseStatus status) {
		this.status = status;
	}

	private LicenseStatus status;

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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

}
