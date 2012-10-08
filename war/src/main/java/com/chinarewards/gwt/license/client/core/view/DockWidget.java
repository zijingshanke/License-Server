package com.chinarewards.gwt.license.client.core.view;

import java.util.Date;

import com.chinarewards.gwt.license.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.license.client.core.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class DockWidget extends Composite implements DockDisplay {

	@UiField
	DockLayoutPanel dock;

	@UiField
	FlowPanel menu;

	@UiField
	Anchor logBtn;

	@UiField
	InlineLabel message;

	@UiField
	Anchor btnLicense;
	@UiField
	Anchor btnSetting;
	@UiField
	Anchor btnStaff;

	@UiField
	Anchor collectionBtn;

	@UiField
	InlineLabel menuTitle;

	@UiField
	Anchor managementCenter;

	@UiField
	Anchor staffCenter;

	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format_chinese);

	interface DockWidgetBinder extends UiBinder<Widget, DockWidget> {
	}

	private static DockWidgetBinder uiBinder = GWT
			.create(DockWidgetBinder.class);

	public DockWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getlogBtn() {
		return logBtn;
	}

	String styleOn = "";
	String styleNo = "";

	private void init() {
		styleOn = btnLicense.getStyleName();
		styleNo = btnSetting.getStyleName();
		btnLicense.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnLicense.setStyleName(styleOn);
				btnSetting.setStyleName(styleNo);
			}
		});
		btnSetting.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnLicense.setStyleName(styleNo);
				btnSetting.setStyleName(styleOn);
			}
		});

	}

	@Override
	public DockLayoutPanel getDock() {
		return dock;
	}

	@Override
	public Panel getMenu() {
		return menu;
	}

	@Override
	public void setMessage(String userName) {
		String time = dateFormat.format(new Date());
		String msg = "欢迎你，" + userName + "！今天是:" + time;
		message.setText(msg);
	}

	@Override
	public void setMenu(Panel panel) {
		menu.clear();

	}

	@Override
	public void setMenuTitle(String title) {
		menuTitle.setText(title);
	}

	@Override
	public HasClickHandlers getBtnSetting() {
		return btnSetting;
	}

	@Override
	public HasClickHandlers getBtnCollection() {
		return collectionBtn;
	}

	@Override
	public HasClickHandlers getManagementCenter() {
		return managementCenter;
	}

	@Override
	public HasClickHandlers getStaffCenter() {
		return staffCenter;
	}

	@Override
	public void disableManagementCenter() {
		managementCenter.setVisible(false);
	}

	@Override
	public void disableStaffCenter() {
		staffCenter.setVisible(false);
	}

	@Override
	public void changeTopMenu(String key) {
		if ("License".equals(key)) {
			btnLicense.setStyleName(styleOn);
			btnSetting.setStyleName(styleNo);
		}

		if ("Setting".equals(key)) {
			btnLicense.setStyleName(styleNo);
			btnSetting.setStyleName(styleOn);
		}

	}

	@Override
	public HasClickHandlers getBtnStaff() {
		return btnStaff;
	}

	@Override
	public HasClickHandlers getBtnLicense() {
		return btnLicense;
	}

}
