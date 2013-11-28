package Exceptions;

import board.Crossword;
import java.util.LinkedList;

/**
 *
 * @author wukat
 */
public class FailedToLoadAllCwsException extends Exception {

    private LinkedList<Crossword> crosswords; // list of crosswords

    /**
     * Getter
     *
     * @return lsit of crosswords
     */
    public LinkedList<Crossword> getCrosswords() {
        return crosswords;
    }

    /**
     * Constructor
     */
    public FailedToLoadAllCwsException() {
    }

    /**
     * Constructor
     *
     * @param message
     */
    public FailedToLoadAllCwsException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message
     * @param crosswords
     */
    public FailedToLoadAllCwsException(String message, LinkedList<Crossword> crosswords) {
        super(message);
        this.crosswords = crosswords;
    }

    /**
     * Constructor
     *
     * @param cause
     */
    public FailedToLoadAllCwsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public FailedToLoadAllCwsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public FailedToLoadAllCwsException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}