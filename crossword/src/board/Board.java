/**
 * Board.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package board;

import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
public class Board {
	private BoardCell[][] board; // crosword's board

	/**
	 * 
	 * Constructor
	 * 
	 * @param width
	 *            - board's width
	 * @param height
	 *            - board's height
	 */
	public Board(int width, int height) {
		board = new BoardCell[width][height];
	}

	/**
	 * Getter
	 * 
	 * @return width of board
	 */
	public int getWidth() {
		return board.length;
	}

	/**
	 * Getter
	 * 
	 * @return height of board
	 */
	public int getHeight() {
		return board[0].length;
	}

	/**
	 * Getter
	 * 
	 * @param x
	 *            - horizontal position
	 * @param y
	 *            - vertical position
	 * @return certain x,y board cell
	 */
	public BoardCell getCell(int x, int y) {
		// TODO exception
		return board[x][y];
	}

	/**
	 * Setter
	 * 
	 * @param x
	 *            - horizontal position
	 * @param y
	 *            - vertical position
	 * @param c
	 *            - cell to set
	 */
	public void setCell(int x, int y, BoardCell c) {
		// TODO exception
		board[x][y] = c;
	}

	/**
	 * Gets cells in which word can start
	 * @return list of cells
	 */
	public LinkedList<BoardCell> getStartCells() {
		LinkedList<BoardCell> startCells = new LinkedList<BoardCell>();
		for (int i = 0; i < getWidth(); i++)
			for (int j = 0; j < getHeight(); j++)
				if ((getCell(i, j).getAbility(BoardCell.hor, BoardCell.beg))
						|| (getCell(i, j).getAbility(BoardCell.ver,
								BoardCell.beg)))
					startCells.add(getCell(i, j));
		return startCells;
	}

//	public createPattern(int fromx, int fromy, int tox, int toy) {
//		
//	}
}
