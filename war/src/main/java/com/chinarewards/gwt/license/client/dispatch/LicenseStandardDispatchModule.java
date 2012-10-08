package com.chinarewards.gwt.license.client.dispatch;

import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;

import com.google.gwt.inject.client.AbstractGinModule;

public class LicenseStandardDispatchModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new StandardDispatchModule(LicenseDispatchExceptionHandler.class));
	}
}
