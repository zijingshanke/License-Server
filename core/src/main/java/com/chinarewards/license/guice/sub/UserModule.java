package com.chinarewards.license.guice.sub;

import com.chinarewards.license.dao.user.UserDao;
import com.chinarewards.license.service.user.UserLogic;
import com.chinarewards.license.service.user.UserService;
import com.chinarewards.license.service.user.impl.UserLogicImpl;
import com.chinarewards.license.service.user.impl.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UserModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserDao.class).in(Singleton.class);
		bind(UserLogic.class).to(UserLogicImpl.class).in(Singleton.class);
		bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
	}

}