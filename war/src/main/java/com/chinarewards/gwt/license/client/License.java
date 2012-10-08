package com.chinarewards.gwt.license.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class License implements EntryPoint {

	public static final String GWT_MODULE_PATH = "/license";
	private final LicenseGinjector injector = GWT
			.create(LicenseGinjector.class);

	@Override
	public void onModuleLoad() {

		injector.getMain().init(RootLayoutPanel.get());

	}

}
