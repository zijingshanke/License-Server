package com.chinarewards.gwt.license.client.login.request;

import net.customware.gwt.dispatch.shared.Result;

public class LastLoginRoleResponse implements Result {
	private String fal;

	public String getFal() {
		return fal;
	}

	public void setFal(String fal) {
		this.fal = fal;
	}

	public LastLoginRoleResponse() {

	}

	public LastLoginRoleResponse(String fal) {
		this.fal = fal;
	}

}
