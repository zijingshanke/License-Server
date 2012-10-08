package com.chinarewards.gwt.license.client.core.ui.event;

import com.google.gwt.event.shared.EventHandler;

public interface PlatformInitHandler extends EventHandler {

	void onInit(boolean loggedIn);

}
