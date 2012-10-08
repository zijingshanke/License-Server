package com.chinarewards.license.model.user;

/**
 * The model to help save or update.
 * 
 * @author yanxin
 * @since 1.0
 */
public class UserVo {
	private String id;
	private String username;
	private String password;
	private String corporationId;
	private String staffId;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}
}
