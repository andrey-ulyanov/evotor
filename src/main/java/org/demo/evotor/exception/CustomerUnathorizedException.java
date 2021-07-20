package org.demo.evotor.exception;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public class CustomerUnathorizedException extends RuntimeException {

	private static final long serialVersionUID = 1644893019319223328L;

	public CustomerUnathorizedException() {
		super();
	}

	public CustomerUnathorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerUnathorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerUnathorizedException(String message) {
		super(message);
	}

	public CustomerUnathorizedException(Throwable cause) {
		super(cause);
	}

	
}
