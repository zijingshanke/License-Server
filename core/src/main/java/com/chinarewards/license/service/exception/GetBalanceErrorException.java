package com.chinarewards.license.service.exception;

/**
 * 从CRM获取企业可用余额出错。
 * 
 * @author yanxin
 * @since 0.2.0 2011-01-18
 */
public class GetBalanceErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6200297816119064453L;

	public GetBalanceErrorException() {

	}

	public GetBalanceErrorException(Throwable e) {
		super(e);
	}

	public GetBalanceErrorException(String message) {
		super(message);
	}
}
