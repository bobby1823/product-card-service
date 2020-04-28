package com.mindtree.productcartbackend.exceptions;

/**
 * 
 * @author M1034075
 *
 */
public class ProductCartBackendApplicationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductCartBackendApplicationException() {
		super();
	}

	public ProductCartBackendApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductCartBackendApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductCartBackendApplicationException(String message) {
		super(message);
	}

	public ProductCartBackendApplicationException(Throwable cause) {
		super(cause);
	}

}
