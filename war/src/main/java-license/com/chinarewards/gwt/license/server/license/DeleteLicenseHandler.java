package com.chinarewards.gwt.license.server.license;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.license.service.license.LicenseService;
import com.chinarewards.gwt.license.client.license.request.DeleteLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.DeleteLicenseResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DeleteLicenseHandler extends
		BaseActionHandler<DeleteLicenseRequest, DeleteLicenseResponse> {

	@InjectLogger
	Logger logger;

	LicenseService licenseService;

	@Inject
	public DeleteLicenseHandler(LicenseService licenseService) {
		this.licenseService = licenseService;
	}

	@Override
	public DeleteLicenseResponse execute(DeleteLicenseRequest request,
			ExecutionContext context) throws DispatchException {

		String totle = licenseService.deleteLicense(request.getLicenseId());

		return new DeleteLicenseResponse(totle);
	}

	
	@Override
	public Class<DeleteLicenseRequest> getActionType() {
		return DeleteLicenseRequest.class;
	}

	@Override
	public void rollback(DeleteLicenseRequest req, DeleteLicenseResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
