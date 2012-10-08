package com.chinarewards.gwt.license.client.mvp.impl;

import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultEventBus extends HandlerManager implements EventBus {

	@Inject
	public DefaultEventBus() {
		super(null);
	}

}
