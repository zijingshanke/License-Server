package com.chinarewards.gwt.license.client.remote.login;

import com.chinarewards.gwt.license.client.support.UserSession;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface LoginServiceAsync {

	void authLogin(String username, String password, String verifyCode,
			AsyncCallback<UserSession> callback);

}
