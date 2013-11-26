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

    private String password; // crossword password
    private int actualIndex = 0; // index of actual word

    /**
     * Checks if it's possible to generate crossword with this password.
     *
     * @param crossword - input crossword
     * @return true if password is OK, otherwise false
     */
    private Boolean checkPassword(Crossword crossword, Board board) {
        char lettersOfPassword[] = password.toCharArray();
        Map<Character, Integer> numberOfCharUse = new HashMap<>();  //maps character - number of use in password
        for (char letter : lettersOfPassword) {
            if (!numberOfCharUse.containsKey(letter)) {
                numberOfCharUse.put(letter, 1);
            } else {
                numberOfCharUse.put(letter, numberOfCharUse.get(letter) + 1);
            }
        }
        for (char key : numberOfCharUse.keySet()) { // checks if there is enough words starting with key letter
            if (key == password.charAt(0)) {
                if (crossword.getCwdb().findAll(key + ".{1,"
                        + Integer.toString(board.getWidth() - 1) + "}").size()
                        <= numberOfCharUse.get(key)) {
                    return Boolean.FALSE;
                }
            } else {
                if (crossword.getCwdb().findAll(key + ".{1,"
                        + Integer.toString(board.getWidth() - 1) + "}").size()
                        < numberOfCharUse.get(key)) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
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
        LinkedList<Entry> list = crossword.getCwdb().findAll(board.getHeight()); //looks for crossword's height length words
        if (list.isEmpty()) { //if there's no - failed
            throw new FailedToGenerateCrosswordException("No matching words");
        }
        Entry temp = null;
        do {
            temp = list.get(random.nextInt(list.size()));
            list.remove(temp);
            password = temp.getWord();
        } while (!checkPassword(crossword, board) && list.size() > 0); // checks possible passwords until there's no left or some is good
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
        if (crossword.isEmpty()) { // first - password
            rand = generatePassword(crossword, board);
        } else if (actualIndex < password.length()) { // for every line - one word
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
