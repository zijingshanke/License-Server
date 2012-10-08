package com.chinarewards.gwt.license.client.user;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.license.client.user.model.UserSearchResultVo;

/**
 * Models the response after user process request.
 * 
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserSearchResponse implements Result {

	private UserSearchResultVo result;

	public UserSearchResponse() {

	}

	public UserSearchResponse(UserSearchResultVo result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public UserSearchResultVo getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(UserSearchResultVo result) {
		this.result = result;
	}

}
