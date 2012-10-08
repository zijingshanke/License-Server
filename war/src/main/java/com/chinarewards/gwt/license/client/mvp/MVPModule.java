package com.chinarewards.gwt.license.client.mvp;

import com.chinarewards.gwt.license.client.mvp.impl.DefaultEventBus;
import com.chinarewards.gwt.license.client.mvp.impl.WindowAlertErrorHandler;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class MVPModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(ErrorHandler.class).to(WindowAlertErrorHandler.class).in(
				Singleton.class);
	}
}