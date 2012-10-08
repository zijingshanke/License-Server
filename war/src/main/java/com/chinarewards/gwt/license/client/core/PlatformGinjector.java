package com.chinarewards.gwt.license.client.core;

import com.chinarewards.gwt.license.client.core.presenter.PresentersGinjector;
import com.chinarewards.gwt.license.client.mvp.MVPGinjector;
import com.google.gwt.inject.client.GinModules;
import com.google.inject.name.Named;

@GinModules( { PlatformModule.class })
public interface PlatformGinjector extends MVPGinjector, PresentersGinjector {

	Platform getPlatform();

	@Named("admin")
	PluginSet getPluginSetAdmin();
	
	@Named("staff")
	PluginSet getPluginSetStaff();
}
