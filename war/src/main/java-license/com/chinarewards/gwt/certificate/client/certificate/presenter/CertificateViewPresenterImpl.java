package com.chinarewards.gwt.certificate.client.certificate.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.certificate.client.certificate.plugin.CertificateConstants;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateByIdRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateByIdResponse;
import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class CertificateViewPresenterImpl extends
		BasePresenter<CertificateViewPresenter.CertificateViewDisplay> implements
		CertificateViewPresenter {
	String instanceId;// 修改时传过来的ID

	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	String licenseId;
	CertificateClient licenseClient = new CertificateClient();
	

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public CertificateViewPresenterImpl(EventBus eventBus, CertificateViewDisplay display,
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
								.setThisAction(CertificateConstants.ACTION_CERTIFICATE_EDIT);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(CertificateConstants.EDITOR_CERTIFICATE_EDIT,
										CertificateConstants.ACTION_CERTIFICATE_EDIT,
										licenseClient);
					}
				}));
	}

	// 查看时初始化数据
	@Override
	public void initInstanceId(String instanceId, CertificateClient licenseClient) {
		this.instanceId = instanceId;
		this.licenseClient = licenseClient;
		initDataToViewCertificate(licenseClient, instanceId);
	}

	private void initDataToViewCertificate(final CertificateClient licenseClient,
			final String instanceId) {
		licenseId = licenseClient.getId();
		dispatcher.execute(new SearchCertificateByIdRequest(licenseId),
				new AsyncCallback<SearchCertificateByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询授权证书出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(CertificateConstants.EDITOR_CERTIFICATE_VIEW,
										instanceId);
					}

					@Override
					public void onSuccess(SearchCertificateByIdResponse response) {
						CertificateVo item = response.getCertificate();
						display.showCertificate(item);
					}

				});

	}

}
