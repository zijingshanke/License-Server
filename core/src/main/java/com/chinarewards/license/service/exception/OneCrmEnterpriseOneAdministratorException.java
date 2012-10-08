package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 违反策略一个大客户只有一个企业管理员帐号时抛出该异常
 * 
 * @author sunhongliang
 * @since 0.2.0 2011-02-12
 */
@ApplicationException(rollback = true)
public class OneCrmEnterpriseOneAdministratorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3399364458799238069L;

	public OneCrmEnterpriseOneAdministratorException() {
	}

	public OneCrmEnterpriseOneAdministratorException(String message) {
		super(message);
	}
}
