package com.chinarewards.gwt.certificate.client.certificate.util;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateVo;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificatePresenter.CertificateDisplay;
import com.chinarewards.gwt.license.util.StringUtil;

/**
 * @author yanrui
 * */
public class CertificateAdapterClient {
	/**
	 * 封装表单属性
	 * */
	public static CertificateVo adapterDisplay(CertificateDisplay display) {
		CertificateVo licenseVo = new CertificateVo();

		// // 基本信息
		licenseVo.setName(display.getName().getValue().trim());
		licenseVo.setSummary(display.getSummary().getValue().trim());
		
		licenseVo.setDispatchcycle(display.getDispatchcycle().getValue().trim());
		licenseVo.setExplains(display.getExplains().getValue().trim());
		licenseVo.setNotes(display.getNotes().getValue().trim());

		int selectedIndex = display.getType().getSelectedIndex();
		licenseVo.setType(display.getType().getValue(selectedIndex));
		licenseVo.setBrand(display.getBrand().getValue().trim());

		licenseVo.setPhoto(display.getPhoto().getValue());
		licenseVo.setStock(StringUtil.valueOf(display.getStock().getValue()));
		licenseVo.setIntegral(StringUtil.valueOf(display.getIntegral().getValue()));

		if (display.getSupplyinner().getValue()) {
			licenseVo.setSource("inner");
		}
		if (display.getSupplyoutter().getValue()) {
			licenseVo.setSource("outter");
		}

//		System.out.println("=======adapterDisplay:" + licenseVo.getSource());

		licenseVo.setBusiness(display.getBusiness().getValue().trim());
		licenseVo.setAddress(display.getAddress().getValue().trim());
		licenseVo.setTell(display.getTell().getValue().trim());
		licenseVo.setServicetell(display.getServicetell().getValue().trim());

		licenseVo.setIndate(display.getIndate().getValue());

		return licenseVo;
	}
}
