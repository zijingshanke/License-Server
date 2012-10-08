package com.chinarewards.gwt.license.client.login.presenter;

import com.chinarewards.gwt.license.client.login.event.LoginEvent;
import com.chinarewards.gwt.license.client.login.event.LoginEvent.LoginStatus;
import com.chinarewards.gwt.license.client.login.event.LoginHandler;
import com.chinarewards.gwt.license.client.login.presenter.LoginPresenter.LoginDisplay;
import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.inject.Inject;

public class LoginPresenterImpl extends BasePresenter<LoginDisplay> implements
		LoginPresenter {

	final SessionManager sessionManager;

	@Inject
	public LoginPresenterImpl(EventBus eventBus, LoginDisplay display,
			SessionManager sessionManager) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
	}

	@Override
	public void bind() {
		registerHandler(display.getLoginClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doLogin();
					}
				}));
		registerHandler(eventBus.addHandler(LoginEvent.getType(),
				new LoginHandler() {
					@Override
					public void onLogin(LoginEvent event) {
						if (event.getStatus() == LoginStatus.LOGIN_FAILED) {
							display.changeImage();
						}
					}
				}));
		registerHandler(display.getVerifyCodeKeyUpHandlers().addKeyUpHandler(
				new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent e) {
						if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							doLogin();
						}
					}
				}));
	}

	protected void doLogin() {
		sessionManager.authenticate(display.getUsername().getValue(), display
				.getPassword().getValue(),display.getVerifyCode().getValue());
	}

}
