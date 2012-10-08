package com.chinarewards.gwt.certificate.client.certificate.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class DeleteCertificateRequest implements Action<DeleteCertificateResponse> {

	private String licenseId;
	private String userId;



	public DeleteCertificateRequest() {
	}

	public DeleteCertificateRequest(String licenseId,String userId) {
		this.licenseId = licenseId;
		this.userId=userId;

	}

	public String getCertificateId() {
		return licenseId;
	}

	public void setCertificateId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
