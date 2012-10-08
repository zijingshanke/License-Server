package com.chinarewards.gwt.license.client.license.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.license.client.license.model.LicenseClient;

/**
 * @author yanrui
 */
public class SearchLicenseResponse implements Result {

	private List<LicenseClient> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<LicenseClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<LicenseClient> result) {
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
