/**
 * CwBrower.java
 * @author - wukat
 * @data - 2 lis 2013
 */
package browser;

import Exceptions.FailedToGenerateCrosswordException;
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

    public void setDefaultCwDB(InteliCwDB defaultCwDB) {
        this.defaultCwDB = defaultCwDB;
    }

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

	public void next(boolean lastUsedNext) {
        if (lastUsedNext)
        {    if (iter.hasNext())
			    actual = iter.next();}
        else {
            actual = iter.next();
            actual = iter.next();
        }
	}

    public boolean hasNext() {
        return iter.hasNext();
    }

	public void previous(boolean lastUsedNext) {
        if (lastUsedNext)
        {

                actual = iter.previous();
                actual = iter.previous();

        }
        else
		if (iter.hasPrevious())
			actual = iter.previous();
	}

    public boolean hasPrevious() {
        return iter.hasPrevious();
    }

    public boolean hasActual() {
        return actual != null;
    }

    public int getIndexOfIterator() {
        return iter.nextIndex();
    }

    public int nextIndex() {
        return iter.nextIndex();
    }

    public int previousIndex() {
        return iter.previousIndex();
    }

    public int getAmountOfCrosswords() {
        return crosswords.size();
    }

	public void saveActual(String folderPath) throws IOException, NullPointerException {
        if (actual != null)
		    new CwWriter(folderPath).write(actual);
        else
            throw new NullPointerException("No actual crossword");
	}

	public void loadFromFiles(String folderPath) throws IOException {
		CwReader reader = new CwReader(folderPath);
		crosswords.addAll(reader.getAllCws());
        iter = crosswords.listIterator();
        while (iter.hasNext())
            actual = iter.next();
	}
}
