package com.chinarewards.gwt.license.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.license.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.model.LicenseCriteria;
import com.chinarewards.gwt.license.client.license.presenter.LicenseListPresenter.LicenseListDisplay;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseResponse;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LicenseListViewAdapter extends BaseDataProvider<LicenseClient> {

	final DispatchAsync dispatch;
	final LicenseCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final LicenseListDisplay display;

	public LicenseListViewAdapter(DispatchAsync dispatch, LicenseCriteria criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, LicenseListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		if (getSorting() != null) {
			criteria.setSorting(getSorting());
		}
		dispatch.execute(new SearchLicenseRequest(criteria, sessionManager
				.getSession().getCorporationId(), sessionManager.getSession()
				.getUserRoles(), sessionManager.getSession().getToken()),
				new AsyncCallback<SearchLicenseResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchLicenseResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal()+"");
					}

				});
	}

}
