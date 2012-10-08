package com.chinarewards.gwt.license.client.win.confirm;


import com.chinarewards.gwt.license.client.mvp.DialogPresenter;
import com.chinarewards.gwt.license.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ConfirmPresenter extends DialogPresenter<ConfirmPresenter.ConfirmDisplay> {

	public static interface ConfirmDisplay extends Display {
		HasClickHandlers getOkBtn();

		HasClickHandlers getCancelBtn();

		void setMsg(String msg);
	}

}
