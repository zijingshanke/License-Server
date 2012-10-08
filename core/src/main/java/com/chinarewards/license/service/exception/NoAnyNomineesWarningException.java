package com.chinarewards.license.service.exception;

/**
 * 找不到任何奖励人员
 * 
 * @author yanxin
 * @since 0.2.0 2011-01-30
 */
public class NoAnyNomineesWarningException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779949198194923282L;

	public NoAnyNomineesWarningException() {

	}

	public NoAnyNomineesWarningException(String message) {
		super(message);
	}
}
