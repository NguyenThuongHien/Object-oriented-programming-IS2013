/**
 * Writer.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.IOException;

import board.Crossword;

/**
 * @author wukat
 * 
 */
public interface Writer {
	/**
	 * Saves crossword in file
	 * 
	 * @param crossword
	 * @throws IOException 
	 */
	public void write(Crossword crossword) throws IOException;

	/** 
	 * Gets unique ID for saving crossword, time in milliseconds
	 * @return unique ID
	 */
	public long getUniqueID();
}