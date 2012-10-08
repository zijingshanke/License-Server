/**
 * 
 */
package com.chinarewards.gwt.certificate.client.certificate.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.license.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class EditCertificateRequest implements Action<EditCertificateResponse> {

	String licenseId;
	String nowUserId;
	private CertificateVo licenseVo;
	private UserSession userSession;

	List<String> staffIds;

	public EditCertificateRequest(CertificateVo licenseVo, UserSession userSession) {
		this.licenseVo = licenseVo;
		this.userSession = userSession;
	}

	/**
	 * For serialization
	 */
	public EditCertificateRequest() {
	}


	public String getCertificateId() {
		return licenseId;
	}

	public void setCertificateId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}

	public CertificateVo getCertificateVo() {
		return licenseVo;
	}

	public void setCertificateVo(CertificateVo licenseVo) {
		this.licenseVo = licenseVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<String> getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(List<String> staffIds) {
		this.staffIds = staffIds;
	}

}
