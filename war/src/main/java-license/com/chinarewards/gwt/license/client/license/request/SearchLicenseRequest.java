package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.license.client.license.model.LicenseCriteria;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseResponse;
import com.chinarewards.gwt.license.model.user.UserRoleVo;

/**
 * @author yanrui
 */
public class SearchLicenseRequest implements Action<SearchLicenseResponse> {

	private LicenseCriteria license;
	private String corporationId;
	private UserRoleVo[] userRoles;
	private String userId;



	public SearchLicenseRequest() {
	}

	public SearchLicenseRequest(LicenseCriteria license,String corporationId,UserRoleVo[] userRoles,String userId) {
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

	public LicenseCriteria getLicense() {
		return license;
	}

	public void setLicense(LicenseCriteria license) {
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
