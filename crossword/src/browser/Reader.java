/**
 * Reader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

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
     * @return vector of loaded crosswords
     * @throws IOException
     * @throws NullPointerException
     */
    public LinkedList<Crossword> getAllCws(Strategy easyStrategy, Strategy hardStrategy) throws IOException, NullPointerException;
}
