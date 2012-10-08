package com.chinarewards.gwt.license.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.util.Modules;

/**
 * 
 * 
 *
 */
public class LicenseServletConfig extends GuiceServletContextListener {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected Injector getInjector() {

		logger.debug("Obtaining guice injector in GuiceServletContextListener");

		Injector injector = Guice.createInjector(new LoggingModule(),
				new LicenseServletModule(), Modules.override(new ActionModule())
						.with(new LicenseServerDispatchModule()));
		// run persist service
		PersistService persitsService = injector.getInstance(PersistService.class);
		persitsService.start();
		return injector;
	}

}
