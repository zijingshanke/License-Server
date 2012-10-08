package com.chinarewards.gwt.license.client.license.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.license.presenter.LicenseViewPresenter;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdResponse;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class LicenseViewPresenterImpl extends
		BasePresenter<LicenseViewPresenter.LicenseViewDisplay> implements
		LicenseViewPresenter {
	String instanceId;// 修改时传过来的ID

	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	String licenseId;
	LicenseClient licenseClient = new LicenseClient();
	

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public LicenseViewPresenterImpl(EventBus eventBus, LicenseViewDisplay display,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.breadCrumbs=breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("查看授权证书");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		
		registerHandler(display.getBackClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						breadCrumbs.getGoHistory();
					}
				}));

		registerHandler(display.getUpdateClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						licenseClient.setId(licenseId);
						licenseClient
								.setThisAction(LicenseConstants.ACTION_LICENSE_EDIT);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(LicenseConstants.EDITOR_LICENSE_EDIT,
										LicenseConstants.ACTION_LICENSE_EDIT,
										licenseClient);
					}
				}));
	}

	// 查看时初始化数据
	@Override
	public void initInstanceId(String instanceId, LicenseClient licenseClient) {
		this.instanceId = instanceId;
		this.licenseClient = licenseClient;
		initDataToViewLicense(licenseClient, instanceId);
	}

	private void initDataToViewLicense(final LicenseClient licenseClient,
			final String instanceId) {
		licenseId = licenseClient.getId();
		dispatcher.execute(new SearchLicenseByIdRequest(licenseId),
				new AsyncCallback<SearchLicenseByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询授权证书出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(LicenseConstants.EDITOR_LICENSE_VIEW,
										instanceId);
					}

					@Override
					public void onSuccess(SearchLicenseByIdResponse response) {
						LicenseVo item = response.getLicense();
						display.showLicense(item);
					}

				});

	}

}
