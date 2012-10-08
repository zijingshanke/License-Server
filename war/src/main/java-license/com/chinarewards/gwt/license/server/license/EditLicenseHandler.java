package com.chinarewards.gwt.license.server.license;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.license.request.EditLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.EditLicenseResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.model.license.search.LicenseStatus;
import com.chinarewards.license.model.user.UserContext;
import com.chinarewards.license.service.license.LicenseService;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditLicenseHandler extends
		BaseActionHandler<EditLicenseRequest, EditLicenseResponse> {

	@InjectLogger
	Logger logger;
	LicenseService licenseService;

	@Inject
	public EditLicenseHandler(LicenseService licenseService) {
		this.licenseService = licenseService;
	}

	@Override
	public Class<EditLicenseRequest> getActionType() {
		return EditLicenseRequest.class;
	}

	@Override
	public EditLicenseResponse execute(EditLicenseRequest action,
			ExecutionContext context) throws DispatchException {

		LicenseVo licenseVo = action.getLicenseVo();

		License license = assembleLicense(licenseVo);

		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession()
				.getUserRoles()));
		License AdddItem = licenseService.createLicense(uc, license);

		return new EditLicenseResponse(AdddItem.getId());
		
	} 

	/**
	 * Convert from LicenseVo to GeneratorLicenseModel.
	 */
	public static License assembleLicense(LicenseVo licenseVo) {
		License license = new License();
		license.setId(licenseVo.getId());
		license.setCorporationId(licenseVo.getCorporationId());
		license.setCorporationName(licenseVo.getCorporationName());
		license.setLicenseType(licenseVo.getLicenseType());
		license.setStaffNumber(licenseVo.getStaffNumber());
		license.setMacaddress(licenseVo.getMacaddress());
		license.setDescription(licenseVo.getDescription());
		license.setNotafter(licenseVo.getNotafter());
		
		
		return license;
	}

	@Override
	public void rollback(EditLicenseRequest action, EditLicenseResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
