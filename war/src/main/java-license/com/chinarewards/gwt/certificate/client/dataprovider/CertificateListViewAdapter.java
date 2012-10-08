package com.chinarewards.gwt.certificate.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateListPresenter.CertificateListDisplay;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateResponse;
import com.chinarewards.gwt.license.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.license.client.mvp.ErrorHandler;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CertificateListViewAdapter extends BaseDataProvider<CertificateClient> {

	final DispatchAsync dispatch;
	final CertificateCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final CertificateListDisplay display;

	public CertificateListViewAdapter(DispatchAsync dispatch, CertificateCriteria criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, CertificateListDisplay display) {
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
		dispatch.execute(new SearchCertificateRequest(criteria, sessionManager
				.getSession().getCorporationId(), sessionManager.getSession()
				.getUserRoles(), sessionManager.getSession().getToken()),
				new AsyncCallback<SearchCertificateResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchCertificateResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal()+"");
					}

				});
	}

}
