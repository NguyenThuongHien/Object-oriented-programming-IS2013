/**
 * Crossword.java
 * @author - wukat
 * @data - 18 paź 2013
 */
package board;

import Exceptions.FailedToGenerateCrosswordException;
import dictionary.CwEntry;
import dictionary.IntelLiCwDB;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
public class Crossword {
	private LinkedList<CwEntry> entries; // list of
											// entries
											// in
											// crossword
	private Board board; // crossword's board
	private IntelLiCwDB cwdb = null; // crossword's intelligent database
	private final long ID; // ID, default set to -1

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
	 */
	public Crossword(int width, int height, IntelLiCwDB cwDB) {
		entries = new LinkedList<CwEntry>();
		board = new Board(width, height);
		cwdb = cwDB;
		this.ID = -1;
	}

	/**
	 * 
	 * Constructor - crossword from file, format: width, height \n, filename of
	 * cwDB \n, CwEntries;
	 * 
	 * @param ID
	 * @param f
	 * @throws IOException
	 */
	public Crossword(long ID, File f) throws IOException {
		entries = new LinkedList<CwEntry>();
		this.ID = ID;
		BufferedReader reader = new BufferedReader(new FileReader(f));
		try {
			String temp = reader.readLine();
			String[] splited = temp.split(" ");
			board = new Board(Integer.parseInt(splited[0]),
					Integer.parseInt(splited[1]));
			while ((temp = reader.readLine()) != null) {
				splited = temp.split(" ");
				if (splited[2].equals("HORIZ"))
					entries.add(new CwEntry(reader.readLine(), reader
							.readLine(), Integer.parseInt(splited[0]), Integer
							.parseInt(splited[1]),
							dictionary.CwEntry.Direction.HORIZ));
				else if (splited[2].equals("VERT"))
					entries.add(new CwEntry(reader.readLine(), reader
							.readLine(), Integer.parseInt(splited[0]), Integer
							.parseInt(splited[1]),
							dictionary.CwEntry.Direction.VERT));
			}
		} finally {
			reader.close();
		}

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
	public IntelLiCwDB getCwdb() {
		return cwdb;
	}

	/**
	 * cwdb setter
	 * 
	 * @param cwdb
	 *            the cwdb to set
	 */
	public void setCwdb(IntelLiCwDB cwdb) {
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
	 */
	public final void addCwEntry(CwEntry cwe, Strategy strategy) {
		entries.add(cwe);
		strategy.updateBoard(getBoard(), cwe);
	}

	/**
	 * Function generating crossword
	 * 
	 * @param strategy
	 * @throws FailedToGenerateCrosswordException
	 */
	public final void generate(Strategy strategy)
			throws FailedToGenerateCrosswordException {
		CwEntry entry = null;
		while ((entry = strategy.findEntry(this)) != null) {
			addCwEntry(entry, strategy);
		}
	}
}
