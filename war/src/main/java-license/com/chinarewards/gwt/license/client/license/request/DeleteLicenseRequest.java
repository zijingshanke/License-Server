package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class DeleteLicenseRequest implements Action<DeleteLicenseResponse> {

	private String licenseId;
	private String userId;



	public DeleteLicenseRequest() {
	}

	public DeleteLicenseRequest(String licenseId,String userId) {
		this.licenseId = licenseId;
		this.userId=userId;

	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
