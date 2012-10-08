package com.chinarewards.gwt.license.client.license.module;

import com.chinarewards.gwt.license.client.license.presenter.LicensePresenter;
import com.chinarewards.gwt.license.client.license.presenter.LicensePresenter.LicenseDisplay;
import com.chinarewards.gwt.license.client.license.presenter.LicensePresenterImpl;
import com.chinarewards.gwt.license.client.license.presenter.LicenseViewPresenter;
import com.chinarewards.gwt.license.client.license.presenter.LicenseViewPresenter.LicenseViewDisplay;
import com.chinarewards.gwt.license.client.license.presenter.LicenseViewPresenterImpl;
import com.chinarewards.gwt.license.client.license.view.LicenseViewWidget;
import com.chinarewards.gwt.license.client.license.view.LicenseWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class LicenseModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LicensePresenter.class).to(LicensePresenterImpl.class);
		bind(LicenseDisplay.class).to(LicenseWidget.class);

		bind(LicenseViewPresenter.class).to(LicenseViewPresenterImpl.class);
		bind(LicenseViewDisplay.class).to(LicenseViewWidget.class);
	}
}
