package com.chinarewards.gwt.certificate.client.certificate.model;

import com.chinarewards.gwt.license.model.PaginationDetailClient;
import com.chinarewards.gwt.license.model.SortingDetailClient;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author yanrui
 */
public class CertificateCriteria implements IsSerializable {
	public CertificateCriteria() {
	}

	public static enum CertificateStatus {

		/* 上架 */
		NORMAL("正常"),

		/* 下架 */
		SHELF("下架");

		private final String displayName;

		CertificateStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private String id;

	private String name;
	private  int integral;

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public CertificateStatus getStatus() {
		return status;
	}

	public void setStatus(CertificateStatus status) {
		this.status = status;
	}

	private CertificateStatus status;

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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
