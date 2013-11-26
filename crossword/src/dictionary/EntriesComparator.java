package dictionary;

import java.util.Comparator;

/**
 * Comparator for entries
 * @author wukat
 * 
 */
public class EntriesComparator implements Comparator<Entry> {
	@Override
	public int compare(Entry e1, Entry e2) {
		return e1.getWord().compareTo(e2.getWord());
	}
}
