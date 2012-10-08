package com.chinarewards.gwt.license.client.license.presenter;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface LicensePresenter extends
		Presenter<LicensePresenter.LicenseDisplay> {

	public static interface LicenseDisplay extends Display {

		public HasValue<String> getCorporationId();

		public HasValue<String> getCorporationName();

		public ListBox getLicenseType();

		public HasValue<String> getStaffNumber();

		public HasValue<String> getMacaddress();

		public HasValue<String> getDescription();

		public DateBox getNotafter();

		public HasClickHandlers getSaveClick();

		public void clear();

		public void initAddLicense(LicenseVo licenseVo);

		public void initEditLicense(LicenseVo licenseVo);

		public HasClickHandlers getBackClick();

		void setBreadCrumbs(Widget breadCrumbs);

		public Hidden getId();

	}

	public void initEditor(String licenseId, String thisAction);
}
