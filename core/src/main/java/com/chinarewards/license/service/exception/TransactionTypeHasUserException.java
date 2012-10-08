package com.chinarewards.license.service.exception;

/**
 * if TransactionType is has used ~user has delete operation then throw this
 * Exception
 * 
 * @author roger
 * @since 0.1.1 2010-10-29
 */
public class TransactionTypeHasUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6127105270940096587L;

	public TransactionTypeHasUserException() {

	}

	public TransactionTypeHasUserException(String message) {
		super(message);
	}

}
