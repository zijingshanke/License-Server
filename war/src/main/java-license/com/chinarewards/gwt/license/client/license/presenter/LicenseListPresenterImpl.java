package com.chinarewards.gwt.license.client.license.presenter;

import java.util.Comparator;
import java.util.Date;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.license.client.dataprovider.LicenseListViewAdapter;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.model.LicenseCriteria;
import com.chinarewards.gwt.license.client.license.model.LicenseCriteria.LicenseStatus;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.chinarewards.gwt.license.client.license.presenter.LicenseListPresenter.LicenseListDisplay;
import com.chinarewards.gwt.license.client.license.request.DeleteLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.DeleteLicenseResponse;
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
import com.chinarewards.gwt.license.util.StringUtil;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.NamedFrame;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class LicenseListPresenterImpl extends BasePresenter<LicenseListDisplay>
		implements LicenseListPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<LicenseClient> cellTable;
	LicenseListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;

	int pageSize = ViewConstants.per_page_number_in_dialog;
	DateTimeFormat dateFormatAll = DateTimeFormat
			.getFormat(ViewConstants.date_format_all);

	@Inject
	public LicenseListPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			LicenseListDisplay display, Win win,
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

						LicenseClient client = new LicenseClient();
						client.setThisAction(LicenseConstants.ACTION_LICENSE_ADD);

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										LicenseConstants.EDITOR_LICENSE_EDIT,
										LicenseConstants.ACTION_LICENSE_ADD,
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
		cellTable = new ListCellTable<LicenseClient>();

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
		LicenseCriteria criteria = new LicenseCriteria();
		if (!StringUtil.isEmpty(display.getKeyName().getValue()))
			criteria.setCorporationName(display.getKeyName().getValue());
		if (!StringUtil.isEmpty(display.getStatus()))
			criteria.setStatus(LicenseStatus.valueOf(display.getStatus()));

		listViewAdapter = new LicenseListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);
	}

	private void initTableColumns() {
		Sorting<LicenseClient> ref = new Sorting<LicenseClient>() {
			@Override
			public void sortingCurrentPage(Comparator<LicenseClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};

		cellTable.addColumn("客户名称", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getCorporationName();
					}
				}, ref, "licenseName");
		cellTable.addColumn("授权类型", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getLicenseTypeText();
					}
				}, ref, "");

		cellTable.addColumn("最大用户数", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getStaffNumber() + "";
					}
				}, ref, "");

		cellTable.addColumn("绑定网卡", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getMacaddress();
					}
				}, ref, "");

		cellTable.addColumn("截止时间", new DateCell(dateFormatAll),
				new GetValue<LicenseClient, Date>() {
					@Override
					public Date getValue(LicenseClient object) {
						return object.getNotafter();
					}
				}, ref, "notafter");
		cellTable.addColumn("说明", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getDescription();
					}
				}, ref, "");

		cellTable.addColumn("授权人", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getAwarduser();
					}
				}, ref, "awarduser");

		cellTable.addColumn("授权时间", new DateCell(dateFormatAll),
				new GetValue<LicenseClient, Date>() {
					@Override
					public Date getValue(LicenseClient object) {
						return object.getIssued();
					}
				}, ref, "issued");

		cellTable.addColumn("状态", new TextCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return license.getStatus().getDisplayName();
					}
				}, ref, "status");

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient arg0) {
						return "查看详细";
					}
				}, new FieldUpdater<LicenseClient, String>() {
					@Override
					public void update(int index, LicenseClient licenseClient,
							String value) {
						licenseClient
								.setThisAction(LicenseConstants.ACTION_LICENSE_VIEW);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										LicenseConstants.EDITOR_LICENSE_VIEW,
										LicenseConstants.EDITOR_LICENSE_VIEW
												+ licenseClient.getId(),
										licenseClient);
					}
				});

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient arg0) {
						return "修改";
					}
				}, new FieldUpdater<LicenseClient, String>() {
					@Override
					public void update(int index,
							final LicenseClient licenseClient, String value) {
						licenseClient
								.setThisAction(LicenseConstants.ACTION_LICENSE_EDIT);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										LicenseConstants.EDITOR_LICENSE_EDIT,
										LicenseConstants.EDITOR_LICENSE_EDIT
												+ licenseClient.getId(),
										licenseClient);
					}
				});

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return "删除";
					}
				}, new FieldUpdater<LicenseClient, String>() {

					@Override
					public void update(int index, LicenseClient o, String value) {
						if (Window.confirm("确定删除?")) {
							delteLicense(o.getId());
						}
					}

				});

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<LicenseClient, String>() {
					@Override
					public String getValue(LicenseClient license) {
						return "下载";
					}
				}, new FieldUpdater<LicenseClient, String>() {

					@Override
					public void update(int index, LicenseClient license,
							String value) {

						
						if (RootPanel.get("downloadiframe") != null) {
							Widget widgetFrame = (Widget) RootPanel
									.get("downloadiframe");
							widgetFrame.removeFromParent();
						}

						NamedFrame frame = new NamedFrame("downloadiframe");
						String url = "";
						// url+=GWT.getModuleBaseURL();
						url += "filedownload?licenseFileName="
								+ license.getLicenseFileName();
						frame.setUrl(url);
						frame.setVisible(false);
						RootPanel.get().add(frame);
					}

				});

	}

	public void delteLicense(String gifId) {

		dispatch.execute(new DeleteLicenseRequest(gifId, sessionManager
				.getSession().getToken()),
				new AsyncCallback<DeleteLicenseResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteLicenseResponse resp) {
						win.alert("删除成功");
						doSearch();
					}
				});
	}

}
