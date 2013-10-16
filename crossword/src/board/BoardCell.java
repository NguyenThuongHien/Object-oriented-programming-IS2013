/**
 * Board.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package board;

import java.util.Map;

/**
 * @author wukat
 * 
 */
public class BoardCell {
	public enum Direction {
		HORIZ, VERT
	} // two options - first if word is horizontal, second - vertical

	public enum Position {
		BEG, IN, END
	} // three options - if cell can be the at the beginning, inner, ending

	private String content; // content of cell
	private Map<DirectionPositionPair, Boolean> possibilities; // map of pairs,
																// enables or
																// disables
																// possibilities

	/**
	 * 
	 * Constructor
	 * 
	 * @param content
	 *            - content of cell
	 */
	public BoardCell(String content) {
		this.content = content;
	}

	/**
	 * Sets ability in certain direction/position
	 * 
	 * @param dirPos
	 *            - pair - direction and position
	 * @param ability
	 *            - boolean - true if enable
	 */
	public void setPossibility(DirectionPositionPair dirPos, Boolean ability) {
		possibilities.put(dirPos, ability);
	}

	/**
	 * Getter
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * content setter
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @author wukat
	 * 
	 */
	public class DirectionPositionPair {
		Direction dir; // direction
		Position pos; // position

		/**
		 * 
		 * Constructor
		 * 
		 * @param dir
		 *            - direction
		 * @param pos
		 *            - position
		 */
		public DirectionPositionPair(Direction dir, Position pos) {
			this.dir = dir;
			this.pos = pos;
		}
	}
}
