package com.chinarewards.license.service.exception;

/**
 * 企业没有足够的余额付这次奖励的金额
 * 
 * @author roger
 * 
 * @since 0.2.0 2011-02-15
 */
public class CorporationEnoughBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985401372049553341L;

	public CorporationEnoughBalanceException() {

	}

	public CorporationEnoughBalanceException(String message) {
		super(message);
	}
}
