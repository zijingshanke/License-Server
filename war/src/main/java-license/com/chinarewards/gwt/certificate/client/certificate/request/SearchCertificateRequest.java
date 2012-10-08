package com.chinarewards.gwt.certificate.client.certificate.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria;
import com.chinarewards.gwt.license.model.user.UserRoleVo;

/**
 * @author yanrui
 */
public class SearchCertificateRequest implements Action<SearchCertificateResponse> {

	private CertificateCriteria license;
	private String corporationId;
	private UserRoleVo[] userRoles;
	private String userId;



	public SearchCertificateRequest() {
	}

	public SearchCertificateRequest(CertificateCriteria license,String corporationId,UserRoleVo[] userRoles,String userId) {
		this.license = license;
		this.corporationId=corporationId;
		this.userRoles=userRoles;
		this.userId=userId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public CertificateCriteria getCertificate() {
		return license;
	}

	public void setCertificate(CertificateCriteria license) {
		this.license = license;
	}

	public UserRoleVo[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoleVo[] userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
