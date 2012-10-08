package com.chinarewards.gwt.certificate.client.certificate.presenter;

import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface CertificateListPresenter extends
		Presenter<CertificateListPresenter.CertificateListDisplay> {

	public static interface CertificateListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();

		public HasClickHandlers getAddBtnClickHandlers();

		void setDataCount(String text);

		HasValue<String> getKeyName();

		String getStatus();

		Panel getResultPanel();

		Panel getResultpage();

		public void initWidget();

		ListBox getPageNumber();

		void setBreadCrumbs(Widget breadCrumbs);

	}
}
