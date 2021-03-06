package org.demo.evotor.exception;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public class CustomerAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1644893019319223328L;

	public CustomerAlreadyExistException() {
		super();
	}

	public CustomerAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerAlreadyExistException(String message) {
		super(message);
	}

	public CustomerAlreadyExistException(Throwable cause) {
		super(cause);
	}

	
}
