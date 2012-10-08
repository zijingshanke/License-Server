package com.chinarewards.gwt.certificate.client.certificate.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria.CertificateStatus;

public class CertificateClient implements Serializable, Comparable<CertificateClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

	public CertificateClient() {
	}

	// 状态
	private CertificateStatus status;
	private String id;
	private String name;
	
	private String titleName;

	protected String thisAction;

	/**
	 * 有效期
	 */
	private Date indate;



	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}


	public CertificateStatus getStatus() {
		return status;
	}

	public void setStatus(CertificateStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int compareTo(CertificateClient o) {
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
