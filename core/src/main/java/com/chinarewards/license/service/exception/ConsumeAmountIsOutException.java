package com.chinarewards.license.service.exception;


/**
 * 给员工的充值金额已经大于了CRM的定义的消费类型的最大金额时候抛出这个异常
 * 
 * @author roger
 * @since 1.0.0 2010-10-20
 * 
 */
public class ConsumeAmountIsOutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8117577295774784393L;

	private String transactionId;

	public ConsumeAmountIsOutException() {

	}

	public ConsumeAmountIsOutException(String message) {
		super(message);
	}

	public ConsumeAmountIsOutException(String message, String transactionId) {
		super(message);
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}
}
