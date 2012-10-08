package com.chinarewards.gwt.license.client.license.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.license.client.license.model.LicenseCriteria.LicenseStatus;
import com.chinarewards.gwt.license.client.util.StringUtil;

public class LicenseClient implements Serializable, Comparable<LicenseClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

	public LicenseClient() {
	}


	private String id;
	private String corporationId;
	private String corporationName;
	private String licenseType;
	private int staffNumber;
	private String macaddress;
	private String description;
	private Date notafter;// 有效截止期

	private boolean deleted; // 删除状态

	private Date issued; // 授权时间
	private String awarduser; // 授权人
	
	// 状态
	private LicenseStatus status;
	
	private String licenseFileName;
	
	private String titleName;

	protected String thisAction;

	public String getLicenseTypeText(){
		if (licenseType != null) {
			if (StringUtil.trim(licenseType).equals("TRIAL")) {
				return "试用版";
			}
			if (StringUtil.trim(licenseType).equals("OFFICIAL")) {
				return "正式版";
			}
		}
		return "";
	}


	public LicenseStatus getStatus() {
		return status;
	}

	public void setStatus(LicenseStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}




	public String getLicenseFileName() {
		return licenseFileName;
	}


	public void setLicenseFileName(String licenseFileName) {
		this.licenseFileName = licenseFileName;
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

	@Override
	public int compareTo(LicenseClient o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	

}
