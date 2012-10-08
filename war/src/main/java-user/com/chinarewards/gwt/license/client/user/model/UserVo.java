package com.chinarewards.gwt.license.client.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The user in client...
 * 
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserVo implements Serializable, Comparable<UserVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6714807329511677467L;

	private String id;

	private String name;

	private String mobile;

	private String email;

	private String enterpriseId;

	private String enterpriseName;

	private Date createdAt;

	private String status;
	//beizhu
	private String description;
	//ziyuming
	private String subdomain;
	
	private String industryId;
	private double balance;
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getSubdomain() {
		return subdomain;
	}
	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserVo() {
	}
	public UserVo(String name,String mobile, String email,String enterpriseName) {
		this.name=name;
		this.mobile=mobile;
		this.email=email;
		this.enterpriseName=enterpriseName;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the enterpriseId
	 */
	public String getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * @param enterpriseId
	 *            the enterpriseId to set
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * @return the enterpriseName
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/**
	 * @param enterpriseName
	 *            the enterpriseName to set
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int compareTo(UserVo o) {
		return null == o ? -1 : o.getId().compareTo(id);
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", mobile=" + mobile
				+ ", email=" + email + ", enterpriseId=" + enterpriseId
				+ ", enterpriseName=" + enterpriseName + ", createdAt="
				+ createdAt + ", status=" + status + ", description="
				+ description + ", subdomain=" + subdomain + ", industryId="
				+ industryId + "]";
	}

}
