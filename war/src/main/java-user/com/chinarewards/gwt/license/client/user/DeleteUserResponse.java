package com.chinarewards.gwt.license.client.user;

import net.customware.gwt.dispatch.shared.Result;

/**
 * 
 * @author nicho
 * @since 2012年1月13日 15:36:33
 */
public class DeleteUserResponse implements Result {

	private String result;

	public DeleteUserResponse() {

	}

	public DeleteUserResponse(String result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

}
