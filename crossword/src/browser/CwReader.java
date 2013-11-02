/**
 * CwReader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import Exceptions.WrongDimensionInBoardAsked;
import board.Crossword;

/**
 * @author wukat
 * 
 */
public class CwReader implements Reader {
	private Vector<Crossword> crosswords; // vector of read crosswords
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
		if (!file.isDirectory() || !file.canRead())
			throw new IOException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see browser.Reader#getAllCws()
	 */
	@Override
	public void getAllCws() throws IOException, FileNotFoundException, WrongDimensionInBoardAsked {
		for (File f : file.listFiles()) {
			if (f.canRead()) {
				 crosswords.add(new Crossword(Integer.parseInt(f.getName()), f));
			}
		}
	}

}
