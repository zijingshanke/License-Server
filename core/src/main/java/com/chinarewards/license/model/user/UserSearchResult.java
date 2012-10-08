package com.chinarewards.license.model.user;


import java.util.List;

/**
 * The result of searching main-accounts using paging.
 * 
 * @author yanxin
 * @since 0.0.1 2010-9-26
 */
public class UserSearchResult {

	List<SearchUserInfo> result;
	int total;

	public UserSearchResult() {

	}

	public UserSearchResult(List<SearchUserInfo> result, int total) {
		this.result = result;
		this.total = total;
	}

	/**
	 * @return the result
	 */
	public List<SearchUserInfo> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<SearchUserInfo> result) {
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
