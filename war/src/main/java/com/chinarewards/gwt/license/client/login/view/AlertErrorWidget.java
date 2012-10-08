package com.chinarewards.gwt.license.client.login.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class AlertErrorWidget extends Composite {

	@UiField
	Button ok;
	@UiField
	InlineLabel message;
	interface ForgetPwdWidgetUiBinder extends UiBinder<Widget, AlertErrorWidget> {

	}

	public static ForgetPwdWidgetUiBinder uiBinder = GWT
			.create(ForgetPwdWidgetUiBinder.class);

	public AlertErrorWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}
	public void setMessage(String text)
	{
		message.setText(text);
	}

	public HasClickHandlers getOkBtn() {
		return ok;
	}
}
