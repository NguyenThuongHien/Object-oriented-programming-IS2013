/**
 * EasyStrategy.java
 * @author - wukat
 * @data - 29 paź 2013
 */
package Strategies;

import board.Board;
import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;
import dictionary.Entry;

/**
 * @author wukat
 *
 */
public class EasyStrategy extends Strategy {
    
	/**
	 * Constructor
	 */
	public EasyStrategy() {
	}

	/* (non-Javadoc)
	 * @see board.Strategy#findEntry(board.Crossword)
	 */
	@Override
	public CwEntry findEntry(Crossword crossword) {
		Entry rand = null;
		if (!(crossword.getROEntryIter().hasNext()))
			//TODO
		    rand = crossword.getCwdb().getRandom(crossword.getBoardHeight());
		return null;
	}

	/* (non-Javadoc)
	 * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
	 */
	@Override
	public void updateBoard(Board board, CwEntry entry) {
		// TODO Auto-generated method stub

	}
}
