package com.chinarewards.license.service.exception;

/**
 * 这个原员工还没加入这个企业的组织时候抛出这个异常
 * 
 * @author roger
 * @since 1.0.0 2010-09-27
 */
public class StaffHasNotJoinThisPartnerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3490008636755073567L;

	public StaffHasNotJoinThisPartnerException() {

	}

	public StaffHasNotJoinThisPartnerException(String message) {
		super(message);
	}
}
