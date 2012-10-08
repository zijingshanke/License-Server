package com.chinarewards.gwt.certificate.client.certificate.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class EditCertificateResponse implements Result {

	String licenseId;

	public EditCertificateResponse() {

	}

	public EditCertificateResponse(String id) {

	}

	public String getCertificateId() {
		return licenseId;
	}

	public void setCertificateId(String licenseId) {
		this.licenseId = licenseId;
	}



}
