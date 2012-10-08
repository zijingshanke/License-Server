package com.chinarewards.gwt.certificate.client.certificate.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author yanrui
 */
public class DeleteCertificateResponse implements Result {

	private String total;

	public DeleteCertificateResponse() {
	}

	public DeleteCertificateResponse(String total) {
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
