/**
 * InteliCwDB.java
 * @author - wukat
 * @data - 13 paź 2013
 */
package dictionary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Intelligent crossword database
 * 
 * @author wukat
 * 
 */
public class InteliCwDB extends CwDB {

	/**
	 * Constructor
	 * 
	 * @param filename
	 */
	public InteliCwDB(String filename) {
		super(filename);
		Collections.sort(dict, new EntriesComparator());
	}

	/**
	 * Finds all entries that match to pattern (regular expression)
	 * 
	 * @param pattern
	 *            - regular expression
	 * @return list of matching entries
	 */
	public LinkedList<Entry> findAll(String pattern) {
		LinkedList<Entry> matched = new LinkedList<Entry>();
		java.util.ListIterator<Entry> iter = dict.listIterator();
		while (iter.hasNext()) {
			Entry temp = iter.next();
			if (temp.getWord().matches(pattern))
				matched.add(temp);
		}
		return matched;
	}

	/**
	 * Finds all entries which length is equal to parameter
	 * 
	 * @param length
	 * @return list of matching entries
	 */
	public LinkedList<Entry> findAll(int length) {
		LinkedList<Entry> matched = new LinkedList<Entry>();
		java.util.ListIterator<Entry> iter = dict.listIterator();
		while (iter.hasNext()) {
			Entry temp = iter.next();
			if (temp.getWord().length() == length)
				matched.add(temp);
		}
		return matched;
	}

	/**
	 * Gets random entry
	 * @return entry
	 */
	public Entry getRandom() {
		Random rand = new Random();
		return dict.get(rand.nextInt(dict.size()));
	}

	/**
	 * Gets random entry with word's length equal to parameter
	 * @param length - integer length
	 * @return entry
	 */
	public Entry getRandom(int length) {
		Random rand = new Random();
		LinkedList<Entry> lenDict = findAll(length);
		if (lenDict.isEmpty()) 
			return null;
		else
		    return lenDict.get(rand.nextInt(lenDict.size()));
	}

	/**
	 * Gets random entry with word matching pattern
	 * @param pattern - regular expression
	 * @return entry
	 */
	public Entry getRandom(String pattern) {
		Random rand = new Random();
		LinkedList<Entry> patDict = findAll(pattern);
		if (patDict.isEmpty()) 
			return null;
		else
		    return patDict.get(rand.nextInt(patDict.size()));
	}

	/**
	 * Overrides the add method from CwDB, inserts and sorts dictionary
	 * 
	 * @param word
	 * @param clue
	 */
	public void add(String word, String clue) {
		dict.addFirst(new Entry(word, clue));
		Collections.sort(dict, new EntriesComparator());
	}
}
