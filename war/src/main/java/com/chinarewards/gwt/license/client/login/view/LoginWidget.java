package com.chinarewards.gwt.license.client.login.view;

import com.chinarewards.gwt.license.client.login.presenter.LoginPresenter.LoginDisplay;
import com.chinarewards.gwt.license.client.ui.DialogBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite implements LoginDisplay {

	@UiField
	TextBox username;

	@UiField
	PasswordTextBox password;

	@UiField
	Button loginButton;
	@UiField
	Anchor forgetPwd;

	DialogBox panel;

	@UiField
	TextBox codeCheck;

	@UiField
	Image verifyImage;

	@UiField
	Anchor prompt;

	interface LoginWidgetBinder extends UiBinder<Widget, LoginWidget> {
	}

	private static LoginWidgetBinder uiBinder = GWT
			.create(LoginWidgetBinder.class);

	public LoginWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	private void init() {
		changeImage();

		verifyImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				codeCheck.setText("");
				changeImage();
			}
		});
		prompt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				codeCheck.setText("");
				changeImage();
			}
		});

		final ForgetPwdWidget w = new ForgetPwdWidget();
		final DialogBox dialogBox = new DialogBox();
		w.getOkBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				dialogBox.hide();
			}
		});
		forgetPwd.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				dialogBox.setWidget(w);
				dialogBox.setGlassEnabled(true);
				dialogBox.setAnimationEnabled(true);
				dialogBox.setWidth("450px");
				dialogBox.setText("密码找回");
				dialogBox.center();
				dialogBox.show();
			}
		});

	}

	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getLoginClickHandlers() {
		return loginButton;
	}

	public HasValue<String> getPassword() {
		return password;
	}

	public HasValue<String> getUsername() {
		return username;
	}

	public HasKeyUpHandlers getVerifyCodeKeyUpHandlers() {
		return codeCheck;
	}
	public HasValue<String> getVerifyCode() {
		return codeCheck;
	}
	
	@Override
	public void changeImage() {
		int rand = Random.nextInt();
		String url = "kaptcha.jpg?dt=" + rand;
		verifyImage.setUrl(url);
	}

}
