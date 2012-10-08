package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 当一个Organization的类型不是期望的类型时候抛出改异常
 * 
 * @author roger
 * @since 2010-12-23 0.2.0
 */
@ApplicationException(rollback = true)
public class OrganizationInstanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2119404192114259640L;

	public OrganizationInstanceException() {
	}

	public OrganizationInstanceException(String message) {
		super(message);
	}
}
