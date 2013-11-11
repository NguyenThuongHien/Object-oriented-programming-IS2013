/**
 * UnproperDataException.java
 * @author - wukat
 * @data - 11 lis 2013
 */
package lab5;

/**
 * @author wukat
 *
 */
public class UnproperDataException extends Exception {

	/**
	 * Constructor
	 */
	public UnproperDataException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param message
	 */
	public UnproperDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public UnproperDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public UnproperDataException(String message, Throwable cause) {
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
	public UnproperDataException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
