/**
 * Reader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import board.Crossword;

import java.io.IOException;
import java.util.Vector;

/**
 * @author wukat
 * 
 */
interface Reader {
	/**
	 * Loads all crosswords into one data base.
	 * @return 
	 * @throws IOException
	 */
	public Vector<Crossword> getAllCws() throws IOException;
}
