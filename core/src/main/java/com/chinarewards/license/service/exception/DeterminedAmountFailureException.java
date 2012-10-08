package com.chinarewards.license.service.exception;

import java.util.Map;

import com.chinarewards.license.model.common.Amount;

/**
 * 通过该规则，无法确定该员工的奖金。
 * 
 * @author yanxin
 * @since 0.2.0 2011-01-17
 */
public class DeterminedAmountFailureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1386118563445739500L;

	/**
	 * String : staffId; Double : rewardsAmount 某员工出错了，但要返回其它员工的信息。
	 */
	Map<String, Double> amountMap;

	Amount amount;

	public DeterminedAmountFailureException() {

	}

	public DeterminedAmountFailureException(Amount amount) {
		this.amount = amount;
	}

	public DeterminedAmountFailureException(Map<String, Double> amountMap) {
		this.amountMap = amountMap;
	}

	public Amount getAmount() {
		return amount;
	}

	public DeterminedAmountFailureException(String message) {
		super(message);
	}

	public Map<String, Double> getData() {
		return amountMap;
	}
}
