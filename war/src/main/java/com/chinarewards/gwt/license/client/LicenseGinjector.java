package com.chinarewards.gwt.license.client;

import com.chinarewards.gwt.license.client.core.PlatformGinjector;
import com.chinarewards.gwt.license.client.dispatch.LicenseStandardDispatchModule;
import com.chinarewards.gwt.license.client.login.module.LoginModule;
import com.chinarewards.gwt.license.client.login.presenter.LoginPresenter;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.google.gwt.inject.client.GinModules;

@GinModules({ MainModule.class, LoginModule.class,
	LicenseStandardDispatchModule.class })
public interface LicenseGinjector extends PlatformGinjector {

	LoginPresenter getLoginPresenter();

	EventBus getEventBus();

	SessionManager getSessionManager();

	ErrorHandler getErrorHandler();

	Main getMain();
}
