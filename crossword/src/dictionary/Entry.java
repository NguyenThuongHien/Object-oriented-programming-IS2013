/**
 * Entry.java
 * @author - wukat
 * @data - 12 paź 2013
 */
package dictionary;

/**
 * @author wukat
 * 
 */
public class Entry {
	private String word;
	private String clue;

	/**
	 * Constructor
	 * 
	 * @param word
	 *            - word in crossword
	 * @param clue
	 *            - clue in crossword
	 */
	public Entry(String word, String clue) {
		this.word = word;
		this.clue = clue;
	}

	/**
	 * Getter
	 * 
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * word setter
	 * 
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Getter
	 * 
	 * @return the clue
	 */
	public String getClue() {
		return clue;
	}

	/**
	 * clue setter
	 * 
	 * @param clue
	 *            the clue to set
	 */
	public void setClue(String clue) {
		this.clue = clue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return word + "\n" + clue + "\n";
	}
}
