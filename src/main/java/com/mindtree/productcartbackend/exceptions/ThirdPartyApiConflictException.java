/**
 * 
 */
package com.mindtree.productcartbackend.exceptions;

/**
 * @author M1030469
 *
 */
public class ThirdPartyApiConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6705534891671901697L;

	public ThirdPartyApiConflictException() {
		super();
	}

	public ThirdPartyApiConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ThirdPartyApiConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ThirdPartyApiConflictException(String message) {
		super(message);
	}

	public ThirdPartyApiConflictException(Throwable cause) {
		super(cause);
	}
}