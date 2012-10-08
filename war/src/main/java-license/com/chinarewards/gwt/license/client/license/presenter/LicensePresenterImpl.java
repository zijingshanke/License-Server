package com.chinarewards.gwt.license.client.license.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.chinarewards.gwt.license.client.license.request.EditLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.EditLicenseResponse;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdResponse;
import com.chinarewards.gwt.license.client.license.util.LicenseAdapterClient;
import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.win.Win;
import com.chinarewards.gwt.license.util.StringUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class LicensePresenterImpl extends
		BasePresenter<LicensePresenter.LicenseDisplay> implements
		LicensePresenter {
	String instanceId;// 修改时传过来的ID

	private String thisAction;
	private String licenseId;
	//
	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final SessionManager sessionManager;

	private final Win win;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public LicensePresenterImpl(EventBus eventBus, LicenseDisplay display,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {

		if (LicenseConstants.ACTION_LICENSE_ADD.equals(thisAction)) {
			breadCrumbs.loadChildPage("增加授权");
			initSave();
		} else if (LicenseConstants.ACTION_LICENSE_EDIT.equals(thisAction)) {			
			breadCrumbs.loadChildPage("编辑授权");
			initEdit();
		} else {
			win.alert("未定义的方法");
		}

		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());

		initEvent();
	}

	private void initEvent() {
		// 保存事件
		registerHandler(display.getSaveClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						if (!validateSubmit()) {
							return;
						}

						LicenseVo licenseVo = LicenseAdapterClient
								.adapterDisplay(display);

						if (LicenseConstants.ACTION_LICENSE_ADD
								.equals(thisAction)) {
							licenseVo.setId(null);
							doSave(licenseVo);
						} else if (LicenseConstants.ACTION_LICENSE_EDIT
								.equals(thisAction)) {
							licenseVo.setId(display.getId().getValue());
							doEdit(licenseVo);
						} else {
							win.alert("未定义的方法");
						}
					}

					private void doSave(LicenseVo license) {
						dispatcher.execute(new EditLicenseRequest(license,
								sessionManager.getSession()),
								new AsyncCallback<EditLicenseResponse>() {
									@Override
									public void onFailure(Throwable t) {
										errorHandler.alert(t.toString());
									}

									@Override
									public void onSuccess(
											EditLicenseResponse response) {
										// if(instanceId!=null||!instanceId.equals(""))
										Platform.getInstance()
												.getEditorRegistry()
												.openEditor(
														LicenseConstants.EDITOR_LICENSE_LIST,
														LicenseConstants.ACTION_LICENSE_LIST,
														instanceId);
									}
								});
					}

					private void doEdit(LicenseVo license) {
						if (Window.confirm("确定修改?")) {
							dispatcher.execute(new EditLicenseRequest(license,
									sessionManager.getSession()),
									new AsyncCallback<EditLicenseResponse>() {
										@Override
										public void onFailure(Throwable t) {
											win.alert("修改失败");
											Platform.getInstance()
													.getEditorRegistry()
													.closeEditor(
															LicenseConstants.EDITOR_LICENSE_EDIT,
															instanceId);
										}

										@Override
										public void onSuccess(
												EditLicenseResponse arg0) {
											win.alert("修改成功");
											Platform.getInstance()
													.getEditorRegistry()
													.openEditor(
															LicenseConstants.EDITOR_LICENSE_LIST,
															LicenseConstants.ACTION_LICENSE_LIST,
															instanceId);
										}
									});
						}
					}

				}));

		registerHandler(display.getBackClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						breadCrumbs.getGoHistory();
					}
				}));

	}

	// 验证方法
	private boolean validateSubmit() {
		boolean flag = true;
		StringBuilder errorMsg = new StringBuilder();
		if (display.getCorporationName().getValue() == null
				|| "".equals(display.getCorporationName().getValue().trim())) {
			errorMsg.append("请填写客户名称!<br>");
			flag = false;
		}

		if (display.getStaffNumber() == null
				|| StringUtil.valueOf(display.getStaffNumber().getValue()) == null
				|| StringUtil.valueOf(display.getStaffNumber().getValue()) < 0) {
			errorMsg.append("请正确填写最大用户数!<br>");
			flag = false;
		}
		if (display.getNotafter().getValue() == null
				|| "".equals(display.getNotafter().getValue())) {
			errorMsg.append("有效期不能为空<br>");
			flag = false;
		}

		if (!flag) {
			win.alert(errorMsg.toString());
		}

		return flag;
	}

	private void initEdit() {
		dispatcher.execute(new SearchLicenseByIdRequest(licenseId),
				new AsyncCallback<SearchLicenseByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(
										LicenseConstants.EDITOR_LICENSE_EDIT,
										instanceId);
					}

					@Override
					public void onSuccess(SearchLicenseByIdResponse response) {
						LicenseVo licenseVo = response.getLicense();
						clear();
						display.initEditLicense(licenseVo);
					}
				});
	}

	private void initSave() {
		display.initAddLicense(new LicenseVo());
	}

	private void clear() {
		display.clear();
	}

	public void setId(String id) {
		this.licenseId = id;
	}

	@Override
	public void initEditor(String licenseId, String thisAction) {
		this.licenseId = licenseId;
		this.thisAction = thisAction;
	}

}
