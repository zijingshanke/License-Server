package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

/**
 * 企业没有足够的余额付这次奖励的金额
 * 
 * @author roger
 * 
 * @since 0.2.0 2011-02-15
 */
@ApplicationException(rollback = true)
public class CorporationEnoughBalanceRollBackException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8693142552213768340L;

	public CorporationEnoughBalanceRollBackException() {

	}

	public CorporationEnoughBalanceRollBackException(String message) {
		super(message);
	}
}
