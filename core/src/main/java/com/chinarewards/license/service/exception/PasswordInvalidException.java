/**
 * 
 */
package com.chinarewards.license.service.exception;

/**
 * @author Cream
 * @since 0.0.1 2010-09-10
 */
public class PasswordInvalidException extends AccessDeniedException {

	private static final long serialVersionUID = -6895520185433215698L;

	public PasswordInvalidException() {
		super();
	}

	public PasswordInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordInvalidException(String message) {
		super(message);
	}

	public PasswordInvalidException(Throwable cause) {
		super(cause);
	}

}
