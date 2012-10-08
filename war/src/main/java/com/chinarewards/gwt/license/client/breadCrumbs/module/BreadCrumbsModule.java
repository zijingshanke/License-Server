package com.chinarewards.gwt.license.client.breadCrumbs.module;

import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter.BreadCrumbsDisplay;
import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenterImpl;
import com.chinarewards.gwt.license.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.license.client.breadCrumbs.ui.impl.BreadCrumbsMenuImpl;
import com.chinarewards.gwt.license.client.breadCrumbs.view.BreadCrumbsWidget;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BreadCrumbsModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BreadCrumbsPresenter.class).to(BreadCrumbsPresenterImpl.class).in(
				Singleton.class);
		bind(BreadCrumbsDisplay.class).to(BreadCrumbsWidget.class);

		bind(BreadCrumbsMenu.class).to(BreadCrumbsMenuImpl.class).in(
				Singleton.class);
	}
}
