package com.chinarewards.gwt.certificate.server.certificate;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.gwt.certificate.client.certificate.request.DeleteCertificateRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.DeleteCertificateResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DeleteCertificateHandler extends
		BaseActionHandler<DeleteCertificateRequest, DeleteCertificateResponse> {

	@InjectLogger
	Logger logger;

//	CertificateService licenseService;

	@Inject
	public DeleteCertificateHandler(/*CertificateService licenseService*/) {
//		this.licenseService = licenseService;
	}

	@Override
	public DeleteCertificateResponse execute(DeleteCertificateRequest request,
			ExecutionContext context) throws DispatchException {

//wating.....最后修改人,最后修改时间
//		String totle = licenseService.deleteCertificate(request.getCertificateId());
		String totle="0";
		return new DeleteCertificateResponse(totle);
	}

	
	@Override
	public Class<DeleteCertificateRequest> getActionType() {
		return DeleteCertificateRequest.class;
	}

	@Override
	public void rollback(DeleteCertificateRequest req, DeleteCertificateResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
