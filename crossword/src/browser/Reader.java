/**
 * Reader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.FileNotFoundException;
import java.io.IOException;

import Exceptions.WrongDimensionInBoardAsked;

/**
 * @author wukat
 * 
 */
interface Reader {
	/**
	 * Loads all crosswords into one data base.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws WrongDimensionInBoardAsked
	 */
	public void getAllCws() throws IOException, FileNotFoundException, WrongDimensionInBoardAsked;
}
