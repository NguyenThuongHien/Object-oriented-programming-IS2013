/**
 * Crossword.java
 * @author - wukat
 * @data - 18 paź 2013
 */
package board;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import dictionary.CwEntry;
import dictionary.InteliCwDB;

/**
 * @author wukat
 * 
 */
public class Crossword {
	private LinkedList<CwEntry> entries; // list of entries in crossword
	private Board board; // crossword's board
	private InteliCwDB cwdb; // crossword's intelligent database
	private final long ID = -1; // ID, default setted to -1

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
	 * Getter (copy)
	 * 
	 * @return copy of board
	 */
	public Board getBoardCopy() {
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
		CwEntry temp = null;
		for (java.util.ListIterator<CwEntry> iter = getEntries().listIterator(); iter
				.hasNext(); temp = iter.next())
			if (temp.getWord().equals(word))
				return true;
		return false;
	}

	/**
	 * Function adds crossword entry to list of entries and updates board
	 * 
	 * @param cwe
	 *            - entry
	 * @param strategy
	 */
	public final void addCwEntry(CwEntry cwe, Strategy strategy) {
		entries.add(cwe);
		strategy.updateBoard(getBoard(), cwe);
	}

	/**
	 * Function generating crossword
	 * 
	 * @param strategy
	 */
	public final void generate(Strategy strategy) {
		CwEntry entry = null;
		while ((entry = strategy.findEntry(this)) != null) {
			addCwEntry(entry, strategy);
		}
	}
}
