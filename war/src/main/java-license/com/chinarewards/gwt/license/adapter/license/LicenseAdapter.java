package com.chinarewards.gwt.license.adapter.license;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.model.LicenseCriteria.LicenseStatus;
import com.chinarewards.license.model.license.search.LicenseListVo;

/**
 * This utility class use to adapter EJB entity to WAR domain.
 * 
 * @author yanrui
 */
public class LicenseAdapter {

	public static LicenseClient adapter(LicenseListVo listVo) {
		if (null == listVo) {
			return null;
		}

		LicenseClient result = new LicenseClient();

		result.setId(listVo.getId());
		result.setCorporationId(listVo.getCorporationId());
		result.setCorporationName(listVo.getCorporationName());
		result.setLicenseType(listVo.getLicenseType());
		result.setStaffNumber(listVo.getStaffNumber());
		result.setMacaddress(listVo.getMacaddress());
		result.setDescription(listVo.getDescription());
		result.setIssued(listVo.getIssued());
		result.setAwarduser(listVo.getAwarduser());
		result.setNotafter(listVo.getNotafter());


		if (listVo.getStatus() != null) {
			result.setStatus(LicenseStatus.valueOf(listVo.getStatus().toString()));
		}
		
		result.setLicenseFileName(listVo.getLicenseFileName());
		
		return result;
	}

	public static List<LicenseClient> adapter(List<LicenseListVo> licenseList) {
		if (null == licenseList) {
			return null;
		}

		List<LicenseClient> resultList = new ArrayList<LicenseClient>();
		for (LicenseListVo license : licenseList) {
			resultList.add(adapter(license));
		}
		return resultList;
	}
	
}
