package com.chinarewards.gwt.license.client;

import com.chinarewards.gwt.certificate.client.certificate.module.CertificateListModule;
import com.chinarewards.gwt.certificate.client.certificate.module.CertificateModule;
import com.chinarewards.gwt.license.client.breadCrumbs.module.BreadCrumbsModule;
import com.chinarewards.gwt.license.client.core.presenter.DockModule;
import com.chinarewards.gwt.license.client.license.module.LicenseListModule;
import com.chinarewards.gwt.license.client.license.module.LicenseModule;
import com.chinarewards.gwt.license.client.user.module.UserModule;
import com.chinarewards.gwt.license.client.win.WinModule;
import com.google.gwt.inject.client.AbstractGinModule;

public class PresenterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new DockModule());
		install(new UserModule());

		install(new WinModule());
		install(new BreadCrumbsModule());

		install(new LicenseModule());
		install(new LicenseListModule());
		
//		install(new CertificateModule());
//		install(new CertificateListModule());
		
		
	}
}