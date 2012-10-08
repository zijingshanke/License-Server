package com.chinarewards.gwt.license.client.license.view;

import java.util.Map.Entry;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.license.model.LicenseType;
import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.license.presenter.LicensePresenter.LicenseDisplay;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.view.constant.ViewConstants;
import com.chinarewards.gwt.license.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;

public class LicenseWidget extends Composite implements LicenseDisplay {

	@UiField
	Hidden id;
	@UiField
	TextBox corporationId;
	@UiField
	TextBox corporationName;
	@UiField
	ListBox licenseType;
	@UiField
	TextBox staffNumber;
	@UiField
	TextBox macaddress;
	@UiField
	TextBox description;
	@UiField
	DateBox notafter;

	@UiField
	Button back;

	@UiField
	Button save;

	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface LicenseWidgetBinder extends UiBinder<Widget, LicenseWidget> {

	}

	private static LicenseWidgetBinder uiBinder = GWT
			.create(LicenseWidgetBinder.class);

	@Inject
	public LicenseWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
		notafter.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditLicense(LicenseVo licenseVo) {
		id.setValue(licenseVo.getId());
		corporationId.setText(licenseVo.getCorporationId());
		corporationName.setText(licenseVo.getCorporationName());
		initTypeSelect(licenseVo.getLicenseType());
		staffNumber.setText(licenseVo.getStaffNumber()+"");
		macaddress.setText(licenseVo.getMacaddress());
		description.setText(licenseVo.getDescription());		
		
		notafter.setValue(licenseVo.getNotafter());
		
//		private Date issued; // 授权时间
//		private String awarduser; // 授权人

	}

	@Override
	public void initAddLicense(LicenseVo licenseVo) {
		initTypeSelect("");

	}

	private void initTypeSelect(String selectedValue) {
		licenseType.clear();
		int selectIndex = 0;
		int i = 0;
		for (Entry<String, String> entry : LicenseType.map.entrySet()) {
			String keyValue = entry.getKey();
			// System.out.println(entry.getKey() + ": " + entry.getValue());
			licenseType.addItem(entry.getValue(), entry.getKey());
			if (selectedValue != null && StringUtil.trim(selectedValue) != ""
					&& StringUtil.trim(keyValue) != "") {
				if (selectedValue.equals(keyValue)) {
					selectIndex = i;
				}
			}
			i++;
		}
		licenseType.setSelectedIndex(selectIndex);
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public void clear() {

	}

	@Override
	public HasValue<String> getCorporationId() {
		return corporationId;
	}

	@Override
	public HasValue<String> getCorporationName() {
		return corporationName;
	}

	@Override
	public ListBox getLicenseType() {
		return licenseType;
	}

	@Override
	public HasValue<String> getStaffNumber() {
		return staffNumber;
	}

	@Override
	public HasValue<String> getMacaddress() {
		return macaddress;
	}

	@Override
	public HasValue<String> getDescription() {
		return description;
	}

	@Override
	public DateBox getNotafter() {
		return notafter;
	}

	@Override
	public HasClickHandlers getSaveClick() {
		return save;
	}

	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	@Override
	public Hidden getId() {
		return id;
	}
}
