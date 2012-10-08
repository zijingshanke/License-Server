package com.chinarewards.gwt.certificate.client.certificate.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;

/**
 * @author yanrui
 */
public class SearchCertificateResponse implements Result {

	private List<CertificateClient> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<CertificateClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<CertificateClient> result) {
		this.result = result;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
