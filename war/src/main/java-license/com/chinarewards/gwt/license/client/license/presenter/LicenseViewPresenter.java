package com.chinarewards.gwt.license.client.license.presenter;

import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public interface LicenseViewPresenter extends
		Presenter<LicenseViewPresenter.LicenseViewDisplay> {
	void initInstanceId(String instanceId, LicenseClient item);

	public static interface LicenseViewDisplay extends Display {

		public Label getCorporationId();

		public Label getCorporationName();

		public Label getLicenseType();

		public Label getStaffNumber();

		public Label getMacaddress();

		public Label getDescription();

		public Label getNotafter();

		public HasClickHandlers getBackClick();

		public HasClickHandlers getUpdateClick();

		public void showLicense(LicenseVo licenseVo);

		void setBreadCrumbs(Widget breadCrumbs);

	}
}
