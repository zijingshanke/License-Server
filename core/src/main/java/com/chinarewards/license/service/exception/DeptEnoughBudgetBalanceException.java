package com.chinarewards.license.service.exception;

/**
 * 部门是否有预算
 * 
 * @author yanxin
 * 
 * @since 0.2.0 2011-02-17
 */
public class DeptEnoughBudgetBalanceException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8267973003029247841L;

	public DeptEnoughBudgetBalanceException() {

	}

	public DeptEnoughBudgetBalanceException(String message) {
		super(message);
	}
}
