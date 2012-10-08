package com.chinarewards.license.service.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NomineeStatusErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9132260943394516680L;

	public NomineeStatusErrorException(String message) {
		super(message);
	}

	public NomineeStatusErrorException() {

	}

}
