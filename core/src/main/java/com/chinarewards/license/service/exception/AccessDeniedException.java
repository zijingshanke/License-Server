/**
 * 
 */
package com.chinarewards.license.service.exception;

/**
 * @author Cream
 *
 */
public class AccessDeniedException extends Exception {

	private static final long serialVersionUID = -6276928321625810010L;

	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
	}

	
}
