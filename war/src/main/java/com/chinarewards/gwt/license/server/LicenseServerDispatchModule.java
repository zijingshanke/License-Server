/**
 * 
 */
package com.chinarewards.gwt.license.server;

import net.customware.gwt.dispatch.server.Dispatch;

import com.google.inject.AbstractModule;

/**
 * 
 * 
 * 
 * @author cyril
 * 
 */
public class LicenseServerDispatchModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Dispatch.class).to(LicenseDispatch.class);
	}

}
