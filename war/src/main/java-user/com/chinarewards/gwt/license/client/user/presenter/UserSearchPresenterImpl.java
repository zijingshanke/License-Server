/**
 * 
 */
package com.chinarewards.gwt.license.client.user.presenter;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.user.DeleteUserRequest;
import com.chinarewards.gwt.license.client.user.DeleteUserResponse;
import com.chinarewards.gwt.license.client.user.dp.UserSearchAsyncDataProvider;
import com.chinarewards.gwt.license.client.user.model.UserSearchVo;
import com.chinarewards.gwt.license.client.user.model.UserVo;
import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenter.UserSearchDisplay;
import com.chinarewards.gwt.license.client.win.Win;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * User search presenter impl.
 * 
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserSearchPresenterImpl extends BasePresenter<UserSearchDisplay>
		implements UserSearchPresenter {

	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final SessionManager sessionManager;
	private final Win win;

	@Inject
	public UserSearchPresenterImpl(EventBus eventBus,
			UserSearchDisplay display, DispatchAsync dispatchAsync,
			ErrorHandler errorHandler, SessionManager sessionManager,Win win) {
		super(eventBus, display);
		this.dispatcher = dispatchAsync;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win=win;
	}

	@Override
	public void bind() {
		// doSearch();
		init();
		// search btn
		registerHandler(display.getSearchHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GWT.log("running click handlers. search");
						doSearch();
					}
				}));
		// add btn
	
		// Active btn
		registerHandler(display.getActiveHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GWT.log("running click handlers. Active");
						win.alert("激活-还未实现");
					}
				}));
		// LogOff btn
		registerHandler(display.getLogOffHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GWT.log("running click handlers. LogOff");
						win.alert("注销-还未实现");
					}
				}));
		// LogOff btn
		registerHandler(display.getDeleteHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GWT.log("running click handlers. delete");
						if(Window.confirm("确定离职?"))
						{
							if(display.getSelectedUsers().size()<1)
							{
								win.alert("请选人,然后再点离职.");
								return;
							}
							deleteUser(display.getSelectedUsers());
						}

					}
				}));
	}

	void init() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Active", "已激活");
		map.put("Inactive", "已注销");
		display.initUserStatus(map);
		doSearch();
	}

	private void doSearch() {
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setAccountName(display.getAccountName().getValue());
		searchVo.setEmail(display.getEmail().getValue());
		searchVo.setMobile(display.getMobile().getValue());
		searchVo.setStatus(display.getStatus());
		searchVo.setEnterpriseId(display.getEnterpriseId());
		UserSearchAsyncDataProvider listViewAdapter = new UserSearchAsyncDataProvider(
				searchVo, errorHandler, sessionManager, dispatcher,display);
		display.setListViewAdapter(listViewAdapter);
	}
	private void deleteUser(Map<String,UserVo> map)
	{
		dispatcher.execute(new DeleteUserRequest(map),
				new AsyncCallback<DeleteUserResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteUserResponse resp) {
						win.alert("离职成功");
						doSearch();
					}
				});
	}
}
