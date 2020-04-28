/**
 * 
 */
package com.mindtree.productcartbackend.exceptions;

/**
 * @author M1030469
 *
 */
public class ThirdPartyNoAuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6505222932726154724L;

	public ThirdPartyNoAuthException() {
	}

	public ThirdPartyNoAuthException(String message) {
		super(message);
	}

	public ThirdPartyNoAuthException(Throwable cause) {
		super(cause);
	}

	public ThirdPartyNoAuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public ThirdPartyNoAuthException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}