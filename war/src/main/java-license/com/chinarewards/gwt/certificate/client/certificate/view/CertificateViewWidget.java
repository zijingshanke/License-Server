package com.chinarewards.gwt.certificate.client.certificate.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateViewPresenter.CertificateViewDisplay;
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

public class CertificateViewWidget extends Composite implements CertificateViewDisplay {

	// --------vo
	@UiField
	Label name;
	@UiField
	Label summary;
	@UiField
	Label dispatchcycle;
	@UiField
	Label explains;
	@UiField
	Label notes;
	@UiField
	Label typeText;
	@UiField
	Label source;
	@UiField
	Label brand;
	@UiField
	Label integral;
	@UiField
	Label stock;

	@UiField
	Label business;
	@UiField
	Label address;
	@UiField
	Label tell;
	@UiField
	Label servicetell;

	@UiField
	Label indate;


	@UiField
	Button back;

	@UiField
	Button update;

	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface CertificateWidgetBinder extends UiBinder<Widget, CertificateViewWidget> {

	}

	private static CertificateWidgetBinder uiBinder = GWT
			.create(CertificateWidgetBinder.class);

	@Inject
	public CertificateViewWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
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
	public void showCertificate(CertificateVo CertificateVo) {
		name.setText(CertificateVo.getName());
		summary.setText(CertificateVo.getSummary());
		dispatchcycle.setText(CertificateVo.getDispatchcycle());
		explains.setText(CertificateVo.getExplains());
		notes.setText(CertificateVo.getNotes());
		typeText.setText(CertificateVo.getTypeText());
		brand.setText(CertificateVo.getBrand());


		integral.setText(CertificateVo.getIntegral() + "");
		stock.setText(CertificateVo.getStock() + "");
		
		source.setText(CertificateVo.getSourceText());

		business.setText(CertificateVo.getBusiness());
		address.setText(CertificateVo.getAddress());
		tell.setText(CertificateVo.getTell());
		servicetell.setText(CertificateVo.getServicetell());
		
		indate.setText(DateTool.dateToString(CertificateVo.getIndate()));

		// @UiField
		// Label status;// boolean
		// @UiField
		// Label deleted;// boolean
		// @UiField
		// DateBox recorddate;
		// @UiField
		// Label recorduser;
		// @UiField
		// DateBox updatetime;
		// ---end vo
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}
}
