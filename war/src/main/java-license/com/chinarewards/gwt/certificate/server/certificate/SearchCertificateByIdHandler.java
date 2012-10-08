package com.chinarewards.gwt.certificate.server.certificate;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateByIdRequest;
import com.chinarewards.gwt.certificate.client.certificate.request.SearchCertificateByIdResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.chinarewards.license.domain.license.Certificate;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchCertificateByIdHandler extends
		BaseActionHandler<SearchCertificateByIdRequest, SearchCertificateByIdResponse> {
	@InjectLogger
	Logger logger;
//	CertificateService licenseService;

	@Inject
	public SearchCertificateByIdHandler(/*CertificateService licenseService*/) {
//		this.licenseService = licenseService;
	}

	@Override
	public SearchCertificateByIdResponse execute(SearchCertificateByIdRequest request,
			ExecutionContext context) throws DispatchException {
		logger.debug(" parameters:{}", request.getId());
//		Certificate license = licenseService.findCertificateById(request.getId());
//		return new SearchCertificateByIdResponse(adapter(license));
		return new SearchCertificateByIdResponse();

	}

	private CertificateVo adapter(Certificate license) {
		CertificateVo licenseVo = new CertificateVo();
		licenseVo.setId(license.getId());
		licenseVo.setName(license.getName());
//		licenseVo.setSummary(license.getSummary());

		return licenseVo;
	}

	@Override
	public Class<SearchCertificateByIdRequest> getActionType() {
		return SearchCertificateByIdRequest.class;
	}

	@Override
	public void rollback(SearchCertificateByIdRequest arg0,
			SearchCertificateByIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
