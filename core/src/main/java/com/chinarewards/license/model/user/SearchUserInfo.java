package com.chinarewards.license.model.user;


import com.chinarewards.license.domain.user.SysUser;

public class SearchUserInfo {


	private SysUser user;
	private String enterpriseName;
	private double balance;
	
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the user
	 */
	public SysUser getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(SysUser user) {
		this.user = user;
	}

	/**
	 * @return the enterpriseName
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/**
	 * @param enterpriseName
	 *            the enterpriseName to set
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
}
