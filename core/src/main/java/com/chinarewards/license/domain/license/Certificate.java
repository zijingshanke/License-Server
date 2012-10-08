package com.chinarewards.license.domain.license;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.license.model.license.search.CertificateStatus;

/**
 * 
 * 数字证书
 * @author yanrui
 * 
 */
public class Certificate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;

	private String name;
	private String type;// 类型
	private Date createtime;// 签发时间
	private String signuser;// 签发人

	@Enumerated(EnumType.STRING)
	private CertificateStatus status;// 状态

	private boolean deleted; // 删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSignuser() {
		return signuser;
	}

	public void setSignuser(String signuser) {
		this.signuser = signuser;
	}

	public CertificateStatus getStatus() {
		return status;
	}

	public void setStatus(CertificateStatus status) {
		this.status = status;
	}

}
