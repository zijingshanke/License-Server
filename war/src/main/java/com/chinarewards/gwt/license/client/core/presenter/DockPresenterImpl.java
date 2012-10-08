package com.chinarewards.gwt.license.client.core.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.LicenseGinjector;
import com.chinarewards.gwt.license.client.core.PluginManager;
import com.chinarewards.gwt.license.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.license.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.license.client.login.event.LoginEvent;
import com.chinarewards.gwt.license.client.login.request.LastLoginRoleRequest;
import com.chinarewards.gwt.license.client.login.request.LastLoginRoleResponse;
import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DockPresenterImpl extends BasePresenter<DockDisplay> implements
		DockPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final LicenseGinjector licenseGinjector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;

	@Inject
	public DockPresenterImpl(EventBus eventBus, DockDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			LicenseGinjector licenseGinjector, MenuProcessor menuProcessor,
			DispatchAsync dispatchAsync) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.licenseGinjector = licenseGinjector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync = dispatchAsync;
	}

	public void bind() {
		List<UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo[] roles = sessionManager.getSession().getUserRoles();

		if (roles.length > 0) {
			for (UserRoleVo r : roles) {
				roleslt.add(r);
			}
			if (!roleslt.contains(UserRoleVo.CORP_ADMIN)) {
				display.disableManagementCenter();
			}
			if (!roleslt.contains(UserRoleVo.STAFF)) {
				display.disableStaffCenter();
			}
		}

		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));

		registerHandler(display.getBtnCollection().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Window.alert("收藏");
					}
				}));

		registerHandler(display.getStaffCenter().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dispatchAsync.execute(new LastLoginRoleRequest(
								sessionManager.getSession().getToken(),
								UserRoleVo.STAFF),
								new AsyncCallback<LastLoginRoleResponse>() {

									@Override
									public void onFailure(Throwable e) {
										// Window.alert("系统切换出错");
									}

									@Override
									public void onSuccess(
											LastLoginRoleResponse resp) {
										// 成功
										if ("success".equals(resp.getFal()))
											Window.Location.reload();
										else
											Window.alert("系统切换出错");

									}
								});

					}
				}));

	}

	public DockDisplay getDisplay() {
		return display;
	}

}
