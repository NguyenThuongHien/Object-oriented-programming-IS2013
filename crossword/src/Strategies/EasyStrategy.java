/**
 * EasyStrategy.java
 *
 * @author - wukat
 * @data - 29 paź 2013
 */
package Strategies;

import Exceptions.FailedToGenerateCrosswordException;
import board.Board;
import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;
import dictionary.Entry;

import java.util.*;

/**
 * @author wukat
 *
 */
public class EasyStrategy extends Strategy {

    private String actualPattern;
    private String password;
    private int actualIndex = 0;

    /**
     * Checks if it's possible to generate crossword with this password.
     *
     * @param crossword - input crossword
     * @return true if password is OK
     */
    private boolean checkPassword(Crossword crossword, Board board) {
        char lettersOfPassword[] = password.toCharArray();
        Map<Character, Integer> numberOfCharUse = new HashMap<>();
        for (char letter : lettersOfPassword) {
            if (!numberOfCharUse.containsKey(letter)) {
                numberOfCharUse.put(letter, 1);
            } else {
                numberOfCharUse.put(letter, numberOfCharUse.get(letter) + 1);
            }
        }
        for (char key : numberOfCharUse.keySet()) {
            if (key == password.charAt(0)) {
                if (crossword
                        .getCwdb()
                        .findAll(
                                key
                                + ".{1,"
                                + Integer.toString(board.getWidth() - 1) + "}").size() <= numberOfCharUse
                        .get(key)) {
                    return false;
                }
            } else {
                if (crossword
                        .getCwdb()
                        .findAll(
                                key
                                + ".{1,"
                                + Integer.toString(board.getWidth() - 1) + "}").size() < numberOfCharUse
                        .get(key)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generates proper password
     *
     * @param crossword - input crossword
     * @return password
     * @throws FailedToGenerateCrosswordException
     */
    private CwEntry generatePassword(Crossword crossword, Board board) throws FailedToGenerateCrosswordException {
        Random random = new Random();
        actualIndex = 0;
        LinkedList<Entry> list = crossword.getCwdb().findAll(
                board.getHeight());
        if (list.isEmpty()) {
            throw new FailedToGenerateCrosswordException("No matching words");
        }
        Entry temp = list.get(random.nextInt(list.size()));
        list.remove(temp);
        password = temp.getWord();
        while (!checkPassword(crossword, board) && list.size() > 0) {
            temp = list.get(random.nextInt(list.size()));
            list.remove(temp);
            password = temp.getWord();
        }
        if (list.isEmpty() && !checkPassword(crossword, board)) {
            throw new FailedToGenerateCrosswordException("No matching words");
        }
        return new CwEntry(temp, 0, 0, CwEntry.Direction.VERT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see board.Strategy#findEntry(board.Crossword)
     * @throws FailedGeneratingCrossword
     */
    @Override
    public CwEntry findEntry(Crossword crossword) throws FailedToGenerateCrosswordException {
        CwEntry rand = null;
        Random random = new Random();
        Board board = crossword.getBoardCopy();
        if (crossword.isEmpty()) {
            rand = generatePassword(crossword, board);
        } else if (actualIndex < password.length()) {
            LinkedList<Entry> tempList = crossword.getCwdb().findAll(password.charAt(actualIndex) + ".{1,"
                    + Integer.toString(board.getWidth() - 1) + "}");
            Entry temp;
            do {
                temp = tempList.get(random.nextInt(tempList.size()));
            } while (crossword.contains(temp.getWord()));
            rand = new CwEntry(temp, 0,
                    actualIndex, CwEntry.Direction.HORIZ);
            actualIndex++;
        }
        return rand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
     */
    @Override
    public void updateBoard(Board board, CwEntry entry) {
        if (entry.getDir() == CwEntry.Direction.VERT) {
            for (int y = entry.getY(); y < entry.getWord().length(); y++) {
                board.getCell(entry.getX(), y).setContent(
                        entry.getWord().charAt(y));
            }
        } else {
            for (int x = entry.getX(); x < entry.getWord().length(); x++) {
                board.getCell(x, entry.getY()).setContent(
                        entry.getWord().charAt(x));
            }
        }
    }
}
