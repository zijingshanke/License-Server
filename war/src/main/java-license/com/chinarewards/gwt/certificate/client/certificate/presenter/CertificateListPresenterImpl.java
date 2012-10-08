package com.chinarewards.gwt.certificate.client.certificate.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria.CertificateStatus;
import com.chinarewards.gwt.certificate.client.certificate.plugin.CertificateConstants;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateListPresenter.CertificateListDisplay;
import com.chinarewards.gwt.certificate.client.certificate.request.DeleteCertificateRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.DeleteCertificateResponse;
import com.chinarewards.gwt.certificate.client.dataprovider.CertificateListViewAdapter;
import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.license.client.mvp.BasePresenter;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.ui.HyperLinkCell;
import com.chinarewards.gwt.license.client.widget.EltNewPager;
import com.chinarewards.gwt.license.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.license.client.widget.GetValue;
import com.chinarewards.gwt.license.client.widget.ListCellTable;
import com.chinarewards.gwt.license.client.widget.Sorting;
import com.chinarewards.gwt.license.client.win.Win;
import com.chinarewards.gwt.license.client.win.confirm.ConfirmHandler;
import com.chinarewards.gwt.license.util.StringUtil;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class CertificateListPresenterImpl extends BasePresenter<CertificateListDisplay>
		implements CertificateListPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<CertificateClient> cellTable;
	CertificateListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;

	int pageSize = ViewConstants.per_page_number_in_dialog;

	@Inject
	public CertificateListPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			CertificateListDisplay display, Win win,
			BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;

	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		initWidget();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doSearch();
					}
				}));
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {

						CertificateClient client = new CertificateClient();
						client.setThisAction(CertificateConstants.ACTION_CERTIFICATE_ADD);

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(CertificateConstants.EDITOR_CERTIFICATE_EDIT,
										CertificateConstants.ACTION_CERTIFICATE_ADD,
										client);
					}
				}));
	
		registerHandler(display.getPageNumber().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						pageSize = Integer.parseInt(display.getPageNumber()
								.getValue(
										display.getPageNumber()
												.getSelectedIndex()));
						buildTable();
						doSearch();
					}
				}));
	}

	private void initWidget() {
		display.initWidget();

		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<CertificateClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(pageSize);

		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
	}

	private void doSearch() {
		CertificateCriteria criteria = new CertificateCriteria();
		if (!StringUtil.isEmpty(display.getKeyName().getValue()))
			criteria.setName(display.getKeyName().getValue());
		if (!StringUtil.isEmpty(display.getStatus()))
			criteria.setStatus(CertificateStatus.valueOf(display.getStatus()));

		listViewAdapter = new CertificateListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);
	}

	private void initTableColumns() {
		Sorting<CertificateClient> ref = new Sorting<CertificateClient>() {
			@Override
			public void sortingCurrentPage(Comparator<CertificateClient> comparator) {
//				 listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};

		cellTable.addColumn("名称", new TextCell(),
				new GetValue<CertificateClient, String>() {
					@Override
					public String getValue(CertificateClient license) {
						return license.getName();
					}
				}, ref, "name");


		cellTable.addColumn("状态", new TextCell(),
				new GetValue<CertificateClient, String>() {
					@Override
					public String getValue(CertificateClient license) {
						return license.getStatus().getDisplayName();
					}
				}, ref, "status");

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<CertificateClient, String>() {
					@Override
					public String getValue(CertificateClient license) {
						if (license.getStatus() != null
								&& license.getStatus() == CertificateStatus.NORMAL)
							return "上架";
						else
							return "未知状态";
					}
				}, new FieldUpdater<CertificateClient, String>() {
					@Override
					public void update(int index, final CertificateClient o,
							String value) {
						String msgStr = "";
						if (o.getStatus() != null
								&& o.getStatus() == CertificateStatus.NORMAL)
							msgStr = "确定上架?";
						else
							msgStr = "未知状态";
						win.confirm("提示", msgStr, new ConfirmHandler() {
							@Override
							public void confirm() {
//								updateCertificateStatus(o.getId(), o.getStatus());
							}
						});
					}
				});

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<CertificateClient, String>() {
					@Override
					public String getValue(CertificateClient arg0) {
						return "查看详细";
					}
				}, new FieldUpdater<CertificateClient, String>() {
					@Override
					public void update(int index, CertificateClient licenseClient,
							String value) {
						licenseClient
								.setThisAction(CertificateConstants.ACTION_CERTIFICATE_VIEW);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										CertificateConstants.EDITOR_CERTIFICATE_VIEW,
										CertificateConstants.EDITOR_CERTIFICATE_VIEW
												+ licenseClient.getId(),
										licenseClient);
					}
				});

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<CertificateClient, String>() {
					@Override
					public String getValue(CertificateClient arg0) {
						return "修改";
					}
				}, new FieldUpdater<CertificateClient, String>() {
					@Override
					public void update(int index,
							final CertificateClient licenseClient, String value) {
						licenseClient
								.setThisAction(CertificateConstants.ACTION_CERTIFICATE_EDIT);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										CertificateConstants.EDITOR_CERTIFICATE_EDIT,
										CertificateConstants.EDITOR_CERTIFICATE_EDIT
												+ licenseClient.getId(),
										licenseClient);
					}
				});

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<CertificateClient, String>() {
					@Override
					public String getValue(CertificateClient license) {
						return "删除";
					}
				}, new FieldUpdater<CertificateClient, String>() {

					@Override
					public void update(int index, CertificateClient o, String value) {
						if (Window.confirm("确定删除?")) {
							delteCertificate(o.getId());
						}
					}

				});

	}

	public void delteCertificate(String gifId) {

		dispatch.execute(new DeleteCertificateRequest(gifId, sessionManager
				.getSession().getToken()),
				new AsyncCallback<DeleteCertificateResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteCertificateResponse resp) {
						win.alert("删除成功");
						doSearch();
					}
				});
	}

}
