package com.chinarewards.gwt.license.client.login.request;

import net.customware.gwt.dispatch.shared.Action;

public class LoginRequest implements Action<LoginResponse> {

	private String userName;
	private String pwd;
	private String verifyCode;


	public LoginRequest(){
		
	}
	
	public LoginRequest(String userName, String pwd,String verifyCode) {
		this.userName = userName;
		this.pwd = pwd;
		this.verifyCode = verifyCode;
	}

	public String getUserName() {
		return userName;
	}

	public String getPwd() {
		return pwd;
	}
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
