package com.chinarewards.gwt.certificate.client.certificate.module;

import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificatePresenter;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificatePresenter.CertificateDisplay;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificatePresenterImpl;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateViewPresenter;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateViewPresenter.CertificateViewDisplay;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateViewPresenterImpl;
import com.chinarewards.gwt.certificate.client.certificate.view.CertificateViewWidget;
import com.chinarewards.gwt.certificate.client.certificate.view.CertificateWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class CertificateModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CertificatePresenter.class).to(CertificatePresenterImpl.class);
		bind(CertificateDisplay.class).to(CertificateWidget.class);

		bind(CertificateViewPresenter.class).to(CertificateViewPresenterImpl.class);
		bind(CertificateViewDisplay.class).to(CertificateViewWidget.class);
	}
}
