package com.chinarewards.license.domain.license;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.license.model.license.search.LicenseStatus;

/**
 * The persistent class for the database table. 授权记录
 * 
 */
@Entity
public class License implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;
	private String corporationId;
	private String corporationName;
	private String licenseType;
	private int staffNumber;
	private String macaddress;
	private String description;
	private Date notafter;// 有效截止期
	
	private String licenseFileName;//文件名

	private boolean deleted; // 删除状态

	private Date issued; // 授权时间
	private String awarduser; // 授权人

	@Enumerated(EnumType.STRING)
	private LicenseStatus status;// 状态

	public final static String TYPE_TRIAL = "TRIAL";// 实物
	public final  static String TYPE_OFFICIAL = "OFFICIAL";// 虚拟

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

	public LicenseStatus getStatus() {
		return status;
	}

	public void setStatus(LicenseStatus status) {
		this.status = status;
	}

	public static String getTYPE_TRIAL() {
		return TYPE_TRIAL;
	}


	public static String getTYPE_OFFICIAL() {
		return TYPE_OFFICIAL;
	}
}