package com.chinarewards.gwt.license.client.win.alert;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class AlertWidget extends Composite implements
		AlertPresenter.AlertDisplay {

	@UiField
	ScrollPanel msg;

	@UiField
	Button ok;

	interface AlertWidgetUiBinder extends UiBinder<Widget, AlertWidget> {

	}

	public static AlertWidgetUiBinder uiBinder = GWT
			.create(AlertWidgetUiBinder.class);

	public AlertWidget() {
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
	public void setMsg(String message) {
		msg.clear();
		msg.add(new HTML(message));
	}

	@Override
	public void setImage(String url) {
		msg.clear();
		msg.add(new Image("imageshow?imageName="+url));
		
	}
}
