/**
 * CwEntry.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package dictionary;

/**
 * @author wukat
 * 
 */
public class CwEntry extends Entry {

	public enum Direction {
		HORIZ, VERT
	}; // two options - first if word is horizontal, second - vertical

	private int x; // level in vertical axe
	private int y; // position in horizontal axe
	private Direction d; // direction

	/**
	 * Constructor
	 * 
	 * @param word
	 *            - word in crossword
	 * @param clue
	 *            - clue in crossword
	 * @param x
	 *            - level in vertical axe
	 * @param y
	 *            - level in horizontal axe
	 * @param d
	 *            - direction
	 */
	public CwEntry(String word, String clue, int x, int y, Direction d) {
		super(word, clue);
		this.x = x;
		this.y = y;
		this.d = d;
	}

	/**
	 * Getter
	 * 
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * x setter
	 * 
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter
	 * 
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * y setter
	 * 
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter
	 * 
	 * @return the d
	 */
	public Direction getDir() {
		return d;
	}

	/**
	 * d setter
	 * 
	 * @param d
	 *            the d to set
	 */
	public void setDir(Direction d) {
		this.d = d;
	}
}
