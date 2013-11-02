/**
 * CwBrower.java
 * @author - wukat
 * @data - 2 lis 2013
 */
package browser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Vector;

import Exceptions.FailedToGenerateCrosswordException;
import Exceptions.WrongDimensionInBoardAsked;
import board.Crossword;
import board.Strategy;
import dictionary.InteliCwDB;

/**
 * @author wukat
 * 
 */
public class CwBrowser {
	private Vector<Crossword> crosswords; // vector of read crosswords
	private ListIterator<Crossword> iter; // vector iterator
	private Crossword actual; // actual crossword
	private InteliCwDB defaultCwDB;

	/**
	 * 
	 * Constructor
	 * 
	 * @param cwDBpath
	 *            - path to database
	 * @throws IOException
	 */
	public CwBrowser(String cwDBpath) throws IOException {
		defaultCwDB = new InteliCwDB(cwDBpath);
		crosswords = new Vector<Crossword>();
		iter = crosswords.listIterator();
		actual = null;
	}

	// generowanie
	/**
	 * Generates crossword (actual)
	 * 
	 * @param width
	 *            - board's width
	 * @param height
	 *            - board's height
	 * @param str
	 *            - strategy
	 * @throws WrongDimensionInBoardAsked
	 * @throws FailedToGenerateCrosswordException
	 */
	public void generateCw(int width, int height, Strategy str)
			throws WrongDimensionInBoardAsked,
			FailedToGenerateCrosswordException {
		Crossword actual = new Crossword(width, height, defaultCwDB);
		actual.generate(str);
		crosswords.add(actual);
		while (iter.hasNext())
			iter.next();
	}

	// przegladanie
	public void next() {
		if (iter.hasNext())
			actual = iter.next();
		// show();
	}

	public void previous() {
		if (iter.hasPrevious())
			actual = iter.previous();
		// show();
	}

	// zapisywanie
	public void saveActual(String folderPath) throws IOException {
		new CwWriter(folderPath).write(actual);
	}

	// wczytywanie
	public void loadFromFiles(String folderPath) throws IOException,
			WrongDimensionInBoardAsked, FileNotFoundException {
		CwReader reader = new CwReader(folderPath);
		reader.getAllCws();
		crosswords.addAll(reader.getAllCws());
	}
}
