package com.chinarewards.license.service.license;

import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.common.PageStore;
import com.chinarewards.license.model.license.search.LicenseListVo;

public interface LicenseLogic {
	/**
	 * 保存
	 * @param context
	 * @param license
	 * @return
	 */
	public License save(SysUser caller, License license);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public License findLicenseById(String id);
	/**
	 * 删除授权证书根据ID
	 * @param id
	 * @return
	 */
	public String deleteLicense(String id);
	/**
	 * 授权证书列表
	 * @param context
	 * @param license
	 * @return
	 */
	public PageStore<LicenseListVo> licenseList(SysUser caller,LicenseListVo licenseVo);


	/**
	 * 生成license文件
	 * @param id
	 * @return
	 */
	public License createLicense(License license);
	
	
}


