package com.chinarewards.gwt.license.server.license;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.slf4j.Logger;
import com.chinarewards.license.model.common.PageStore;
import com.chinarewards.license.model.common.PaginationDetail;
import com.chinarewards.license.model.common.SortingDetail;
import com.chinarewards.license.model.license.search.LicenseListVo;
import com.chinarewards.license.model.license.search.LicenseStatus;
import com.chinarewards.license.model.user.UserContext;
import com.chinarewards.license.service.license.LicenseService;
import com.chinarewards.gwt.license.adapter.license.LicenseAdapter;
import com.chinarewards.gwt.license.client.license.model.LicenseCriteria;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchLicenseHandler extends
		BaseActionHandler<SearchLicenseRequest, SearchLicenseResponse> {

	@InjectLogger
	Logger logger;
	LicenseService licenseService;

	@Inject
	public SearchLicenseHandler(LicenseService licenseService) {
		this.licenseService = licenseService;

	}

	@Override
	public SearchLicenseResponse execute(SearchLicenseRequest request,
			ExecutionContext context) throws DispatchException {

		SearchLicenseResponse resp = new SearchLicenseResponse();

		LicenseCriteria license = request.getLicense();
		LicenseListVo criteria = adapter(license);
		PageStore<LicenseListVo> licensePage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getCorporationId());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserRoles()));
		uc.setUserId(request.getUserId());

		licensePage = licenseService.licenseList(uc, criteria);
		
		resp.setTotal(licensePage.getResultCount());
		resp.setResult(LicenseAdapter.adapter(licensePage.getResultList()));

		return resp;
	}

	private LicenseListVo adapter(LicenseCriteria criteria) {
		LicenseListVo vo = new LicenseListVo();
		if (criteria.getCorporationName() != null) {
			vo.setCorporationName(criteria.getCorporationName());
		}
		if (criteria.getStatus() != null) {
			vo.setStatus(LicenseStatus.valueOf(criteria.getStatus().toString()));
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
	public Class<SearchLicenseRequest> getActionType() {
		return SearchLicenseRequest.class;
	}

	@Override
	public void rollback(SearchLicenseRequest req, SearchLicenseResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
