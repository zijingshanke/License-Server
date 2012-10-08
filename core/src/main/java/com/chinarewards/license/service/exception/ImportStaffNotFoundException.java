package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 无员工可导入时抛出该异常
 * 
 * @author sunhongliang
 * @since 2.0.0 2011-01-05
 */
@ApplicationException(rollback = true)
public class ImportStaffNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048763120543119992L;

	public ImportStaffNotFoundException() {

	}

	public ImportStaffNotFoundException(String message) {
		super(message);
	}

	public ImportStaffNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
