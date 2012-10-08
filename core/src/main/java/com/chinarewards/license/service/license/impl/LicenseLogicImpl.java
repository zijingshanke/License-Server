package com.chinarewards.license.service.license.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.license.dao.license.LicenseDao;
import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.common.PageStore;
import com.chinarewards.license.model.license.search.LicenseListVo;
import com.chinarewards.license.model.license.search.LicenseStatus;
import com.chinarewards.license.service.license.LicenseLogic;
import com.chinarewards.license.util.DateUtil;
import com.chinarewards.license.util.MachineUtil;
import com.chinarewards.license.util.StringUtil;
import com.google.inject.Inject;

import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

public class LicenseLogicImpl implements LicenseLogic {
	private LicenseDao licenseDao;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	protected LicenseLogicImpl(LicenseDao licenseDao) {
		this.licenseDao = licenseDao;
	}

	@Override
	public License createLicense(License license) {
		LicenseParam parameter = getNormalLicenseParam();

		LicenseContent content = new LicenseContent();
		content.setConsumerType("User");// 不能改
		content.setConsumerAmount(1);
		content.setSubject(SUBJECT);

		java.util.Calendar cal = java.util.Calendar.getInstance();
		content.setIssued(cal.getTime());// 发布时间

		content.setNotAfter(license.getNotafter());// 截止有效期

		String info = "<root>";
		info += "<licenseId></licenseId>";
		info += "<corporationId>" + license.getCorporationId()
				+ "</corporationId>";
		info += "<corporationName>" + license.getCorporationName()
				+ "</corporationName>";
		info += "<license-type>" + license.getLicenseType() + "</license-type>";

		info += "<macaddress>" + license.getMacaddress() + "</macaddress>";
		info += "<description>" + license.getDescription() + "</description>";
		info += "<staffNumber>" + license.getStaffNumber() + "</staffNumber>";
		info += "</root>";
		content.setInfo(info);

		String licenseFileName = createLicenseKey(parameter, content);// 创建License

		license.setLicenseFileName(licenseFileName);
		
		licenseDao.update(license);
		return license;
	}

	private static LicenseParam getNormalLicenseParam() {
		LicenseParam parameter = new DefaultLicenseParam(SUBJECT,
				Preferences.userRoot(), new DefaultKeyStoreParam(
						MyLicenseManager.class, // CUSTOMIZE
						KEYSTORE_RESOURCE, SUBJECT, KEYSTORE_STORE_PWD,
						KEYSTORE_KEY_PWD), new DefaultCipherParam(
						CIPHER_KEY_PWD));
		return parameter;
	}

	private static final String SUBJECT = "privatekey"; // CUSTOMIZE
	private static final String KEYSTORE_RESOURCE = "privateKeys.store"; // 私匙库文件名
	private static final String KEYSTORE_STORE_PWD = "privatestore123"; // 私匙库密码
	private static final String KEYSTORE_KEY_PWD = "privatekey123"; // 私匙库主键密码
	private static final String CIPHER_KEY_PWD = "a8a8a8"; // 即将生成的license密码

	private String createLicenseKey(LicenseParam parameter,
			LicenseContent content) {
		String result = "";
		LicenseManager manager = new LicenseManager(parameter);
		try {
			String fileName = "license";
			fileName += DateUtil.getDateString("yyyyMMddHHmmss");
			fileName += new Random().nextInt(10000);
			fileName += ".lic";

			String filePath = getLicenseStorePath() + fileName;
			manager.store(content, new File(filePath));

			result = fileName;
		} catch (Exception exc) {
			System.err.println("Could not save license key");
			exc.printStackTrace();
			result = "FAILED";
		}
		return result;
	}

	public static String getLicenseStorePath() {
		String realPath = "";
		realPath = MyLicenseManager.class.getResource("").getPath();

		System.out.println(realPath);

		if (!StringUtil.isEmptyString(realPath)) {
			int rootIndex = realPath.indexOf("jboss-5.1.0.GA");
			if (rootIndex <0) {
				rootIndex = realPath.indexOf("elt/war");
				if (rootIndex>-1) {
					rootIndex+=4;
				}
			}
			if (rootIndex <0) {
				rootIndex = realPath.indexOf("war/war");
//				if (rootIndex>-1) {
//					rootIndex+=4;
//				}
			}
			if (rootIndex <0) {
				rootIndex = realPath.indexOf("elt/core");
				if (rootIndex>-1) {
					rootIndex+=4;
				}
			}

			if (rootIndex < 0) {
				return null;
			} else {
				realPath = realPath.substring(0, rootIndex);
			}

			int firstIndex = realPath.indexOf("/");
			if (firstIndex == 0) {
				if (MachineUtil.getIsWindowsOS()) {
					realPath = realPath.substring(1, realPath.length());
				}				
			}

			realPath=realPath.replace("file:/", "");

			realPath = realPath + "licenseStore" + File.separator;
		}

		System.out.println(realPath);
		return realPath;
	}

	@Override
	public License save(SysUser caller, License license) {
		Date currTime = DateUtil.getTime();

		if (StringUtil.isEmptyString(license.getId())) {
			// Create
			license.setDeleted(false);
			license.setAwarduser(caller.getUserName());
			license.setIssued(currTime);
			license.setStatus(LicenseStatus.NORMAL);
			license=licenseDao.save(license);
		} else {
			// Update
			License tempLicense = licenseDao.findById(License.class,
					license.getId());
			tempLicense.setCorporationId(license.getCorporationId());
			tempLicense.setCorporationName(license.getCorporationName());
			tempLicense.setLicenseType(license.getLicenseType());
			tempLicense.setStaffNumber(license.getStaffNumber());
			tempLicense.setMacaddress(license.getMacaddress());
			tempLicense.setDescription(license.getDescription());
			tempLicense.setNotafter(license.getNotafter());

			tempLicense.setAwarduser(caller.getUserName());
			tempLicense.setIssued(currTime);
			license=licenseDao.update(tempLicense);
		}

		return license;
	}

	@Override
	public License findLicenseById(String id) {

		return licenseDao.findById(License.class, id);
	}

	@Override
	public String deleteLicense(String id) {
		License license = licenseDao.findById(License.class, id);
		license.setDeleted(true);
		license = licenseDao.update(license);
		return license.getId();
	}

	@Override
	public PageStore<LicenseListVo> licenseList(SysUser caller,
			LicenseListVo licenseVo) {

		PageStore<License> pageStore = new PageStore<License>();
		pageStore.setResultCount(licenseDao.countLicense(licenseVo));
		List<License> licenseList = licenseDao.licenseList(licenseVo);
		List<LicenseListVo> licenseVoList = new ArrayList<LicenseListVo>();
		for (License license : licenseList) {
			licenseVoList.add(convertFromLicenseToVo(license));
		}
		PageStore<LicenseListVo> storeVo = new PageStore<LicenseListVo>();
		storeVo.setResultCount(pageStore.getResultCount());
		storeVo.setResultList(licenseVoList);

		return storeVo;
	}

	private LicenseListVo convertFromLicenseToVo(License license) {
		LicenseListVo licenseVo = new LicenseListVo();
		licenseVo.setId(license.getId());
		licenseVo.setCorporationId(license.getCorporationId());
		licenseVo.setCorporationName(license.getCorporationName());
		licenseVo.setLicenseType(license.getLicenseType());
		licenseVo.setStaffNumber(license.getStaffNumber());
		licenseVo.setMacaddress(license.getMacaddress());
		licenseVo.setDescription(license.getDescription());
		licenseVo.setNotafter(license.getNotafter());
		licenseVo.setAwarduser(license.getAwarduser());
		licenseVo.setIssued(license.getIssued());

		licenseVo.setDeleted(license.isDeleted());

		licenseVo.setStatus(license.getStatus());
		
		licenseVo.setLicenseFileName(license.getLicenseFileName());

		return licenseVo;
	}

}
