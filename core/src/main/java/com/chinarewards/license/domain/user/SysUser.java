package com.chinarewards.license.domain.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.license.model.user.UserStatus;

@Entity
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4076124377833291323L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private String userName;

	private String password;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	private Date createdAt;

	private Date lastModifiedAt;

	@ManyToOne
	private SysUser createdBy;

	@ManyToOne
	private SysUser lastModifiedBy;

	@Enumerated(EnumType.STRING)
	private UserRole lastLoginRole;

	public UserRole getLastLoginRole() {
		return lastLoginRole;
	}

	public void setLastLoginRole(UserRole lastLoginRole) {
		this.lastLoginRole = lastLoginRole;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public SysUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}

	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}
