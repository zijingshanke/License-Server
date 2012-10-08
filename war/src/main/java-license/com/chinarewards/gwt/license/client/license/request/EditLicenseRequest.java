package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class EditLicenseRequest implements Action<EditLicenseResponse> {

	String licenseId;
	String nowUserId;
	private LicenseVo licenseVo;
	private UserSession userSession;

	public EditLicenseRequest(LicenseVo licenseVo, UserSession userSession) {
		this.licenseVo = licenseVo;
		this.userSession = userSession;
	}

	/**
	 * For serialization
	 */
	public EditLicenseRequest() {
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}

	public LicenseVo getLicenseVo() {
		return licenseVo;
	}

	public void setLicenseVo(LicenseVo licenseVo) {
		this.licenseVo = licenseVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}
