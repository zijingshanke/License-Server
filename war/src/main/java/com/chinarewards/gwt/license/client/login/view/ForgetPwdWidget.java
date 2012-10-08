package com.chinarewards.gwt.license.client.login.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ForgetPwdWidget extends Composite {

	@UiField
	Button ok;

	interface ForgetPwdWidgetUiBinder extends UiBinder<Widget, ForgetPwdWidget> {

	}

	public static ForgetPwdWidgetUiBinder uiBinder = GWT
			.create(ForgetPwdWidgetUiBinder.class);

	public ForgetPwdWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getOkBtn() {
		return ok;
	}
}
