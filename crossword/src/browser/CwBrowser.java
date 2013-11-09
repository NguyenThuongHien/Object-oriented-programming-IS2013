/**
 * CwBrower.java
 * @author - wukat
 * @data - 2 lis 2013
 */
package browser;

import Exceptions.FailedToGenerateCrosswordException;
import Exceptions.WrongDimensionInBoardAsked;
import board.Crossword;
import board.Strategy;
import dictionary.InteliCwDB;

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
        if (cwDBpath == null)
            cwDBpath = "cwdb.txt";
		defaultCwDB = new InteliCwDB(cwDBpath);
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
	 * @throws WrongDimensionInBoardAsked
	 * @throws FailedToGenerateCrosswordException
	 */
	public void generateCw(int width, int height, Strategy str)
			throws WrongDimensionInBoardAsked,
			FailedToGenerateCrosswordException {
        actual = new Crossword(width, height, defaultCwDB);
        actual.generate(str);
        crosswords.add(actual);
        iter = crosswords.listIterator();
        while (iter.hasNext())
            actual = iter.next();
	}

	public void next() {
		if (iter.hasNext())
			actual = iter.next();
		// show();
	}

    public boolean hasNext() {
        return iter.hasNext();
    }

	public void previous() {
		if (iter.hasPrevious())
			actual = iter.previous();
		// show();
	}

    public boolean hasPrevious() {
        return iter.hasPrevious();
    }

    public boolean hasActual() {
        return actual != null;
    }

    public boolean hasOnlyActualOrNone() {
        return crosswords.size() <= 1;
    }

	public void saveActual(String folderPath) throws IOException, NullPointerException {
        if (actual != null)
		    new CwWriter(folderPath).write(actual);
        else
            throw new NullPointerException("No actual crossword");
	}

	public void loadFromFiles(String folderPath) throws IOException,
			WrongDimensionInBoardAsked {
		CwReader reader = new CwReader(folderPath);
		crosswords.addAll(reader.getAllCws());
        iter = crosswords.listIterator();
	}
}
