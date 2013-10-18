/**
 * Board.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package board;

/**
 * @author wukat
 * 
 */
public class BoardCell {
	private String content; // content of cell
	private Boolean[][] abilities; // array of abilities - hor/ver beg/in/end
									// enable/disable

	public static final int beg = 0;
	public static final int in = 1;
	public static final int end = 2;
	public static final int hor = 0;
	public static final int ver = 1;
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
	 * @param dir
	 *            - direction
	 * @param pos
	 *            - position
	 * @param ability
	 *            - boolean - true if enable
	 */
	public void setAbility(int dir, int pos, Boolean ability) {
		//TODO exception
		abilities[dir][pos] = ability;
	}

	/**
	 * Gets ability in certain direction/position
	 * 
	* @param dir
	 *            - direction
	 * @param pos
	 *            - position
	 * @return ability - boolean - true if enable
	 */
	public Boolean getAbility(int dir, int pos) {
		//TODO exception
		return abilities[dir][pos];
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
	 * Function copying boardCell
	 * @return copy
	 */
	public BoardCell copy() {
		BoardCell boardCellCopy = new BoardCell(new String(getContent()));
		for (int i = 0; i < 1; i++)
			for (int j = 0; j < 2; j++)
				boardCellCopy.setAbility(i, j, new Boolean(getAbility(i, j)));
		return boardCellCopy;
	}
}
