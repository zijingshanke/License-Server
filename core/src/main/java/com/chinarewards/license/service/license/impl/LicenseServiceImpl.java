package com.chinarewards.license.service.license.impl;

import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.common.PageStore;
import com.chinarewards.license.model.license.search.LicenseListVo;
import com.chinarewards.license.model.user.UserContext;
import com.chinarewards.license.service.license.LicenseLogic;
import com.chinarewards.license.service.license.LicenseService;
import com.chinarewards.license.service.user.UserLogic;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
@Transactional
public class LicenseServiceImpl implements LicenseService {
	private final LicenseLogic licenseLogic;
	private final UserLogic userLogic;

	@Inject
	public LicenseServiceImpl(LicenseLogic licenseLogic,UserLogic userLogic) {
		this.userLogic = userLogic;
		this.licenseLogic = licenseLogic;
		
	}
	@Override
	public License save(UserContext context, License license) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		License licenses = licenseLogic.save(caller, license);
		return licenses;
	}
	
	@Override
	public License createLicense(UserContext context, License license) {
		
		SysUser caller = userLogic.findUserById(context.getUserId());
		
		License newlicense = licenseLogic.save(caller, license);			

		newlicense =licenseLogic.createLicense(newlicense);
		
		return newlicense;
	}
	

	@Override
	public License findLicenseById(String id) {
		
		return licenseLogic.findLicenseById(id);
	}

	@Override
	public String deleteLicense(String id) {
		
		return licenseLogic.deleteLicense(id);
	}

	@Override
	public PageStore<LicenseListVo> licenseList(UserContext context, LicenseListVo licenseVo) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		return licenseLogic.licenseList(caller, licenseVo);
	}


}
