package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 把员工创建成CRM的会员发生异常时抛出该异常
 * 
 * @author roger
 * @since 1.0.0 2010-09-21
 */
@ApplicationException(rollback = true)
public class CreateCRMemberHappenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2311866981588871714L;

	public CreateCRMemberHappenException() {

	}

	public CreateCRMemberHappenException(String message) {
		super(message);
	}

	public CreateCRMemberHappenException(String message, Throwable cause) {
		super(message, cause);
	}

}
