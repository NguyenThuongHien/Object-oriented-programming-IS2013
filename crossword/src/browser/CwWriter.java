/**
 * CwWriter.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

import dictionary.CwEntry;
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
		if (!file.isDirectory() || !file.canWrite())
			throw new IOException();
//		boolean flag = false;
//		String[] contentList = file.list();
//		for (String i : contentList) {
//			if (i.equals("Databases"))
//				flag = true;
//		}
//		if (!flag)
//			new File(path + "/Databases").mkdir();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see browser.Writer#write(board.Crossword)
	 */
	@Override
	public void write(Crossword crossword) throws IOException {
		long ID = getUniqueID();
		FileWriter cwFile = new FileWriter(file.getAbsolutePath() + "/"
				+ Long.toString(ID));
		try {
			cwFile.write(crossword.getBoardWidth() + " "
					+ crossword.getBoardHeight() + "\n");
//			cwFile.write(file.getAbsolutePath() + "/Databases/"
//					+ Long.toString(ID) + "\n");
//			crossword.getCwdb().saveDB(
//					file.getAbsolutePath() + "/Databases/" + Long.toString(ID));
			Iterator<CwEntry> iter = crossword.getROEntryIter();
			while (iter.hasNext()) {
				cwFile.write(iter.next().toString());
			}
		} finally {
			cwFile.close();
		}
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
