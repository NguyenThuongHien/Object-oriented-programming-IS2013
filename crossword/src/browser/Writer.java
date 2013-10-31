/**
 * Writer.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

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
	 */
	public void write(Crossword crossword);

	/** 
	 * Gets unique ID for saving crossword, time in milliseconds
	 * @return unique ID
	 */
	public long getUniqueID();
}

// zapisujemy krzyżówkę jako ściezkę do bazy danych, listę CwEntry i wymiary; ID
// to nazwa pliku
