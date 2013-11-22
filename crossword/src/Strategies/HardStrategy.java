/**
 * HardStrategy.java
 *
 * @author - wukat
 * @data - 15 lis 2013
 */
package Strategies;

import Exceptions.FailedToGenerateCrosswordException;
import board.Board;
import board.BoardCell;
import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author wukat
 *
 */
public class HardStrategy extends Strategy {

    /**
     * Constructor
     */
    public HardStrategy() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see board.Strategy#findEntry(board.Crossword)
     */
    @Override
    public CwEntry findEntry(Crossword crossword)
            throws FailedToGenerateCrosswordException {
        if (crossword.getCwdb().getSize() == 0)
            throw new FailedToGenerateCrosswordException();
        Random rand = new Random();
        LinkedList<BoardCell> startingCells = crossword.getBoardCopy().getStartCells();
        Boolean flag = Boolean.FALSE;
        do {
            BoardCell temp = startingCells.get(rand.nextInt(startingCells.size()));
            if (temp.getAbility(BoardCell.beg, BoardCell.hor)) {
                int size = crossword.getBoardHeight();
                while (size > 1) {
                    
                }
            }
            
        } while (startingCells.size() > 0 || flag);
        return null;
    }

    /* (non-Javadoc)
     * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
     */
    @Override
    public void updateBoard(Board board, CwEntry entry) {
        if (entry.getDir() == CwEntry.Direction.VERT) {
            if (entry.getY() > 0) {
                board.getCell(entry.getX(), entry.getY() - 1).setFalse();
            }
            if (entry.getY() + entry.getWord().length() < board.getHeight()) {
                board.getCell(entry.getX(), entry.getY() + entry.getWord().length()).setFalse();
            }
            for (int y = entry.getY(); y < entry.getWord().length(); y++) {
                board.getCell(entry.getX(), y).setContent(
                        entry.getWord().charAt(y - entry.getY()));
                board.getCell(entry.getX(), y).setVerFalse();
            }
            if (entry.getX() > 0) {
                board.getCell(entry.getX() - 1, entry.getY()).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() - 1, entry.getY()).setAbility(BoardCell.in, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() - 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.in, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() - 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() - 1, entry.getY()).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() - 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                for (int y = entry.getY() + 1; y < entry.getWord().length() - 1; y++) {
                    board.getCell(entry.getX() - 1, y).setVerFalse();
                    board.getCell(entry.getX() - 1, y).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                }
            }
            if (entry.getX() + 1 < board.getWidth()) {
                board.getCell(entry.getX() + 1, entry.getY()).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + 1, entry.getY()).setAbility(BoardCell.in, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.in, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + 1, entry.getY()).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() + 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                for (int y = entry.getY() + 1; y < entry.getWord().length() - 1; y++) {
                    board.getCell(entry.getX() + 1, y).setVerFalse();
                    board.getCell(entry.getX() + 1, y).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                }
            }
        } else {
            if (entry.getX() > 0) {
                board.getCell(entry.getX() - 1, entry.getY()).setFalse();
            }
            if (entry.getX() + entry.getWord().length() < board.getWidth()) {
                board.getCell(entry.getX() + entry.getWord().length(), entry.getY()).setFalse();
            }
            for (int x = entry.getX(); x < entry.getWord().length(); x++) {
                board.getCell(x, entry.getY()).setContent(
                        entry.getWord().charAt(x - entry.getX()));
                board.getCell(x, entry.getY()).setVerFalse();
            }
            if (entry.getY() > 0) {
                board.getCell(entry.getX(), entry.getY() - 1).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX(), entry.getY() - 1).setAbility(BoardCell.in, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() - 1).setAbility(BoardCell.in, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() - 1).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX(), entry.getY() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                for (int x = entry.getX() + 1; x < entry.getWord().length() - 1; x++) {
                    board.getCell(x, entry.getY() -1).setHorFalse();
                    board.getCell(x, entry.getY() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                }
            }
            if (entry.getY() + 1 < board.getHeight()) {
                board.getCell(entry.getX(), entry.getY()  + 1).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX(), entry.getY()  + 1).setAbility(BoardCell.in, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() + 1).setAbility(BoardCell.in, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() + 1).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX(), entry.getY()  + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                for (int x = entry.getX() + 1; x < entry.getWord().length() - 1; x++) {
                    board.getCell(x, entry.getY() + 1).setHorFalse();
                    board.getCell(x, entry.getY() + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                }
            }
        }
    }
}
