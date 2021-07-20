package org.demo.evotor.exception;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public class CustomerNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1644893019319223328L;

	public CustomerNotExistException() {
		super();
	}

	public CustomerNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerNotExistException(String message) {
		super(message);
	}

	public CustomerNotExistException(Throwable cause) {
		super(cause);
	}

	
}
