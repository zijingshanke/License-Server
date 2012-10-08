package com.chinarewards.gwt.license.client.license.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.license.presenter.LicenseViewPresenter.LicenseViewDisplay;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.view.constant.ViewConstants;
import com.chinarewards.gwt.license.util.DateTool;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class LicenseViewWidget extends Composite implements LicenseViewDisplay {

	@UiField
	Label corporationId;
	@UiField
	Label corporationName;
	@UiField
	Label licenseType;
	@UiField
	Label staffNumber;
	@UiField
	Label macaddress;
	@UiField
	Label description;
	@UiField
	Label notafter;
	
	@UiField
	Label awarduser;
	@UiField
	Label issued;
	@UiField
	Label licenseFileName;


	@UiField
	Button back;

	@UiField
	Button update;

	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface LicenseWidgetBinder extends UiBinder<Widget, LicenseViewWidget> {

	}

	private static LicenseWidgetBinder uiBinder = GWT
			.create(LicenseWidgetBinder.class);

	@Inject
	public LicenseViewWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	@Override
	public HasClickHandlers getUpdateClick() {
		return update;
	}

	@Override
	public void showLicense(LicenseVo licenseVo) {
		corporationId.setText(licenseVo.getCorporationId());
		corporationName.setText(licenseVo.getCorporationName());
		licenseType.setText(licenseVo.getLicenseTypeText());
		staffNumber.setText(licenseVo.getStaffNumber() + "");
		macaddress.setText(licenseVo.getMacaddress());
		description.setText(licenseVo.getDescription()); 

		notafter.setText(DateTool.dateToString(licenseVo.getNotafter()));
		
		awarduser.setText(licenseVo.getAwarduser());
		issued.setText(DateTool.dateToString(licenseVo.getIssued()));
		licenseFileName.setText(licenseVo.getLicenseFileName());
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public Label getCorporationId() {
		return corporationId;
	}

	@Override
	public Label getCorporationName() {
		return corporationName;
	}

	@Override
	public Label getLicenseType() {
		return licenseType;
	}

	@Override
	public Label getStaffNumber() {
		return staffNumber;
	}

	@Override
	public Label getMacaddress() {
		return macaddress;
	}

	@Override
	public Label getDescription() {
		return description;
	}

	@Override
	public Label getNotafter() {
		return notafter;
	}
}
