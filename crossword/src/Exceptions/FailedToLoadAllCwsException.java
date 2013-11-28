/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import board.Crossword;
import java.util.LinkedList;

/**
 *
 * @author wukat
 */
public class FailedToLoadAllCwsException extends Exception {

    final static long serialVersionUID = -1;
    private LinkedList<Crossword> crosswords;
    

    public LinkedList<Crossword> getCrosswords() {
        return crosswords;
    }

    /**
     * Constructor
     */
    public FailedToLoadAllCwsException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     *
     * @param message
     */
    public FailedToLoadAllCwsException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
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
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public FailedToLoadAllCwsException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
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
        // TODO Auto-generated constructor stub
    }
}
