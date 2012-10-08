package com.chinarewards.license.service.exception;

/**
 * 当HR系统给员工 充值时候发生错误时候抛出该异常
 * 
 * @author roger
 * @since 1.0.0 2010-09-14
 */
public class TransactionHasHappenException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6251981161698393220L;

	String businessId;

	public TransactionHasHappenException() {

	}

	public TransactionHasHappenException(String message) {
		super(message);
	}

	public TransactionHasHappenException(String bussinessId, String message) {
		super(message);
		this.businessId = bussinessId;
	}

	public String getTransactionBussinessLogId() {
		return this.businessId;
	}
}
