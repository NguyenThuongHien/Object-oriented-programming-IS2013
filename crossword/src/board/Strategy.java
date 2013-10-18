/**
 * Strategy.java
 * @author - wukat
 * @data - 18 paź 2013
 */
package board;

import dictionary.CwEntry;

/**
 * @author wukat
 * 
 */
public abstract class Strategy {
	/**
	 * Finds entry that can be put into the crossword
	 * 
	 * @param cw
	 *            - crossword class
	 * @return crossword entry
	 */
	public abstract CwEntry findEntry(Crossword crossword);

	/**
	 * Updates board, inserts crossword entry to board
	 * 
	 * @param board
	 *            - board to update
	 * @param entry
	 *            - value to insert
	 */
	public abstract void updateBoard(Board board, CwEntry entry);
}
