package com.chinarewards.license.guice.sub;

import com.chinarewards.license.dao.license.LicenseDao;
import com.chinarewards.license.service.license.LicenseLogic;
import com.chinarewards.license.service.license.LicenseService;
import com.chinarewards.license.service.license.impl.LicenseLogicImpl;
import com.chinarewards.license.service.license.impl.LicenseServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * License module.
 * 
 * @author yanrui
 */
public class LicenseModule extends AbstractModule {

	@Override
	protected void configure() {

		// License bind
		bind(LicenseDao.class).in(Singleton.class);

		bind(LicenseLogic.class).to(LicenseLogicImpl.class).in(Singleton.class);

		bind(LicenseService.class).to(LicenseServiceImpl.class).in(
				Singleton.class);
	}

}
