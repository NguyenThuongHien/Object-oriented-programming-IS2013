package Exceptions;

/**
 * @author wukat
 * Exception class.
 */
public class FailedToGenerateCrosswordException extends Exception {

	final static long serialVersionUID = -1;
	
	/**
	 * Constructor
	 */
	public FailedToGenerateCrosswordException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param message
	 */
	public FailedToGenerateCrosswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public FailedToGenerateCrosswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public FailedToGenerateCrosswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FailedToGenerateCrosswordException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
