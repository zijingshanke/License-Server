package com.chinarewards.gwt.license.client.license.model;

import java.io.Serializable;
import java.util.Date;

public class LicenseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String corporationId;
	private String corporationName;
	private String licenseType;
	private int staffNumber;
	private String macaddress;
	private String description;
	private Date notafter;//有效截止期

	public static String TYPE_TRIAL = "TRIAL";// 实物
	public static String TYPE_OFFICIAL = "OFFICIAL";// 虚拟


	private boolean deleted; // 删除状态
	
	private Date issued; // 授权时间
	private String awarduser; // 授权人

	private String licenseFileName;
	
	public LicenseVo() {
	}

	public String getLicenseTypeText() {
		if (licenseType != null) {
			if (licenseType.equals(TYPE_TRIAL)) {
				return "试用版";
			} else if (licenseType.equals(TYPE_OFFICIAL)) {
				return "正式版";
			} else {
				return "未定义";
			}
		}
		return "";
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public int getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(int staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getNotafter() {
		return notafter;
	}

	public void setNotafter(Date notafter) {
		this.notafter = notafter;
	}

	public static String getTYPE_TRIAL() {
		return TYPE_TRIAL;
	}

	public static void setTYPE_TRIAL(String tYPE_TRIAL) {
		TYPE_TRIAL = tYPE_TRIAL;
	}

	public static String getTYPE_OFFICIAL() {
		return TYPE_OFFICIAL;
	}

	public static void setTYPE_OFFICIAL(String tYPE_OFFICIAL) {
		TYPE_OFFICIAL = tYPE_OFFICIAL;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getIssued() {
		return issued;
	}

	public void setIssued(Date issued) {
		this.issued = issued;
	}

	public String getAwarduser() {
		return awarduser;
	}

	public void setAwarduser(String awarduser) {
		this.awarduser = awarduser;
	}

	public String getLicenseFileName() {
		return licenseFileName;
	}

	public void setLicenseFileName(String licenseFileName) {
		this.licenseFileName = licenseFileName;
	}
	
	

	
}