package com.chinarewards.gwt.license.client.login.presenter;

import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Login Presenter, this will emit a LoginEvent upon the login button being clicked.
 * 
 * @author kmtong
 * 
 */
public interface LoginPresenter extends Presenter<LoginPresenter.LoginDisplay> {

	/**
	 * Logical UI structure for the Login Window/Widget
	 * 
	 * @author kmtong
	 * 
	 */
	public static interface LoginDisplay extends Display {

		HasClickHandlers getLoginClickHandlers();

		HasValue<String> getUsername();

		HasValue<String> getPassword();
		public void changeImage();

		public HasKeyUpHandlers getVerifyCodeKeyUpHandlers();

		public HasValue<String> getVerifyCode();
	}

}
