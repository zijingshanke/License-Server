package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 添加或者修改一个员工时候员工的员工编号在同一公司员工中已经存在的时候抛出这个异常
 * 
 * @author sunhngliang
 * @since 1.0.0 2010-09-19
 */
@ApplicationException(rollback = true)
public class MemberCardNoDuplicateInCorporationException extends Exception {

	private static final long serialVersionUID = -332279504501889150L;

	public MemberCardNoDuplicateInCorporationException() {

	}

	public MemberCardNoDuplicateInCorporationException(String message) {
		super(message);
	}
}
