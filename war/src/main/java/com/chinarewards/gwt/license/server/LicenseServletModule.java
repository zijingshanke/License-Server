package com.chinarewards.gwt.license.server;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.gwt.license.client.License;
import com.chinarewards.gwt.license.server.login.LoginServiceImpl;
import com.chinarewards.license.guice.CoreModule;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

/**
 * 
 * 
 */
public class LicenseServletModule extends ServletModule {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void configureServlets() {

		logger.info("Configuring servlet modules...");

		serve(License.GWT_MODULE_PATH + "/dispatch").with(
				GuiceStandardDispatchServlet.class);
		bind(InitServlet.class).in(Singleton.class);
		serve(License.GWT_MODULE_PATH + "/donottouchme")
				.with(InitServlet.class);
		bind(LoginServiceImpl.class).in(Singleton.class);
		serve(License.GWT_MODULE_PATH + "/loginService").with(
				LoginServiceImpl.class);

		install(new CoreModule());

		logger.info("Configuring servlet modules completed.");

	}

}
