package com.chinarewards.license.service.license;

import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.model.common.PageStore;
import com.chinarewards.license.model.license.search.LicenseListVo;
import com.chinarewards.license.model.license.search.LicenseStatus;
import com.chinarewards.license.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author yanrui
 * @since 1.5
 */
public interface LicenseService {

	/**
	 * 保存
	 * @param context
	 * @param license
	 * @return
	 */
	public License save(UserContext context, License license);

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
	public PageStore<LicenseListVo> licenseList(UserContext context,LicenseListVo licenseListVo);



	public License createLicense(UserContext context, License license);
}
