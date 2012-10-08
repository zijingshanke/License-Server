package com.chinarewards.gwt.license.client.remote.login;

import com.chinarewards.gwt.license.client.support.UserSession;
import com.chinarewards.gwt.license.model.ClientException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {

	public UserSession authLogin(String username, String password,String verifyCode) throws ClientException;

	
}
