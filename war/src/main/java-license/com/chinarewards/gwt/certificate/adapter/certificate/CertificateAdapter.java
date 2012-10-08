package com.chinarewards.gwt.certificate.adapter.certificate;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria.CertificateStatus;
import com.chinarewards.license.model.license.search.CertificateListVo;

/**
 * This utility class use to adapter EJB entity to WAR domain.
 * 
 * @author yanrui
 */
public class CertificateAdapter {

	public static CertificateClient adapter(CertificateListVo Certificate) {
		if (null == Certificate) {
			return null;
		}

		CertificateClient result = new CertificateClient();

		result.setId(Certificate.getId());
		result.setName(Certificate.getName());
		result.setIndate(Certificate.getIndate());

		if (Certificate.getStatus() != null) {
			result.setStatus(CertificateStatus.valueOf(Certificate.getStatus().toString()));
		}
		return result;
	}

	public static List<CertificateClient> adapter(List<CertificateListVo> CertificateList) {
		if (null == CertificateList) {
			return null;
		}

		List<CertificateClient> resultList = new ArrayList<CertificateClient>();
		for (CertificateListVo Certificate : CertificateList) {
			resultList.add(adapter(Certificate));
		}
		return resultList;
	}
	
}
