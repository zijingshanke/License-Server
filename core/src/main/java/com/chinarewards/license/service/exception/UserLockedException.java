/**
 * 
 */
package com.chinarewards.license.service.exception;

/**
 * When the status equals to {@code UserStatus#Logoff} this exception will be
 * throw at login check.
 * 
 * @author Cream
 * @since 0.0.1 2010-10-08
 */
public class UserLockedException extends AccessDeniedException {

	private static final long serialVersionUID = -3541639017197859469L;

	public UserLockedException() {
		super();
	}

	public UserLockedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserLockedException(String message) {
		super(message);
	}

	public UserLockedException(Throwable cause) {
		super(cause);
	}

}
