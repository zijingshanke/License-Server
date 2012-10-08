package com.chinarewards.license.service.exception;


/**
 * 当使用员工数据时候发现员工的数据错误时候抛出这个异常
 * 
 * 
 * @author roger
 * @since 1.0.0 2010-09-23
 */
public class StaffTableHasDataErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1425959977825624891L;
	private String transactionId;

	public StaffTableHasDataErrorException() {

	}

	public StaffTableHasDataErrorException(String message) {
		super(message);
	}

	public StaffTableHasDataErrorException(String message, String transactionId) {
		super(message);
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}
}
