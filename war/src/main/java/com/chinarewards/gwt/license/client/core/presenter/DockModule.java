package com.chinarewards.gwt.license.client.core.presenter;

import com.chinarewards.gwt.license.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.license.client.core.view.DockWidget;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DockModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DockPresenter.class).to(DockPresenterImpl.class).in(
				Singleton.class);
		bind(DockDisplay.class).to(DockWidget.class);
	}
}
