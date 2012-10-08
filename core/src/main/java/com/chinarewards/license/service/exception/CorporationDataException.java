package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 当Corporation表中发现一个CRMEnterpriseID在Corporation存在多条记录时候抛出该异常
 * 
 * @author roger
 * @since 0.2.0 2011-02-12
 */
@ApplicationException(rollback = true)
public class CorporationDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1091073461528333313L;

	public CorporationDataException() {
	}

	public CorporationDataException(String message) {
		super(message);
	}
}
