package com.chinarewards.license.guice;

import com.chinarewards.license.guice.sub.LicenseModule;
import com.chinarewards.license.guice.sub.UserModule;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule {

	@Override
	protected void configure() {

		install(new CommonModule());

		install(new UserModule());

		install(new LicenseModule());
	}

}
