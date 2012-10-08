package com.chinarewards.gwt.license.client.license.module;


import com.chinarewards.gwt.license.client.license.presenter.LicenseListPresenter;
import com.chinarewards.gwt.license.client.license.presenter.LicenseListPresenter.LicenseListDisplay;
import com.chinarewards.gwt.license.client.license.presenter.LicenseListPresenterImpl;
import com.chinarewards.gwt.license.client.license.view.LicenseListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class LicenseListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LicenseListPresenter.class).to(LicenseListPresenterImpl.class);
		bind(LicenseListDisplay.class).to(LicenseListWidget.class);
	}

}
