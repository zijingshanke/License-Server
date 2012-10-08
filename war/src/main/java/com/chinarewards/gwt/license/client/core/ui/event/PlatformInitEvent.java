package com.chinarewards.gwt.license.client.core.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public class PlatformInitEvent extends GwtEvent<PlatformInitHandler> {

	private static Type<PlatformInitHandler> TYPE = new Type<PlatformInitHandler>();

	final boolean loggedIn;

	public static Type<PlatformInitHandler> getType() {
		return TYPE;
	}

	public PlatformInitEvent(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	protected void dispatch(PlatformInitHandler handler) {
		handler.onInit(loggedIn);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PlatformInitHandler> getAssociatedType() {
		return TYPE;
	}

}
