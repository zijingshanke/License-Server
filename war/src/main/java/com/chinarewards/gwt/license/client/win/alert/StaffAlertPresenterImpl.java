package com.chinarewards.gwt.license.client.win.alert;


import com.chinarewards.gwt.license.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class StaffAlertPresenterImpl extends
		BaseDialogPresenter<StaffAlertPresenter.StaffAlertDisplay> implements StaffAlertPresenter {

	@Inject
	public StaffAlertPresenterImpl(EventBus eventBus, StaffAlertPresenter.StaffAlertDisplay display) {
		super(eventBus, display);
	}

	@Override
	public void bind() {
		registerHandler(display.getOkBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				closeDialog();
			}
		}));
	}
}
