package browser;

import board.Crossword;
import java.util.LinkedList;

/**
 *
 * @author wukat
 */
public class CwsAndReport {

    private LinkedList<Crossword> crosswords; // list of crosswords
    private final String message; // report

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
     *
     * @param message
     */
    public CwsAndReport(String message) {
        this.message = message;
    }

    /**
     * Constructor
     *
     * @param message
     * @param crosswords
     */
    public CwsAndReport(String message, LinkedList<Crossword> crosswords) {
        this.message = message;
        this.crosswords = crosswords;
    }

    /**
     * Getter
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
