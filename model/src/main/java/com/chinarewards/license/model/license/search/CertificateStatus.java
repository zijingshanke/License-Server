package com.chinarewards.license.model.license.search;

public enum CertificateStatus {

	/* 正常 */
	NORMAL("正常");

	
	private CertificateStatus(String displayName) {
		this.displayName = displayName;
	}

	String displayName;

	public String getDisplayName() {
		return this.displayName;
	}
}
