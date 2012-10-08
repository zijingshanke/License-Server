package com.chinarewards.gwt.license.client.login.module;

import com.chinarewards.gwt.license.client.login.presenter.LoginPresenter;
import com.chinarewards.gwt.license.client.login.presenter.LoginPresenter.LoginDisplay;
import com.chinarewards.gwt.license.client.login.presenter.LoginPresenterImpl;
import com.chinarewards.gwt.license.client.login.view.LoginWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class LoginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LoginPresenter.class).to(LoginPresenterImpl.class);
		bind(LoginDisplay.class).to(LoginWidget.class);
	}

}
