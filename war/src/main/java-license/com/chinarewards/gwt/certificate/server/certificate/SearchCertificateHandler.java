package com.chinarewards.gwt.certificate.server.certificate;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.gwt.certificate.adapter.certificate.CertificateAdapter;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.chinarewards.license.model.common.PageStore;
import com.chinarewards.license.model.common.PaginationDetail;
import com.chinarewards.license.model.common.SortingDetail;
import com.chinarewards.license.model.license.search.CertificateListVo;
import com.chinarewards.license.model.license.search.CertificateStatus;
import com.chinarewards.license.model.user.UserContext;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchCertificateHandler extends
		BaseActionHandler<SearchCertificateRequest, SearchCertificateResponse> {

	@InjectLogger
	Logger logger;
//	CertificateService licenseService;

	@Inject
	public SearchCertificateHandler(/*CertificateService licenseService*/) {
//		this.licenseService = licenseService;

	}

	@Override
	public SearchCertificateResponse execute(SearchCertificateRequest request,
			ExecutionContext context) throws DispatchException {

		SearchCertificateResponse resp = new SearchCertificateResponse();

		CertificateCriteria license = request.getCertificate();
		CertificateListVo criteria = adapter(license);
		PageStore<CertificateListVo> licensePage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getCorporationId());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserRoles()));
		uc.setUserId(request.getUserId());

//		licensePage = licenseService.licenseList(uc, criteria);
		
		resp.setTotal(licensePage.getResultCount());
		resp.setResult(CertificateAdapter.adapter(licensePage.getResultList()));

		return resp;
	}

	private CertificateListVo adapter(CertificateCriteria criteria) {
		CertificateListVo vo = new CertificateListVo();
		if (criteria.getName() != null) {
			vo.setName(criteria.getName());
		}
		if (criteria.getStatus() != null) {
			vo.setStatus(CertificateStatus.valueOf(criteria.getStatus().toString()));
		}
		if (criteria.getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(criteria.getPagination().getLimit());
			detail.setStart(criteria.getPagination().getStart());

			vo.setPaginationDetail(detail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			vo.setSortingDetail(sortingDetail);
		}
		return vo;
	}

	@Override
	public Class<SearchCertificateRequest> getActionType() {
		return SearchCertificateRequest.class;
	}

	@Override
	public void rollback(SearchCertificateRequest req, SearchCertificateResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
