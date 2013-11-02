/**
 * Reader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import board.Crossword;
import Exceptions.WrongDimensionInBoardAsked;

/**
 * @author wukat
 * 
 */
interface Reader {
	/**
	 * Loads all crosswords into one data base.
	 * @return 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws WrongDimensionInBoardAsked
	 */
	public Vector<Crossword> getAllCws() throws IOException, FileNotFoundException, WrongDimensionInBoardAsked;
}
