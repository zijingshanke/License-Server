package com.chinarewards.gwt.certificate.client.certificate.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

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
import com.chinarewards.gwt.license.client.win.Win;
import com.chinarewards.gwt.license.util.StringUtil;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class CertificatePresenterImpl extends BasePresenter<CertificatePresenter.CertificateDisplay>
		implements CertificatePresenter {
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
	public CertificatePresenterImpl(EventBus eventBus, CertificateDisplay display,
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
		// 绑定事件
//		init();

//		if (CertificateConstants.ACTION_CERTIFICATE_ADD.equals(thisAction)) {
//			breadCrumbs.loadChildPage("新建礼品");
//			initSave();
//		} else if (CertificateConstants.ACTION_CERTIFICATE_EDIT.equals(thisAction)) {
//			initEdit();
//			breadCrumbs.loadChildPage("编辑礼品");
//		} else {
//			win.alert("未定义的方法");
//		}
//
//		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
	}

	private void init() {
//		// 保存事件
//		registerHandler(display.getSaveClick().addClickHandler(
//				new ClickHandler() {
//					@Override
//					public void onClick(ClickEvent arg0) {
//						if (!validateSubmit()) {
//							return;
//						}

//						CertificateVo licenseVo = CertificateAdapterClient
//								.adapterDisplay(display);
//
//						if (CertificateConstants.ACTION_CERTIFICATE_ADD.equals(thisAction)) {
//							licenseVo.setId(null);
//							doSave(licenseVo);
//						} else if (CertificateConstants.ACTION_CERTIFICATE_EDIT
//								.equals(thisAction)) {
//							licenseVo.setId(licenseId);
//							doEdit(licenseVo);
//						} else {
//							win.alert("未定义的方法");
//						}
//					}

//					private void doSave(CertificateVo license) {
//						dispatcher.execute(new EditCertificateRequest(license,
//								sessionManager.getSession()),
//								new AsyncCallback<EditCertificateResponse>() {
//									@Override
//									public void onFailure(Throwable t) {
//										errorHandler.alert(t.toString());
//									}
//
//									@Override
//									public void onSuccess(
//											EditCertificateResponse response) {
//										win.alert("添加成功");
//										// if(instanceId!=null||!instanceId.equals(""))
//										Platform.getInstance()
//												.getEditorRegistry()
//												.openEditor(
//														CertificateConstants.EDITOR_CERTIFICATE_LIST,
//														CertificateConstants.ACTION_CERTIFICATE_LIST,
//														instanceId);
//									}
//								});
//					}
//
//					private void doEdit(CertificateVo license) {
//						if (Window.confirm("确定修改?")) {
//							dispatcher.execute(new EditCertificateRequest(license,
//									sessionManager.getSession()),
//									new AsyncCallback<EditCertificateResponse>() {
//										@Override
//										public void onFailure(Throwable t) {
//											win.alert("修改失败");
//											Platform.getInstance()
//													.getEditorRegistry()
//													.closeEditor(
//															CertificateConstants.EDITOR_CERTIFICATE_EDIT,
//															instanceId);
//										}
//
//										@Override
//										public void onSuccess(
//												EditCertificateResponse arg0) {
//											win.alert("修改成功");
//											Platform.getInstance()
//													.getEditorRegistry()
//													.openEditor(
//															CertificateConstants.EDITOR_CERTIFICATE_LIST,
//															CertificateConstants.ACTION_CERTIFICATE_LIST,
//															instanceId);
//										}
//									});
//						}
//					}
//
//				}));
//
//
//		registerHandler(display.getBackClick().addClickHandler(
//				new ClickHandler() {
//					@Override
//					public void onClick(ClickEvent arg0) {
////						breadCrumbs.getGoHistory();
//					}
//				}));		
	
	}

	// 验证方法
	private boolean validateSubmit() {
		boolean flag = true;
		StringBuilder errorMsg = new StringBuilder();
		if (display.getName().getValue() == null
				|| "".equals(display.getName().getValue().trim())) {
			errorMsg.append("请填写礼品名称!<br>");
			flag = false;
		}

		if (display.getStock() == null
				|| StringUtil.valueOf(display.getStock().getValue()) == null
				|| StringUtil.valueOf(display.getStock().getValue()) < 0) {
			errorMsg.append("请正确填写礼品库存!<br>");
			flag = false;
		}
		if (display.getIndate().getValue() == null|| "".equals(display.getIndate().getValue())) {
			errorMsg.append("有效期不能为空<br>");
			flag = false;
		}
	

		if (!flag) {
			win.alert(errorMsg.toString());
		}

		return flag;
	}

	private void initEdit() {
		dispatcher.execute(new SearchCertificateByIdRequest(licenseId),
				new AsyncCallback<SearchCertificateByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(CertificateConstants.EDITOR_CERTIFICATE_EDIT,
										instanceId);
					}

					@Override
					public void onSuccess(SearchCertificateByIdResponse response) {
						CertificateVo licenseVo = response.getCertificate();
						clear();
						display.initEditCertificate(licenseVo);
					}
				});
	}

	private void initSave() {
		display.initAddCertificate(new CertificateVo());
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
