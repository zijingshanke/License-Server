package com.chinarewards.gwt.license.client.user.module;

import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenter;
import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenter.UserSearchDisplay;
import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenterImpl;
import com.chinarewards.gwt.license.client.user.view.UserSearchWidget;
import com.google.gwt.inject.client.AbstractGinModule;

/**
 * @author Cream
 * @since 0.0.1
 */
public class UserModule extends AbstractGinModule {

	@Override
	protected void configure() {
		// user search.
		bind(UserSearchPresenter.class).to(UserSearchPresenterImpl.class);
		bind(UserSearchDisplay.class).to(UserSearchWidget.class);
	}

}
