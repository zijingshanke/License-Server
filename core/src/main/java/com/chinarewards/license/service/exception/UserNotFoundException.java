/**
 * 
 */
package com.chinarewards.license.service.exception;

/**
 * @author Cream
 * @since 0.0.1 2010-09-09
 */
public class UserNotFoundException extends AccessDeniedException {

	private static final long serialVersionUID = 7431187981335514820L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

}
