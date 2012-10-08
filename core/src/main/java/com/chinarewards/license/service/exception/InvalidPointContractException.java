package com.chinarewards.license.service.exception;

/**
 * 当用户登陆时候去查看这个企业的积分合同时候~如果没有积分合同或者积分合同过期抛出改异常
 * 
 * @author roger
 * @since 0.1.0 2010-10-29
 */
public class InvalidPointContractException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5220053370358089972L;

	public InvalidPointContractException() {

	}

	public InvalidPointContractException(String message) {
		super(message);
	}
}
