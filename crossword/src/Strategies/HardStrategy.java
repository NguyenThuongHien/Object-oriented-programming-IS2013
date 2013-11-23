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
import dictionary.Entry;
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

    private Boolean checkAbilities(int size, Board board, int dir, int position) {
        if (dir == BoardCell.ver) {
            for (int i = position; i < position + size - 1; i++) {
                if (!board.getCell(position, i).getAbility(BoardCell.in, dir)) {
                    return Boolean.FALSE;
                }
            }
            if (!board.getCell(position, position + size - 1).getAbility(BoardCell.end, dir)) {
                return Boolean.FALSE;
            }
        } else {
            for (int i = position; i < position + size - 1; i++) {
                if (!board.getCell(i, position).getAbility(BoardCell.in, dir)) {
                    return Boolean.FALSE;
                }
            }
            if (!board.getCell(position + size - 1, position).getAbility(BoardCell.end, dir)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private Boolean checkIfHaveAnyLetter(int size, Board board, int dir, int position) {
        if (dir == BoardCell.ver) {
            for (int i = position; i < position + size; i++) {
                if (board.getCell(i, position).checkContent()) {
                    return Boolean.TRUE;
                }
            }
        } else {
            for (int i = position; i < position + size; i++) {
                if (board.getCell(position, i).checkContent()) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
    /* (non-Javadoc)
     * @see board.Strategy#findEntry(board.Crossword)
     */

    @Override
    public CwEntry findEntry(Crossword crossword)
            throws FailedToGenerateCrosswordException {
        if (crossword.getCwdb().getSize() == 0) {
            throw new FailedToGenerateCrosswordException();
        }
        Random rand = new Random();
        Board board = crossword.getBoardCopy();
        LinkedList<BoardCell> startingCells = board.getStartCells();
        Boolean flag = Boolean.FALSE;
        CwEntry toReturn = null;
        while (startingCells.size() > 0 && !flag) {
            BoardCell temp;
//            if (crossword.isEmpty())
//                temp = board.getCell(0, 0);
//            else
                temp = startingCells.get(rand.nextInt(startingCells.size()));
            if (temp.getAbility(BoardCell.beg, BoardCell.ver)) {
                int size = crossword.getBoardHeight() - board.getVerPosition(temp) - 1;
                while (size > 1 && !flag) {
                    if (checkAbilities(size, board, BoardCell.ver, board.getVerPosition(temp)) && (checkIfHaveAnyLetter(size, board, BoardCell.ver, board.getVerPosition(temp)) || crossword.isEmpty())) {
                        Entry found = crossword.getCwdb().getRandom(board.createPattern(board.getHorPosition(temp), board.getVerPosition(temp), board.getHorPosition(temp), board.getVerPosition(temp) + size).toString());
                        if (found != null) {
                            flag = Boolean.TRUE;
                            toReturn = new CwEntry(found, board.getHorPosition(temp), board.getVerPosition(temp), CwEntry.Direction.VERT);
                        }
                    }
                    size--;
                }
            }
            if (!flag && temp.getAbility(BoardCell.beg, BoardCell.hor)) {
                int size = crossword.getBoardWidth()- board.getHorPosition(temp) - 1;
                while (size > 1 && !flag) {
                    if (checkAbilities(size, board, BoardCell.hor, board.getHorPosition(temp)) && (checkIfHaveAnyLetter(size, board, BoardCell.hor, board.getHorPosition(temp)) || crossword.isEmpty())) {
                        Entry found = crossword.getCwdb().getRandom(board.createPattern(board.getHorPosition(temp), board.getVerPosition(temp), board.getHorPosition(temp)  + size, board.getVerPosition(temp)).toString());
                        if (found != null) {
                            flag = Boolean.TRUE;
                            toReturn = new CwEntry(found, board.getHorPosition(temp), board.getVerPosition(temp), CwEntry.Direction.HORIZ);
                        }
                    }
                    size--;
                }
            }
            startingCells.remove(temp);
        }
        if (toReturn != null) 
            System.out.println(toReturn.toString());
        return toReturn;
    }

    /* (non-Javadoc)
     * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
     */
    @Override
    public void updateBoard(Board board, CwEntry entry
    ) {
        if (entry.getDir() == CwEntry.Direction.VERT) {
            if (entry.getY() > 0) {
                board.getCell(entry.getX(), entry.getY() - 1).setFalse();
            }
            if (entry.getY() + entry.getWord().length() < board.getHeight()) {
                board.getCell(entry.getX(), entry.getY() + entry.getWord().length()).setFalse();
            }
            for (int y = entry.getY(); y < entry.getY() + entry.getWord().length(); y++) {
                board.getCell(entry.getX(), y).setContent(
                        entry.getWord().charAt(y - entry.getY()));
                board.getCell(entry.getX(), y).setVerFalse();
            }
            if (entry.getX() > 0) {
                board.getCell(entry.getX() - 1, entry.getY()).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() - 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                for (int y = entry.getY(); y < entry.getY() + entry.getWord().length(); y++) {
                    board.getCell(entry.getX() - 1, y).setVerFalse();
                    board.getCell(entry.getX() - 1, y).setAbility(BoardCell.end, BoardCell.hor, Boolean.FALSE);
                }
            }
            if (entry.getX() + 1 < board.getWidth()) {
                board.getCell(entry.getX() + 1, entry.getY()).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                board.getCell(entry.getX() + 1, entry.getY() + entry.getWord().length() - 1).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
                for (int y = entry.getY(); y < entry.getY() + entry.getWord().length(); y++) {
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
            for (int x = entry.getX(); x < entry.getX() + entry.getWord().length(); x++) {
                board.getCell(x, entry.getY()).setContent(
                        entry.getWord().charAt(x - entry.getX()));
                board.getCell(x, entry.getY()).setVerFalse();
            }
            if (entry.getY() > 0) {
                board.getCell(entry.getX(), entry.getY() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                for (int x = entry.getX(); x < entry.getX() + entry.getWord().length(); x++) {
                    board.getCell(x, entry.getY() - 1).setHorFalse();
                    board.getCell(x, entry.getY() - 1).setAbility(BoardCell.end, BoardCell.ver, Boolean.FALSE);
                }
            }
            if (entry.getY() + 1 < board.getHeight()) {
                board.getCell(entry.getX(), entry.getY() + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                board.getCell(entry.getX() + entry.getWord().length() - 1, entry.getY() + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                for (int x = entry.getX(); x < entry.getX() + entry.getWord().length(); x++) {
                    board.getCell(x, entry.getY() + 1).setHorFalse();
                    board.getCell(x, entry.getY() + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
                }
            }
        }
    }
}
