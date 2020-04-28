package com.mindtree.productcartbackend.exceptions;

/**
 * @author M1030469
 *
 */
public class MicroservicesCoordinationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7518163077294964256L;

	public MicroservicesCoordinationException() {
		super();
	}

	public MicroservicesCoordinationException(String message) {
		super(message);
	}

	public MicroservicesCoordinationException(Throwable cause) {
		super(cause);
	}

	public MicroservicesCoordinationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MicroservicesCoordinationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}