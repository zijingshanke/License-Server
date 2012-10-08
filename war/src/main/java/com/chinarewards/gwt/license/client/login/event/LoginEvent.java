package com.chinarewards.gwt.license.client.login.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginHandler> {

	private static Type<LoginHandler> TYPE;

	public static Type<LoginHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<LoginHandler>());
	}

	public static enum LoginStatus {
		LOGIN_OK, LOGIN_FAILED, LOGOUT, LOGIN_OK_STAFF,
	}

	private final LoginStatus status;
	private final Throwable exception;

	public LoginEvent(LoginStatus status) {
		this.status = status;
		this.exception = null;
	}

	public LoginEvent(LoginStatus status, Throwable t) {
		this.status = status;
		this.exception = t;
	}

	public LoginStatus getStatus() {
		return status;
	}

	@Override
	public final Type<LoginHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(LoginHandler handler) {
		handler.onLogin(this);
	}

	public Throwable getException() {
		return exception;
	}
}
