package com.chinarewards.license.model.user;


import java.io.Serializable;

import com.chinarewards.license.model.common.PaginationDetail;
import com.chinarewards.license.model.common.SortingDetail;

/**
 * This class is designed to wrap the parameter to search main-accounts using
 * paging.
 * 
 * @author yanxin
 * @since 0.0.1 2010-9-26
 */
public class UserSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;

	private String status;

	private String accountName;

	private String mobile;

	private String email;

	private String corporationId;

	private String enterpriseId;

	private String enterpriseName;

	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

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

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	 * @return the paginationDetail
	 */
	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	/**
	 * @param paginationDetail
	 *            the paginationDetail to set
	 */
	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	/**
	 * @return the sortingDetail
	 */
	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	/**
	 * @param sortingDetail
	 *            the sortingDetail to set
	 */
	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
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
	 * @return the corporationId
	 */
	public String getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId
	 *            the corporationId to set
	 */
	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	@Override
	public String toString() {
		return "UserSearchCriteria [status=" + status + ", accountName="
				+ accountName + ", mobile=" + mobile + ", email=" + email
				+ ", corporationId=" + corporationId + ", enterpriseId="
				+ enterpriseId + ", enterpriseName=" + enterpriseName
				+ ", paginationDetail=" + paginationDetail + ", sortingDetail="
				+ sortingDetail + "]";
	}

}
