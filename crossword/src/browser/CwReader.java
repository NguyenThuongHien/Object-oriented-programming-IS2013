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
		crosswords = new Vector<Crossword>();
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
	public void getAllCws() throws IOException, FileNotFoundException, WrongDimensionInBoardAsked {
		for (File f : file.listFiles()) {
			if (f.canRead() && f.isFile()) {
				 crosswords.add(new Crossword(Long.parseLong(f.getName()), f));
			}
		}
	}
	
	public void showCws() {
		for (Crossword a : crosswords) {
			Iterator<CwEntry> iter = a.getROEntryIter();
			while (iter.hasNext()) {
				CwEntry temp = iter.next();
				System.out.println(temp);
			}
		}
	}

}
