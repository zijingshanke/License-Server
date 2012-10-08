/**
 * 
 */
package com.chinarewards.license.model.common;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public class SimpleAmount implements Amount {

	private BigDecimal amount;

	private String currency;

	public SimpleAmount() {

	}

	public SimpleAmount(String currency, BigDecimal amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public SimpleAmount(String currency, double amount) {
		this.currency = currency;
		this.amount = new BigDecimal(amount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chinarewards.hr.model.budget.Amount#getAmount()
	 */
	public BigDecimal getAmount() {
		return this.amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chinarewards.hr.model.budget.Amount#getCurrency()
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
