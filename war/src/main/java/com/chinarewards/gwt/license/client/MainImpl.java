package com.chinarewards.gwt.license.client;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.core.ui.event.PlatformInitEvent;
import com.chinarewards.gwt.license.client.core.ui.event.PlatformInitHandler;
import com.chinarewards.gwt.license.client.login.event.LoginEvent;
import com.chinarewards.gwt.license.client.login.event.LoginHandler;
import com.chinarewards.gwt.license.client.login.presenter.LoginPresenter;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.win.Win;
import com.chinarewards.gwt.license.model.user.UserRoleVo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class MainImpl implements Main, PlatformInitHandler, LoginHandler {

	final LicenseGinjector injector;
	final SessionManager sessionManager;
	final EventBus eventBus;
	final Win win;
	RootLayoutPanel rootLayoutPanel;
	LoginPresenter loginPresenter;
	final DispatchAsync dispatchAsync;

	@Inject
	public MainImpl(LicenseGinjector injector, SessionManager sessionManager,
			EventBus eventBus, Win win, DispatchAsync dispatchAsync) {
		this.injector = injector;
		this.sessionManager = sessionManager;
		this.eventBus = eventBus;
		this.win = win;
		this.loginPresenter = injector.getLoginPresenter();
		this.dispatchAsync = dispatchAsync;
	}

	public void init(RootLayoutPanel panel) {
		GWT.log("Main Initializing...");
		this.rootLayoutPanel = panel;
		eventBus.addHandler(PlatformInitEvent.getType(), this);
		eventBus.addHandler(LoginEvent.getType(), this);

		sessionManager.initialize();

		GWT.log("sessionManager Initializing Success...");
	}

	public void onInit(boolean loggedIn) {
		rootLayoutPanel.clear();
		if (!loggedIn) {
			loginPresenter.bind();
			rootLayoutPanel.add(loginPresenter.getDisplay().asWidget());
		} else {
			loginPresenter.unbind();
			UserRoleVo role = sessionManager.getSession().getLastLoginRole();
			if (role == UserRoleVo.CORP_ADMIN) {
				injector.getPlatform().initialize(injector.getPluginSetAdmin(),
						rootLayoutPanel);
			}
		}
	}

	public void onLogin(LoginEvent event) {
		switch (event.getStatus()) {
		case LOGIN_OK:
			rootLayoutPanel.clear();
			loginPresenter.unbind();
			injector.getPlatform().initialize(injector.getPluginSetAdmin(),
					rootLayoutPanel);
			break;
		case LOGIN_OK_STAFF:
			rootLayoutPanel.clear();
			loginPresenter.unbind();
			injector.getPlatform().initialize(injector.getPluginSetStaff(),
					rootLayoutPanel);
			break;	
		case LOGIN_FAILED:
			win.alert("登录失败，请重试！");
			break;
		case LOGOUT:
			sessionManager.resetLogin();
			Window.Location.reload();
			break;

		}
	}

}
