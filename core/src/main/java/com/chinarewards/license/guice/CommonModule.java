package com.chinarewards.license.guice;

import com.chinarewards.license.common.BaseDao;
import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class CommonModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("license"));
		bind(BaseDao.class);
	}

}
