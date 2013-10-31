/**
 * CwWriter.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import board.Crossword;

/**
 * @author wukat
 * 
 */
public class CwWriter implements Writer {
	private File file; // folder

	/**
	 * 
	 * Constructor
	 * 
	 * @param path
	 *            - path to destination folder
	 */
	public CwWriter(String path) throws IOException {
		file = new File(path);
		if (!file.isDirectory())
			throw new IOException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see browser.Writer#write(board.Crossword)
	 */
	@Override
	public void write(Crossword crossword) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see browser.Writer#getUniqueID()
	 */
	@Override
	public long getUniqueID() {
		return new Date().getTime();
	}

}
