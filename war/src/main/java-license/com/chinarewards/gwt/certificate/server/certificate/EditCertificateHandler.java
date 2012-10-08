package com.chinarewards.gwt.certificate.server.certificate;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.certificate.client.certificate.request.EditCertificateRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.EditCertificateResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.chinarewards.license.domain.license.Certificate;
import com.chinarewards.license.model.user.UserContext;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditCertificateHandler extends
		BaseActionHandler<EditCertificateRequest, EditCertificateResponse> {

	@InjectLogger
	Logger logger;
//	CertificateService licenseService;

	@Inject
	public EditCertificateHandler(/*CertificateService licenseService*/) {
//		this.licenseService = licenseService;
	}

	@Override
	public Class<EditCertificateRequest> getActionType() {
		return EditCertificateRequest.class;
	}

	@Override
	public EditCertificateResponse execute(EditCertificateRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("AddCertificateResponse , license:" + action.getCertificateVo().toString());
		logger.debug("AddCertificateResponse ,rewardId=" + action.getCertificateVo().getId());

		CertificateVo licenseVo = action.getCertificateVo();

		Certificate license = assembleCertificate(licenseVo);

		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession()
				.getUserRoles()));
//		Certificate AdddItem = licenseService.save(uc, license);

//		return new EditCertificateResponse(AdddItem.getId());
		
		return new EditCertificateResponse();
		
	}

	/**
	 * Convert from CertificateVo to GeneratorCertificateModel.
	 */
	public static Certificate assembleCertificate(CertificateVo licenseVo) {
		Certificate license = new Certificate();
		license.setId(licenseVo.getId());
		license.setName(licenseVo.getName());

//		license.setSummary(licenseVo.getSummary());

		System.out.println("assembleCertificate(licenseVo):" + licenseVo.getSummary());


		return license;
	}

	@Override
	public void rollback(EditCertificateRequest action, EditCertificateResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
