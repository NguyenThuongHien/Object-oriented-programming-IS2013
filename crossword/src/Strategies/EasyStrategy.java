/**
 * EasyStrategy.java
 * @author - wukat
 * @data - 29 paź 2013
 */
package Strategies;

import java.util.regex.Pattern;

import board.Board;
import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;

/**
 * @author wukat
 *
 */
public class EasyStrategy extends Strategy {
    private String actualPattern;
    
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
		//TODO is doesn't work
		CwEntry rand = null;
		int i = 0;
		if (crossword.isEmpty())
		    rand = new CwEntry(crossword.getCwdb().getRandom(crossword.getBoardHeight()), 0, 0, CwEntry.Direction.VERT);
		else {
			rand = new CwEntry(crossword.getCwdb().getRandom(actualPattern), 0, i, CwEntry.Direction.HORIZ);
			i++;
		}
		return rand;
	}

	/* (non-Javadoc)
	 * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
	 */
	@Override
	public void updateBoard(Board board, CwEntry entry) {
		// TODO Auto-generated method stub

	}
}
