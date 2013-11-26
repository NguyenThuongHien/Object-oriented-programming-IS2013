package board;

import java.util.LinkedList;

/**
 * @author wukat
 *
 */
public class Board {

    private final BoardCell[][] board; // crosword's board

    /**
     *
     * Constructor
     *
     * @param width - board's width
     * @param height - board's height
     */
    public Board(int width, int height) {
        board = new BoardCell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new BoardCell("");
            }
        }
        setAllAbilitiesAtTheBeginning();
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
     * @param x - horizontal position
     * @param y - vertical position
     * @return certain x,y board cell
     */
    public BoardCell getCell(int x, int y) {
        return board[x][y];
    }

    /**
     * Gets vertiacl position of cell
     * @param cell
     * @return int - position
     */
    public int getVerPosition(BoardCell cell) {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (board[i][j] == cell) {
                    return j;
                }
            }
        }
        return -1;
    }

    /**
     * Gets vertiacl position of cell
     * @param cell
     * @return int - position
     */
    public int getHorPosition(BoardCell cell) {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (board[i][j] == cell) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Setter
     *
     * @param x - horizontal position
     * @param y - vertical position
     * @param c - cell to set
     */
    public void setCell(int x, int y, BoardCell c) {
        board[x][y] = c;
    }

    /**
     * Sets ability to false in places like corners and at borders.
     */
    private void setAllAbilitiesAtTheBeginning() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (i == 0) {
                    getCell(i, j).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                    getCell(i, j).setAbility(BoardCell.in, BoardCell.hor, Boolean.FALSE);
                }
                if (i == getWidth() - 1) {
                    getCell(i, j).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                    getCell(i, j).setAbility(BoardCell.in, BoardCell.hor, Boolean.FALSE);
                }
                if (j == 0) {
                    getCell(i, j).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                    getCell(i, j).setAbility(BoardCell.in, BoardCell.ver, Boolean.FALSE);
                }
                if (j == getHeight() - 1) {
                    getCell(i, j).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                    getCell(i, j).setAbility(BoardCell.in, BoardCell.ver, Boolean.FALSE);
                }
            }
        }
    }

    /**
     * Gets cells in which word can start
     *
     * @return list of cells with beg ability
     */
    public LinkedList<BoardCell> getStartCells() {
        LinkedList<BoardCell> startCells = new LinkedList<>();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if ((getCell(i, j).getAbility(BoardCell.beg, BoardCell.hor))
                        || (getCell(i, j).getAbility(BoardCell.beg,
                                BoardCell.ver))) {
                    startCells.add(getCell(i, j));
                }
            }
        }
        return startCells;
    }

    /**
     * Copying function
     *
     * @return board copy
     */
    public Board copy() {
        Board boardCopy = new Board(getWidth(), getHeight());
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                boardCopy.setCell(i, j, getCell(i, j).copy());
            }
        }
        return boardCopy;
    }

    /**
     * Function creates pattern of word in position fromx, fromy, tox, toy
     *
     * @param fromx - beginning in x axe
     * @param fromy - beginning in y axe
     * @param tox - end in x axe
     * @param toy - end in y axe
     * @return pattern string
     */
    public String createPattern(int fromx, int fromy, int tox, int toy) {
        String pattern = "";
        if (fromx == tox) {
            for (int i = fromy; i < toy; i++) {
                if (getCell(fromx, i).checkContent()) {
                    pattern += getCell(fromx, i).getContent();
                } else {
                    pattern += ".";
                }
            }
        } else if (fromy == toy) {
            for (int i = fromx; i < tox; i++) {
                if (getCell(i, fromy).checkContent()) {
                    pattern += getCell(i, fromy).getContent();
                } else {
                    pattern += ".";
                }
            }
        }
        return pattern;
    }
}
