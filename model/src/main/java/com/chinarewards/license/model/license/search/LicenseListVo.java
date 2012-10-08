package com.chinarewards.license.model.license.search;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.license.model.common.PaginationDetail;
import com.chinarewards.license.model.common.SortingDetail;

public class LicenseListVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The paging detail, contains the info it requires.
	 */
	private PaginationDetail paginationDetail;

	/**
	 * Sorting detail.
	 */
	private SortingDetail sortingDetail;
	private String id;
	private String corporationId;
	private String corporationName;
	private String licenseType;
	private int staffNumber;
	private String macaddress;
	private String description;
	private Date notafter;// 有效截止期
	private Date issued; // 授权时间
	private String awarduser; // 授权人
    private LicenseStatus status;    //状态（上下架）
    private boolean deleted;   //删除状态
    
    private String licenseFileName;//
	

	public LicenseListVo() {
	}

	
	

	public String getLicenseFileName() {
		return licenseFileName;
	}




	public void setLicenseFileName(String licenseFileName) {
		this.licenseFileName = licenseFileName;
	}




	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}


	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}


	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}


	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
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


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
}