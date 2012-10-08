package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 添加或者修改一个员工时候员工的EMAIL在同一公司的员工表已经存在时候抛出这个异常
 * 
 * @author roger
 * @since 1.0.0 2010-09-19
 */
@ApplicationException(rollback = true)
public class EmailDuplicateInCorporationException extends Exception {

	private static final long serialVersionUID = -2497755184313967509L;

	public EmailDuplicateInCorporationException() {

	}

	public EmailDuplicateInCorporationException(String message) {
		super(message);
	}
}
