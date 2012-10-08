package com.chinarewards.gwt.license.client.win.confirm;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmWidget extends Composite implements
		ConfirmPresenter.ConfirmDisplay {

	@UiField
	ScrollPanel msg;

	@UiField
	Button ok;

	@UiField
	Button cancel;

	interface ConfirmWidgetUiBinder extends UiBinder<Widget, ConfirmWidget> {

	}

	public static ConfirmWidgetUiBinder uiBinder = GWT
			.create(ConfirmWidgetUiBinder.class);

	public ConfirmWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getOkBtn() {
		return ok;
	}

	@Override
	public HasClickHandlers getCancelBtn() {
		return cancel;
	}

	@Override
	public void setMsg(String message) {
		msg.add(new HTML(message));
	}
}
