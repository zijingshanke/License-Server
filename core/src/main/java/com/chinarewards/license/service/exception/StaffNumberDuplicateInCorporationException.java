package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 添加或者修改一个员工时候员工的员工编号在同一公司员工中已经存在的时候抛出这个异常
 * 
 * @author sunhngliang
 * @since 1.0.0 2010-09-19
 */
@ApplicationException(rollback = true)
public class StaffNumberDuplicateInCorporationException extends Exception {

	private static final long serialVersionUID = -2216650500760173023L;

	public StaffNumberDuplicateInCorporationException() {

	}

	public StaffNumberDuplicateInCorporationException(String message) {
		super(message);
	}
}
