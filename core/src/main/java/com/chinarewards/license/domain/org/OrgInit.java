package com.chinarewards.license.domain.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the  database table.
 * 
 * 
 */
@Entity
public class OrgInit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;
	private String corpId;//企业ID
	
	private int corpInit; //0是没有初始化注册企业，1是已有注册
	private int hrInit;   //0没有初始化HR账号，1是已有HR账号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public int getCorpInit() {
		return corpInit;
	}
	public void setCorpInit(int corpInit) {
		this.corpInit = corpInit;
	}
	public int getHrInit() {
		return hrInit;
	}
	public void setHrInit(int hrInit) {
		this.hrInit = hrInit;
	}

	
}