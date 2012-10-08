package com.chinarewards.gwt.certificate.client.certificate.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;

/**
 * @author yanrui
 * @since
 */
public class SearchCertificateByIdResponse implements Result {

	private CertificateVo licenseVo;

	public SearchCertificateByIdResponse() {

	}

	public SearchCertificateByIdResponse(CertificateVo licenseVo) {
		this.licenseVo = licenseVo;
	}

	public CertificateVo getCertificate() {
		return licenseVo;
	}

	public void setCertificate(CertificateVo licenseVo) {
		this.licenseVo = licenseVo;
	}

}
