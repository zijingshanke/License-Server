package com.chinarewards.gwt.license.client.core.presenter;

import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;

public interface DockPresenter extends Presenter<DockPresenter.DockDisplay> {

	public static interface DockDisplay extends Display {

		HasClickHandlers getlogBtn();

		HasClickHandlers getBtnStaff();

		HasClickHandlers getBtnSetting();

		HasClickHandlers getBtnCollection();

		HasClickHandlers getBtnLicense();

		HasClickHandlers getManagementCenter();

		HasClickHandlers getStaffCenter();

		DockLayoutPanel getDock();

		Panel getMenu();

		void setMessage(String userName);

		void setMenu(Panel panel);

		void setMenuTitle(String title);

		void changeTopMenu(String key);

	
		void disableManagementCenter();
		void disableStaffCenter();
	}

}
