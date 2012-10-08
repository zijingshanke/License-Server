/**
 * 
 */
package com.chinarewards.gwt.license.client.user.model;

import java.io.Serializable;

/**
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserSearchVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7198792977316444231L;

	private String status;

	private String accountName;

	private String mobile;

	private String email;

	private String enterpriseId;

	// for paging
	int start = 0;
	int length = 10;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the enterpriseId
	 */
	public String getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * @param enterpriseId
	 *            the enterpriseId to set
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserSearchVo [status=" + status + ", accountName="
				+ accountName + ", mobile=" + mobile + ", email=" + email
				+ ", enterpriseId=" + enterpriseId + ", start=" + start
				+ ", length=" + length + "]";
	}

}
