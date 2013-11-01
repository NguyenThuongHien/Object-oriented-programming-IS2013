/**
 * Board.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package board;

import java.util.LinkedList;
import java.util.regex.Pattern;

import Exceptions.WrongDimensionInBoardAsked;

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
	 * @throws WrongDimensionInBoardAsked
	 */
	public Board(int width, int height) throws WrongDimensionInBoardAsked {
		if (width <= 0 || height <= 0)
			throw new WrongDimensionInBoardAsked();
		board = new BoardCell[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				board[i][j] = new BoardCell("");
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
	 * @throws WrongDimensionInBoardAsked
	 */
	public BoardCell getCell(int x, int y) throws WrongDimensionInBoardAsked {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
			throw new WrongDimensionInBoardAsked();
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
	 * @throws WrongDimensionInBoardAsked
	 */
	public void setCell(int x, int y, BoardCell c)
			throws WrongDimensionInBoardAsked {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
			throw new WrongDimensionInBoardAsked();
		board[x][y] = c;
	}

	/**
	 * Gets cells in which word can start
	 * 
	 * @return list of cells
	 * @throws WrongDimensionInBoardAsked
	 */
	public LinkedList<BoardCell> getStartCells()
			throws WrongDimensionInBoardAsked {
		LinkedList<BoardCell> startCells = new LinkedList<BoardCell>();
		for (int i = 0; i < getWidth(); i++)
			for (int j = 0; j < getHeight(); j++)
				if ((getCell(i, j).getAbility(BoardCell.hor, BoardCell.beg))
						|| (getCell(i, j).getAbility(BoardCell.ver,
								BoardCell.beg)))
					startCells.add(getCell(i, j));
		return startCells;
	}

	/**
	 * Copying function
	 * 
	 * @return board copy
	 * @throws WrongDimensionInBoardAsked
	 */
	public Board copy() throws WrongDimensionInBoardAsked {
		Board boardCopy = new Board(getWidth(), getHeight());
		for (int i = 0; i < getWidth(); i++)
			for (int j = 0; j < getHeight(); j++)
				boardCopy.setCell(i, j, getCell(i, j).copy());
		return boardCopy;
	}

	/**
	 * Function creates pattern of word in position fromx, fromy, tox, toy
	 * 
	 * @param fromx
	 *            - beginning in x axe
	 * @param fromy
	 *            - beginning in y axe
	 * @param tox
	 *            - end in x axe
	 * @param toy
	 *            - end in y axe
	 * @throws WrongDimensionInBoardAsked
	 */
	public Pattern createPattern(int fromx, int fromy, int tox, int toy)
			throws WrongDimensionInBoardAsked {
		String pattern = "";
		if (fromx == tox) {
			for (int i = fromy; i < toy; i++) {
				if (getCell(fromx, i).checkContent())
					pattern += getCell(fromx, i).getContent();
				else
					pattern += ".";
			}
		} else if (fromy == toy) {
			for (int i = fromx; i < tox; i++) {
				if (getCell(i, fromy).checkContent())
					pattern += getCell(i, fromy).getContent();
				else
					pattern += ".";
			}
		}
		return Pattern.compile(pattern);
	}
}
