package com.chinarewards.gwt.license.client;

import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.support.impl.CookieSessionManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Define the main initializer
 * 
 * @author kmtong
 * 
 */
public class MainModule extends AbstractGinModule {

	protected void configure() {
		bind(Main.class).to(MainImpl.class);
		bind(SessionManager.class).to(CookieSessionManager.class).in(Singleton.class);
	}

}
