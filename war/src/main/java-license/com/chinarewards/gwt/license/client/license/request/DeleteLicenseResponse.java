package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author yanrui
 */
public class DeleteLicenseResponse implements Result {

	private String total;

	public DeleteLicenseResponse() {
	}

	public DeleteLicenseResponse(String total) {
		this.total = total;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
}
