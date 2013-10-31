/**
 * CwReader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

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
		if (!file.isDirectory())
			throw new IOException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see browser.Reader#getAllCws()
	 */
	@Override
	public void getAllCws() {
		for (File f : file.listFiles()) {
			if (f.canRead()) {
				// TODO reading from file (i don't know the format
				// crosswords.add(new Crossword(Integer.parseInt(f.getName()),
				// ....)
			}
		}
	}

}
