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

    private Boolean checkAbilities(int size, Board board, int dir, int positionVer, int positionHor) {
        if (dir == BoardCell.ver) {
            for (int i = positionVer + 1; i < positionVer + size - 1; i++) {
                if (!board.getCell(positionHor, i).getAbility(BoardCell.in, dir)) {
                    return Boolean.FALSE;
                }
            }
            if (!board.getCell(positionHor, positionVer + size - 1).getAbility(BoardCell.end, dir)) {
                return Boolean.FALSE;
            }
        } else {
            for (int i = positionHor + 1; i < positionHor + size - 1; i++) {
                if (!board.getCell(i, positionVer).getAbility(BoardCell.in, dir)) {
                    return Boolean.FALSE;
                }
            }
            if (!board.getCell(positionHor + size - 1, positionVer).getAbility(BoardCell.end, dir)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private Boolean checkIfHaveAnyLetter(int size, Board board, int dir, int positionVer, int positionHor) {
        if (dir == BoardCell.ver) {
            for (int i = positionVer; i < positionVer + size; i++) {
                if (board.getCell(positionHor, i).checkContent()) {
                    return Boolean.TRUE;
                }
            }
        } else {
            for (int i = positionHor; i < positionHor + size; i++) {
                if (board.getCell(i, positionVer).checkContent()) {
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
            if (crossword.isEmpty()) {
                if (rand.nextBoolean()) {
                    temp = board.getCell(0, rand.nextInt(board.getHeight()));
                } else {
                    temp = board.getCell(rand.nextInt(board.getWidth()), 0);
                }
            } else {
                temp = startingCells.get(rand.nextInt(startingCells.size()));
            }
            if (temp.getAbility(BoardCell.beg, BoardCell.ver)) {
                int size = board.getHeight() - board.getVerPosition(temp);
                while (size > 1 && !flag) {
                    if (checkAbilities(size, board, BoardCell.ver, board.getVerPosition(temp), board.getHorPosition(temp)) && (checkIfHaveAnyLetter(size, board, BoardCell.ver, board.getVerPosition(temp), board.getHorPosition(temp)) || crossword.isEmpty())) {
                        LinkedList<Entry> matched = crossword.getCwdb().findAll(board.createPattern(board.getHorPosition(temp), board.getVerPosition(temp), board.getHorPosition(temp), board.getVerPosition(temp) + size));
                        while (matched.size() > 0 && !flag) {
                            Entry found = matched.get(rand.nextInt(matched.size()));
                            if (!crossword.contains(found.getWord())) {
                                flag = Boolean.TRUE;
                                toReturn = new CwEntry(found, board.getHorPosition(temp), board.getVerPosition(temp), CwEntry.Direction.VERT);
                            }
                            matched.remove(found);
                        } 
                    }
                    size--;
                }
            }
            if (!flag && temp.getAbility(BoardCell.beg, BoardCell.hor)) {
                int size = board.getWidth() - board.getHorPosition(temp);
                while (size > 1 && !flag) {
                    if (checkAbilities(size, board, BoardCell.hor, board.getVerPosition(temp), board.getHorPosition(temp)) && (checkIfHaveAnyLetter(size, board, BoardCell.hor, board.getVerPosition(temp), board.getHorPosition(temp)) || crossword.isEmpty())) {
                        LinkedList<Entry> matched =  crossword.getCwdb().findAll(board.createPattern(board.getHorPosition(temp), board.getVerPosition(temp), board.getHorPosition(temp) + size, board.getVerPosition(temp)));
                        while (matched.size() > 0 && !flag) {
                            Entry found = matched.get(rand.nextInt(matched.size()));
                            if (!crossword.contains(found.getWord())) {
                                flag = Boolean.TRUE;
                                toReturn = new CwEntry(found, board.getHorPosition(temp), board.getVerPosition(temp), CwEntry.Direction.HORIZ);
                            }
                            matched.remove(found);
                        } 
                    }
                    size--;
                }
            }
            startingCells.remove(temp);
        }
        return toReturn;
    }

    /* (non-Javadoc)
     * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
     */
    @Override
    public void updateBoard(Board board, CwEntry entry
    ) {
        if (entry.getDir() == CwEntry.Direction.VERT) {
            if (entry.getY() > 0 && entry.getX() < board.getWidth() - 1) {
                board.getCell(entry.getX() + 1, entry.getY() - 1).setAbility(BoardCell.beg, BoardCell.hor, Boolean.FALSE);
            }
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
            if (entry.getY() < board.getHeight() - 1 && entry.getX() > 0) {
                board.getCell(entry.getX() - 1, entry.getY() + 1).setAbility(BoardCell.beg, BoardCell.ver, Boolean.FALSE);
            }
            if (entry.getX() > 0) {
                board.getCell(entry.getX() - 1, entry.getY()).setFalse();
            }
            if (entry.getX() + entry.getWord().length() < board.getWidth()) {
                board.getCell(entry.getX() + entry.getWord().length(), entry.getY()).setFalse();
            }
            for (int x = entry.getX(); x < entry.getX() + entry.getWord().length(); x++) {
                board.getCell(x, entry.getY()).setContent(
                        entry.getWord().charAt(x - entry.getX()));
                board.getCell(x, entry.getY()).setHorFalse();
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
