/**
 * CwDB.java
 * @author - wukat
 * @data - 12 paź 2013
 */
package dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Implementation of crossword database
 * 
 * @author wukat
 * 
 */
public class CwDB {
	private LinkedList<Entry> dict = new LinkedList<Entry>(); // dictionary -
																// list of
																// entries

	/**
	 * Constructor
	 * 
	 * @param filename
	 *            - name of file with data
	 */
	public CwDB(String filename) {
		try {
			createDB(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Nie ma takiego pliku!");
			// TODO something here
		} catch (IOException e) {
			// TODO something here
		}
	}

	/**
	 * Adds new record
	 * 
	 * @param word
	 * @param clue
	 */
	public void add(String word, String clue) {
		dict.add(new Entry(word, clue));
	}

	/**
	 * Getter
	 * 
	 * @param word
	 *            - parameter to identify entry
	 * @return entry in dictionary with field word
	 */
	public Entry get(String word) {
		Entry temp = null;
		for (java.util.ListIterator<Entry> iter = dict.listIterator(0); iter
				.hasNext(); temp = iter.next())
			if (temp.getWord().equals(word))
				return temp;

		return temp;
	}

	/**
	 * Removes specified entry from dictionary
	 * 
	 * @param word
	 *            - parameter to identify entry
	 */
	public void remove(String word) {
		Entry temp = null;
		for (java.util.ListIterator<Entry> iter = dict.listIterator(0); iter
				.hasNext(); temp = iter.next())
			if (temp.getWord().equals(word))
				iter.remove();
	}

	/**
	 * Saves database in file
	 * 
	 * @param filename
	 *            - name of output file
	 */
	public void saveDB(String filename) throws IOException {
		FileWriter outputDB = null;
		try {
			outputDB = new FileWriter(filename);
			Entry temp = null;
			for (java.util.ListIterator<Entry> iter = dict.listIterator(0); iter
					.hasNext(); temp = iter.next())
				outputDB.write(temp.toString());
		} finally {
			if (outputDB != null)
				outputDB.close();
		}
	}

	/**
	 * Getter
	 * 
	 * @return size of dictionary (number of elements)
	 */
	public int getSize() {
		return dict.size();
	}

	/**
	 * Creates database from file
	 * 
	 * @param filename
	 *            - file name
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected void createDB(String filename) throws IOException,
			FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
			String line1 = null;
			String line2 = null;
			while (((line1 = br.readLine()) != null)
					&& ((line2 = br.readLine()) != null)) {
				add(line1, line2);
			}
		} finally {
			br.close();
		}
	}
}
