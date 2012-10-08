package com.chinarewards.gwt.certificate.client.certificate.module;


import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateListPresenter;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateListPresenter.CertificateListDisplay;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateListPresenterImpl;
import com.chinarewards.gwt.certificate.client.certificate.view.CertificateListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class CertificateListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CertificateListPresenter.class).to(CertificateListPresenterImpl.class);
		bind(CertificateListDisplay.class).to(CertificateListWidget.class);
	}

}
