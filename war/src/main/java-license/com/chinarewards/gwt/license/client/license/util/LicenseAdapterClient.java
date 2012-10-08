package com.chinarewards.gwt.license.client.license.util;

import com.chinarewards.gwt.license.client.license.model.LicenseVo;
import com.chinarewards.gwt.license.client.license.presenter.LicensePresenter.LicenseDisplay;
import com.chinarewards.gwt.license.util.StringUtil;

/**
 * @author yanrui
 * */
public class LicenseAdapterClient {
	/**
	 * 封装表单属性
	 * */
	public static LicenseVo adapterDisplay(LicenseDisplay display) {
		LicenseVo licenseVo = new LicenseVo();

		// // 基本信息
		licenseVo
				.setCorporationId(display.getCorporationId().getValue().trim());
		licenseVo.setCorporationName(display.getCorporationName().getValue()
				.trim());
		licenseVo.setStaffNumber(StringUtil.valueOf(display.getStaffNumber()
				.getValue().trim()));

		int selectedIndex = display.getLicenseType().getSelectedIndex();
		licenseVo.setLicenseType(display.getLicenseType().getValue(
				selectedIndex));

		licenseVo.setMacaddress(display.getMacaddress().getValue());
		licenseVo.setDescription(display.getDescription().getValue());

		// System.out.println("=======adapterDisplay:" + licenseVo.getSource());

		licenseVo.setNotafter(display.getNotafter().getValue());
		
		return licenseVo;
	}
}
