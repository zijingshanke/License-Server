package com.chinarewards.gwt.license.server.license;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.service.license.LicenseService;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchLicenseByIdHandler extends
		BaseActionHandler<SearchLicenseByIdRequest, SearchLicenseByIdResponse> {
	@InjectLogger
	Logger logger;
	LicenseService licenseService;

	@Inject
	public SearchLicenseByIdHandler(LicenseService licenseService) {
		this.licenseService = licenseService;
	}

	@Override
	public SearchLicenseByIdResponse execute(SearchLicenseByIdRequest request,
			ExecutionContext context) throws DispatchException {
		logger.debug(" parameters:{}", request.getId());
		License license = licenseService.findLicenseById(request.getId());
		return new SearchLicenseByIdResponse(adapter(license));

	}

	private LicenseVo adapter(License license) {
		LicenseVo licenseVo = new LicenseVo();
		licenseVo.setId(license.getId());
		licenseVo.setCorporationId(license.getCorporationId());
		licenseVo.setCorporationName(license.getCorporationName());
		licenseVo.setLicenseType(license.getLicenseType());
		licenseVo.setStaffNumber(license.getStaffNumber());
		licenseVo.setMacaddress(license.getMacaddress());
		licenseVo.setDescription(license.getDescription());
		licenseVo.setNotafter(license.getNotafter());
		licenseVo.setIssued(license.getIssued());
		licenseVo.setAwarduser(license.getAwarduser());// 授权人
		
		licenseVo.setLicenseFileName(license.getLicenseFileName());

		return licenseVo;
	}

	@Override
	public Class<SearchLicenseByIdRequest> getActionType() {
		return SearchLicenseByIdRequest.class;
	}

	@Override
	public void rollback(SearchLicenseByIdRequest arg0,
			SearchLicenseByIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
