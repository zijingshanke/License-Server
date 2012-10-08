package com.chinarewards.gwt.license.client.user.dp;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.client.user.UserSearchRequest;
import com.chinarewards.gwt.license.client.user.UserSearchResponse;
import com.chinarewards.gwt.license.client.user.model.UserSearchVo;
import com.chinarewards.gwt.license.client.user.model.UserVo;
import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenter.UserSearchDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class UserSearchAsyncDataProvider extends AsyncDataProvider<UserVo> {
	final UserSearchVo criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final DispatchAsync dispatcher;
	final UserSearchDisplay display;

	public UserSearchAsyncDataProvider(UserSearchVo criteria,
			ErrorHandler errorHandler, SessionManager sessionManager,
			DispatchAsync dispatcher, UserSearchDisplay display) {
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.dispatcher = dispatcher;
		this.display=display;
	}

	@Override
	protected void onRangeChanged(HasData<UserVo> view) {
		int start = view.getVisibleRange().getStart();
		int length = view.getVisibleRange().getLength();
		GWT.log("Region Changed: " + start + ", " + length);
		criteria.setStart(start);
		criteria.setLength(length);
		fetchData(start, length);
	}

	private void fetchData(final int start, final int length) {
		dispatcher.execute(
				new UserSearchRequest(criteria, sessionManager.getSession()),
				new AsyncCallback<UserSearchResponse>() {
					@Override
					public void onFailure(Throwable t) {
						errorHandler.alert(t.getMessage(), t);
					}

					@Override
					public void onSuccess(UserSearchResponse response) {
						updateRowCount(response.getResult().getTotal(), true);
						updateRowData(start, response.getResult().getList());
						display.setDataCount(response.getResult().getTotal()+"");
					}
				});
	}
}
