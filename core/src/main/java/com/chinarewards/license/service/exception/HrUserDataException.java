package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 一个Corporation在HrUser存在多个可用企业帐号时候抛出该异常
 * 
 * @author sunhongliang
 * @since 0.2.0 2011-02-12
 */
@ApplicationException(rollback = true)
public class HrUserDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7522396673645868977L;

	public HrUserDataException() {
	}

	public HrUserDataException(String message) {
		super(message);
	}
}
