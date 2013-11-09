/**
 * CwBrower.java
 * @author - wukat
 * @data - 2 lis 2013
 */
package browser;

import Exceptions.FailedToGenerateCrosswordException;
import board.Crossword;
import board.Strategy;
import dictionary.IntelLiCwDB;

import java.io.IOException;
import java.util.ListIterator;
import java.util.Vector;

/**
 * @author wukat
 * 
 */
public class CwBrowser {
	private Vector<Crossword> crosswords; // vector of read crosswords
	private ListIterator<Crossword> iter; // vector iterator
	private Crossword actual; // actual crossword
	private IntelLiCwDB defaultCwDB;

	/**
	 * Setter
	 * 
	 * @param defaultCwDB
	 */
	public void setDefaultCwDB(IntelLiCwDB defaultCwDB) {
		this.defaultCwDB = defaultCwDB;
	}

	/**
	 * getter
	 * 
	 * @return actual crossword
	 */
	public Crossword getActual() {
		return actual;
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param cwDBpath
	 *            - path to database
	 * @throws IOException
	 */
	public CwBrowser(String cwDBpath) throws IOException {
		if (cwDBpath == null)
			cwDBpath = "cwdb.txt";
		defaultCwDB = new IntelLiCwDB(cwDBpath);
		crosswords = new Vector<Crossword>();
		iter = crosswords.listIterator();
		actual = null;
	}

	/**
	 * Generates crossword (actual)
	 * 
	 * @param width
	 *            - board's width
	 * @param height
	 *            - board's height
	 * @param str
	 *            - strategy
	 * @throws FailedToGenerateCrosswordException
	 */
	public void generateCw(int width, int height, Strategy str)
			throws FailedToGenerateCrosswordException {
		actual = new Crossword(width, height, defaultCwDB);
		actual.generate(str);
		crosswords.add(actual);
		iter = crosswords.listIterator();
		while (iter.hasNext())
			iter.next();
	}

	/**
	 * Next crossword
	 * 
	 * @param lastUsedNext
	 *            - if before was used NEXTBUTTON
	 */
	public void next(boolean lastUsedNext) {
		if (lastUsedNext) {
			if (iter.hasNext())
				actual = iter.next();
		} else {
			actual = iter.next();
			actual = iter.next();
		}
	}

	/**
	 * Iter.hasNext()
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return iter.hasNext();
	}

	/**
	 * Previous crossword
	 * 
	 * @param lastUsedNext
	 *            - if before was used NEXTBUTTON
	 */
	public void previous(boolean lastUsedNext) {
		if (lastUsedNext) {
			actual = iter.previous();
			actual = iter.previous();
		} else if (iter.hasPrevious())
			actual = iter.previous();
	}

	/**
	 * iter.hasPrevious()
	 * @return
	 */
	public boolean hasPrevious() {
		return iter.hasPrevious();
	}

	/**
	 * Checks if browser has actual crossword
	 * return logical value
	 */
	public boolean hasActual() {
		return actual != null;
	}

	/**
	 * Index
	 * @return
	 */
	public int getIndexOfIterator() {
		return iter.nextIndex();
	}

	/**
	 * Next index iterator
	 * @return
	 */
	public int nextIndex() {
		return iter.nextIndex();
	}

	/**
	 * Previous element index
	 * @return
	 */
	public int previousIndex() {
		return iter.previousIndex();
	}

	/**
	 * Amount of crossword
	 * @return
	 */
	public int getAmountOfCrosswords() {
		return crosswords.size();
	}

	/**
	 * Saves actual crossword in file
	 * @param folderPath
	 * @throws IOException
	 */
	public void saveActual(String folderPath) throws IOException,
			NullPointerException {
		if (actual != null)
			new CwWriter(folderPath).write(actual);
	}

	public void loadFromFiles(String folderPath) throws IOException {
		CwReader reader = new CwReader(folderPath);
		crosswords.addAll(reader.getAllCws());
		iter = crosswords.listIterator();
		while (iter.hasNext())
			actual = iter.next();
	}
}
