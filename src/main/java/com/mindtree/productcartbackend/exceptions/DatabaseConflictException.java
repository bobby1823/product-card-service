/**
 * 
 */
package com.mindtree.productcartbackend.exceptions;

/**
 * @author M1030469
 *
 */
public class DatabaseConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7275124088958413189L;

	public DatabaseConflictException() {
		super();
	}

	public DatabaseConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatabaseConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseConflictException(String message) {
		super(message);
	}

	public DatabaseConflictException(Throwable cause) {
		super(cause);
	}
}