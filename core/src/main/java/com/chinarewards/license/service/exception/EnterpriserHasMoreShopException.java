package com.chinarewards.license.service.exception;

/**
 * 当一个企业在CRM有多个门市时候抛出这个异常
 * 
 * @author roger
 * @since 1.0.0 2010-10-20
 */
public class EnterpriserHasMoreShopException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4260237398822829264L;

	public EnterpriserHasMoreShopException() {

	}

	public EnterpriserHasMoreShopException(String message) {
		super(message);
	}
}
