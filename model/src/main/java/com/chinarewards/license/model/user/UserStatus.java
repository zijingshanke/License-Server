package com.chinarewards.license.model.user;

/**
 * The status of account.
 * 
 * @author yanxin
 * @since 1.0.0
 * 
 */
public enum UserStatus {

	Active("已激活"), Inactive("已注销");

	private UserStatus(String displayName) {
		this.displayName = displayName;
	}

	String displayName;

	public String getDisplayName() {
		return this.displayName;
	}
}
