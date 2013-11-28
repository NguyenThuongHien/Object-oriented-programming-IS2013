/**
 * Reader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import Exceptions.FailedToLoadAllCwsException;
import board.Crossword;

import board.Strategy;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
interface Reader {

    /**
     * Loads all crosswords into one data base.
     * 
     * @param easyStrategy
     * @param hardStrategy 
     * @throws IOException
     * @throws FailedToLoadAllCwsException - in this exception function returns list of loaded crossword
     */
    public void getAllCws(Strategy easyStrategy, Strategy hardStrategy) throws IOException, FailedToLoadAllCwsException;
}
