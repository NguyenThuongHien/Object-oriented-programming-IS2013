/**
 * Reader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;


import Strategies.Strategy;
import java.io.IOException;


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
     */
    public CwsAndReport getAllCws(Strategy easyStrategy, Strategy hardStrategy) throws IOException;
}
