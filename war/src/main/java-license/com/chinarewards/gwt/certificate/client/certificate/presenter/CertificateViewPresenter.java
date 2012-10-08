package com.chinarewards.gwt.certificate.client.certificate.presenter;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface CertificateViewPresenter extends
		Presenter<CertificateViewPresenter.CertificateViewDisplay> {
	void initInstanceId(String instanceId, CertificateClient item);

	public static interface CertificateViewDisplay extends Display {


		public HasClickHandlers getBackClick();

		public HasClickHandlers getUpdateClick();

		public void showCertificate(CertificateVo licenseVo);

		void setBreadCrumbs(Widget breadCrumbs);

	}
}
