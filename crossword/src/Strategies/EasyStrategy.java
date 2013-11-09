/**
 * EasyStrategy.java
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
	private int i = 0;

	/**
	 * Checks if it's possible to generate crossword with this password.
	 * 
	 * @param crossword
	 *            - input crossword
	 * @return true if password is OK
	 */
	private boolean checkPassword(Crossword crossword) {
		char lettersOfPassword[] = password.toCharArray();
		Map<Character, Integer> numberOfCharUse = new HashMap<Character, Integer>();
		for (char i : lettersOfPassword) {
			if (!numberOfCharUse.containsKey(i))
				numberOfCharUse.put(i, 1);
			else
				numberOfCharUse.put(i, numberOfCharUse.get(i) + 1);
		}
		for (char i : numberOfCharUse.keySet()) {
			if (i == password.charAt(0)) {
			if (crossword
					.getCwdb()
					.findAll(
							i
									+ ".{1,"
									+ Integer.toString(crossword
											.getBoardWidth() - 1) + "}").size() <= numberOfCharUse
					.get(i))
				return false;
			}
			else {
				if (crossword
						.getCwdb()
						.findAll(
								i
										+ ".{1,"
										+ Integer.toString(crossword
												.getBoardWidth() - 1) + "}").size() < numberOfCharUse
						.get(i))
					return false;
				}
			}
		return true;
	}

	/**
	 * Generates proper password
	 * @param crossword
	 *            - input crossword
	 * @return password 
	 * @throws FailedToGenerateCrosswordException
	 */
	private CwEntry generatePassword(Crossword crossword) throws FailedToGenerateCrosswordException {
		Random random = new Random();
        i = 0;
		LinkedList<Entry> list = crossword.getCwdb().findAll(
				crossword.getBoardHeight());
		if (list.isEmpty())
			throw new FailedToGenerateCrosswordException("No matching words");
		Entry temp = list.get(random.nextInt(list.size()));
		list.remove(temp);
		password = temp.getWord();
		while (!checkPassword(crossword) && list.size() > 0) {
			temp = list.get(random.nextInt(list.size()));
			list.remove(temp);
			password = temp.getWord();
		}
		if (list.isEmpty() && !checkPassword(crossword))
		    throw new FailedToGenerateCrosswordException("No matching words");
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
		if (crossword.isEmpty()) {
			rand = generatePassword(crossword);
		} else if (i < password.length()) {
			actualPattern = password.charAt(i) + ".{1,"
					+ Integer.toString(crossword.getBoardWidth() - 1) + "}";
			rand = new CwEntry(crossword.getCwdb().getRandom(actualPattern), 0,
					i, CwEntry.Direction.HORIZ);
			while (crossword.contains(rand.getWord()))
				rand = new CwEntry(
						crossword.getCwdb().getRandom(actualPattern), 0, i,
						CwEntry.Direction.HORIZ);
			i++;
		}
		return rand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see board.Strategy#updateBoard(board.Board, dictionary.CwEntry)
	 */
	@Override
	public void updateBoard(Board board, CwEntry entry){
		if (entry.getDir() == CwEntry.Direction.VERT) {
			for (int i = entry.getY(); i < entry.getWord().length(); i++) {
				board.getCell(entry.getX(), i).setContent(
						entry.getWord().charAt(i));
			}
		} else {
			for (int i = entry.getX(); i < entry.getWord().length(); i++) {
				board.getCell(i, entry.getY()).setContent(
						entry.getWord().charAt(i));
			}
		}
	}

    public String printAllEntries(Crossword crossword) {
        String resultHor = "Horizontally: \n";
        Iterator<CwEntry> iter = crossword.getROEntryIter();
        int k = 1;
        while (iter.hasNext()) {
            CwEntry temp = iter.next();
            if (temp.getDir() == CwEntry.Direction.HORIZ)          {
                resultHor = resultHor + k + ". " + temp.getClue() + "\n";
                k++;
            }
        }
        return resultHor;
    }
}
