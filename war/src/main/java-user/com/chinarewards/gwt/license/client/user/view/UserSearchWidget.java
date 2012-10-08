/**
 * 
 */
package com.chinarewards.gwt.license.client.user.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.license.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.license.client.ui.HyperLinkCell;
import com.chinarewards.gwt.license.client.user.model.UserVo;
import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenter.UserSearchDisplay;
import com.chinarewards.gwt.license.client.widget.EltNewPager;
import com.chinarewards.gwt.license.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.license.client.widget.GetValue;
import com.chinarewards.gwt.license.client.widget.ListCellTable;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

/**
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserSearchWidget extends Composite implements UserSearchDisplay {

	@UiField
	ListBox status;

	@UiField
	TextBox accountName;

	@UiField
	TextBox mobile;

	@UiField
	TextBox email;

	@UiField
	TextBox enterpriseName;

	String enterpriseId;

	@UiField
	Button choose;

	@UiField
	Button search;

	@UiField
	Button reset;

	@UiField
	Button add;

	@UiField
	Button active;

	@UiField
	Button logOff;

	@UiField
	Button resetPwd;

	@UiField
	Button Update;

	@UiField
	Panel result;
	@UiField
	Button delete;

	@UiField
	Panel resultpage;
	
	// @UiField
	// Button searchSubAccount;
	@UiField
	InlineLabel dataCount;
	
	ListCellTable<UserVo> resultTable;
	EltNewPager pager;
	AsyncDataProvider<UserVo> listViewAdapter;

	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	Map<String, UserVo> users;

	interface UserSearchWidgetBinder extends UiBinder<Widget, UserSearchWidget> {
	}

	private static UserSearchWidgetBinder uiBinder = GWT
			.create(UserSearchWidgetBinder.class);

	public UserSearchWidget() {

		initWidget(uiBinder.createAndBindUi(this));

		enterpriseName.setReadOnly(true);

		buildTable();
	}

	private void buildTable() {

		resultTable = new ListCellTable<UserVo>();
		resultTable.setWidth(ViewConstants.page_width);
		resultTable.setPageSize(ViewConstants.per_page_number);
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(resultTable);
		MultiSelectionModel<UserVo> selectionModel = new MultiSelectionModel<UserVo>();
		resultTable.setSelectionModel(selectionModel);
		initTableColumns(selectionModel);
		resultTable.setRowCount(0);
		result.clear();
		resultpage.clear();
		result.add(resultTable);
		resultpage.add(pager);
		
	}

	private void initTableColumns(final SelectionModel<UserVo> selectionModel) {
		Column<UserVo, Boolean> checkColumn = new Column<UserVo, Boolean>(
				new CheckboxCell()) {
			@Override
			public Boolean getValue(UserVo o) {
				return selectionModel.isSelected(o);
			}
		};
		users = new HashMap<String, UserVo>();
		checkColumn.setFieldUpdater(new FieldUpdater<UserVo, Boolean>() {
			@Override
			public void update(int index, UserVo o, Boolean value) {
				if (value) {
					users.put(o.getId(), o);
				} else {
					users.remove(o.getId());
				}
				selectionModel.setSelected(o, value);
			}
		});
		resultTable.addColumn(checkColumn, "");

		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getName();
			}
		}, "账号名称");

		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getEnterpriseName();
			}
		}, "关联企业名称");

		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getMobile();
			}
		}, "手机");

		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getEmail();
			}
		}, "邮箱");

		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getCreatedAt() != null ? dateFormat.format(o
						.getCreatedAt()) : "";
			}
		}, "新建账号日期");

		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getStatus();
			}
		}, "状态");
		resultTable.addColumn(new TextColumn<UserVo>() {

			@Override
			public String getValue(UserVo o) {
				return o.getBalance()+"";
			}
		}, "积分");
		
		resultTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<UserVo, String>() {
					@Override
					public String getValue(UserVo userVo) {
						return "删除";
					}
				}, new FieldUpdater<UserVo, String>() {

					@Override
					public void update(int index, UserVo o, String value) {
						users.put(o.getId(), o);
					}

				});
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasValue<String> getAccountName() {
		return accountName;
	}

	@Override
	public HasValue<String> getEnterpriseName() {
		return enterpriseName;
	}

	@Override
	public HasValue<String> getMobile() {
		return mobile;
	}

	@Override
	public HasValue<String> getEmail() {
		return email;
	}

	@Override
	public String getStatus() {
		return status.getValue(status.getSelectedIndex());
	}

	@Override
	public HasClickHandlers getAddHandlers() {
		return add;
	}

	// @Override
	// public HasClickHandlers getDeleteHandlers() {
	// return delete;
	// }

	@Override
	public HasClickHandlers getActiveHandlers() {
		return active;
	}

	@Override
	public HasClickHandlers getLogOffHandlers() {
		return logOff;
	}

	@Override
	public HasClickHandlers getResetPwdHandlers() {
		return resetPwd;
	}

	/*
	 * Initialize user status.
	 */
	public void initUserStatus(Map<String, String> map) {
		status.addItem("未选择", "");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			status.addItem(entry.getValue(), entry.getKey());
		}
	}

	@Override
	public HasClickHandlers getSearchHandlers() {
		return search;
	}

	@Override
	public HasClickHandlers getResetHandlers() {
		return reset;
	}

	@Override
	public void setListViewAdapter(AsyncDataProvider<UserVo> listViewAdapter) {
		this.listViewAdapter = listViewAdapter;
		buildTable();
		this.listViewAdapter.addDataDisplay(resultTable);

	}

	@Override
	public void clean() {
		accountName.setValue("");
		email.setValue("");
		mobile.setValue("");
		status.setItemSelected(0, true);
		enterpriseId = null;
		enterpriseName.setValue(null);
	}

	@Override
	public Map<String, UserVo> getSelectedUsers() {
		return users;
	}

	@Override
	public HasClickHandlers getChooseHandlers() {
		return choose;
	}

	@Override
	public HasClickHandlers getTextChooseHandlers() {
		return enterpriseName;
	}

	@Override
	public String getEnterpriseId() {
		return enterpriseId;
	}

	@Override
	public void setEnterpriseInfo(String enterpriseId, String enterpriseName) {
		this.enterpriseId = enterpriseId;
		this.enterpriseName.setValue(enterpriseName);
	}

	/*
	 * @Override public void refresh() { resultTable.refresh(); }
	 */

	// @Override
	// public HasClickHandlers getSearchSubAccount() {
	// return searchSubAccount;
	// }

	@Override
	public HasClickHandlers getUpdateHandlers() {
		return Update;
	}

	@Override
	public HasClickHandlers getDeleteHandlers() {
		return delete;
	}

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}

}
