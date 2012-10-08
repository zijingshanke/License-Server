package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 添加或者修改一个员工时候员工的手机号码在同一公司的员工表已经存在时候抛出这个异常
 * 
 * @author roger
 * @since 1.0.0 2010-09-19
 */
@ApplicationException(rollback = true)
public class MobileNoDuplicateInCorporationException extends Exception {

	private static final long serialVersionUID = 479927165987186687L;

	public MobileNoDuplicateInCorporationException() {

	}

	public MobileNoDuplicateInCorporationException(String message) {
		super(message);
	}
}
