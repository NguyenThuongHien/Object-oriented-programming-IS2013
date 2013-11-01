/**
 * Crossword.java
 * @author - wukat
 * @data - 18 paź 2013
 */
package board;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import Exceptions.FailedToGenerateCrosswordException;
import Exceptions.WrongDimensionInBoardAsked;
import dictionary.CwEntry;
import dictionary.InteliCwDB;

/**
 * @author wukat
 * 
 */
public class Crossword {
	private LinkedList<CwEntry> entries = new LinkedList<CwEntry>(); // list of
																		// entries
																		// in
																		// crossword
	private Board board; // crossword's board
	private InteliCwDB cwdb; // crossword's intelligent database
	private final long ID = -1; // ID, default set to -1

	/**
	 * 
	 * Constructor
	 * 
	 * @param width
	 *            - width of board
	 * @param height
	 *            - height of board
	 * @param cwDB
	 *            - data base
	 * @throws WrongDimensionInBoardAsked 
	 */
	public Crossword(int width, int height, InteliCwDB cwDB) throws WrongDimensionInBoardAsked {
		board = new Board(width, height);
		cwdb = cwDB;
	}

	/**
	 * Getter
	 * 
	 * @return the entries
	 */
	private LinkedList<CwEntry> getEntries() {
		return entries;
	}

	/**
	 * entries setter
	 * 
	 * @param entries
	 *            the entries to set
	 */
	private void setEntries(LinkedList<CwEntry> entries) {
		this.entries = entries;
	}

	/**
	 * Getter
	 * 
	 * @return the board
	 */
	private Board getBoard() {
		return board;
	}

	/**
	 * board setter
	 * 
	 * @param board
	 *            the board to set
	 */
	private void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Special getter
	 * 
	 * @return board's height
	 */
	public int getBoardHeight() {
		return board.getHeight();
	}

	/**
	 * Special getter
	 * 
	 * @return board's width
	 */
	public int getBoardWidth() {
		return board.getWidth();
	}

	/**
	 * Getter
	 * 
	 * @return the cwdb
	 */
	public InteliCwDB getCwdb() {
		return cwdb;
	}

	/**
	 * cwdb setter
	 * 
	 * @param cwdb
	 *            the cwdb to set
	 */
	public void setCwdb(InteliCwDB cwdb) {
		this.cwdb = cwdb;
	}

	/**
	 * Getter
	 * 
	 * @return the iD
	 */
	private long getID() {
		return ID;
	}

	/**
	 * Getter
	 * 
	 * @return read-only iterator
	 */
	public Iterator<CwEntry> getROEntryIter() {
		return Collections.unmodifiableList(getEntries()).iterator();
	}

	/**
	 * Checks if list of entries is empty
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	/**
	 * Getter (copy)
	 * 
	 * @return copy of board
	 * @throws WrongDimensionInBoardAsked 
	 */
	public Board getBoardCopy() throws WrongDimensionInBoardAsked {
		return board.copy();
	}

	/**
	 * Function checks if crossword contains given word
	 * 
	 * @param word
	 *            - word to find
	 * @return true if contains
	 */
	public boolean contains(String word) {
		java.util.ListIterator<CwEntry> iter = getEntries().listIterator();
		while (iter.hasNext()) {
			CwEntry temp = iter.next();
			if (temp.getWord().equals(word))
				return true;
		}
		return false;
	}

	/**
	 * Function adds crossword entry to list of entries and updates board
	 * 
	 * @param cwe
	 *            - entry
	 * @param strategy
	 * @throws WrongDimensionInBoardAsked 
	 */
	public final void addCwEntry(CwEntry cwe, Strategy strategy) throws WrongDimensionInBoardAsked {
		entries.add(cwe);
		strategy.updateBoard(getBoard(), cwe);
	}

	/**
	 * Function generating crossword
	 * 
	 * @param strategy
	 * @throws FailedToGenerateCrosswordException, WrongDimensionInBoardAsked 
	 */
	public final void generate(Strategy strategy) throws FailedToGenerateCrosswordException, FailedToGenerateCrosswordException, WrongDimensionInBoardAsked {
		CwEntry entry = null;
		while ((entry = strategy.findEntry(this)) != null) {
			addCwEntry(entry, strategy);
		}
	}
}
