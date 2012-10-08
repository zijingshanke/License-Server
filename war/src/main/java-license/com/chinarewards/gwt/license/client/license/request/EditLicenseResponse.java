package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class EditLicenseResponse implements Result {

	String licenseId;

	public EditLicenseResponse() {

	}

	public EditLicenseResponse(String id) {

	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}



}
