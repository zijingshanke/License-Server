/**
 * 
 */
package com.chinarewards.license.service.exception;

/**
 * When user had existed in database.
 * 
 * @author Cream
 * @since 0.0.1 2010-09-17
 */
public class UserExistedException extends Exception {

	private static final long serialVersionUID = -1811814661341267086L;

	public UserExistedException() {
		super();
	}

	public UserExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistedException(String message) {
		super(message);
	}

	public UserExistedException(Throwable cause) {
		super(cause);
	}

}
