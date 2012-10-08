package com.chinarewards.license.service.exception;

/**
 * 从CRM获取单笔交易最大值错误
 * 
 * @author yanxin
 * @since 0.2.0 2011-02-16
 */
public class GetMaxConsumeErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -567426494130502692L;

	public GetMaxConsumeErrorException() {

	}

	public GetMaxConsumeErrorException(Throwable e) {
		super(e);
	}

	public GetMaxConsumeErrorException(String message) {
		super(message);
	}
}
