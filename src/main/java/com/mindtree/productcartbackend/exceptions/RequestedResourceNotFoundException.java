/**
 * 
 */
package com.mindtree.productcartbackend.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author M1030469
 *
 */
@ToString(callSuper = true)
public class RequestedResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8745754924061737878L;

	@Getter
	@Setter(value = AccessLevel.NONE)
	private String resourceName;

	@Getter
	@Setter(value = AccessLevel.NONE)
	private String fieldName;

	@Getter
	@Setter(value = AccessLevel.NONE)
	private Object fieldValue;
    
	public RequestedResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
//	public RequestedResourceNotFoundException() {
//		super();
//	}
//	
//	
//	public RequestedResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
//			boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
//
//	public RequestedResourceNotFoundException(String message, Throwable cause) {
//		super(message, cause);
//	}
//
	public RequestedResourceNotFoundException(String message) {
		super(message);
	}
//	public RequestedResourceNotFoundException(Throwable cause) {
//		super(cause);
//	}
}