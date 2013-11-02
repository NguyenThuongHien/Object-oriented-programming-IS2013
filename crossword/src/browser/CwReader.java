/**
 * CwReader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import dictionary.CwEntry;
import Exceptions.WrongDimensionInBoardAsked;
import board.Crossword;

/**
 * @author wukat
 * 
 */
public class CwReader implements Reader {
	private File file; // folder

	/**
	 * 
	 * Constructor
	 * 
	 * @param path
	 *            - folder with crosswords
	 */
	public CwReader(String path) throws IOException {
		file = new File(path);
		if (!file.isDirectory() || !file.canRead() || !file.canExecute())
			throw new IOException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see browser.Reader#getAllCws()
	 */
	@Override
	public Vector<Crossword> getAllCws() throws IOException, FileNotFoundException, WrongDimensionInBoardAsked {
		Vector<Crossword> crosswords = new Vector<Crossword>();
		for (File f : file.listFiles()) {
			if (f.canRead() && f.isFile()) {
				 crosswords.add(new Crossword(Long.parseLong(f.getName()), f));
			}
		}
		return crosswords;
	}
}
