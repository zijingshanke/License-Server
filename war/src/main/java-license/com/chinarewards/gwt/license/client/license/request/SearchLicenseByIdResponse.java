package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;

/**
 * @author yanrui
 * @since
 */
public class SearchLicenseByIdResponse implements Result {

	private LicenseVo licenseVo;

	public SearchLicenseByIdResponse() {

	}

	public SearchLicenseByIdResponse(LicenseVo licenseVo) {
		this.licenseVo = licenseVo;
	}

	public LicenseVo getLicense() {
		return licenseVo;
	}

	public void setLicense(LicenseVo licenseVo) {
		this.licenseVo = licenseVo;
	}

}
