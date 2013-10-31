/**
 * EasyStrategy.java
 * @author - wukat
 * @data - 29 paź 2013
 */
package Strategies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import board.Board;
import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;
import dictionary.Entry;

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
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see board.Strategy#findEntry(board.Crossword)
	 */
	@Override
	public CwEntry findEntry(Crossword crossword) {
		CwEntry rand = null;
		if (crossword.isEmpty()) {
			Random random = new Random();
			LinkedList<Entry> list = crossword.getCwdb().findAll(crossword.getBoardHeight());
			Entry temp = list.get(random.nextInt(list.size()));
			rand = new CwEntry(temp, 0, 0, CwEntry.Direction.VERT);
			list.remove(temp);
			password = rand.getWord();
			while (!checkPassword(crossword) && list.size() > 0) {
				temp = list.get(random.nextInt(list.size()));
				rand = new CwEntry(temp, 0, 0, CwEntry.Direction.VERT);
				list.remove(temp);
				password = rand.getWord();
			}
//			if (list.isEmpty() && !checkPassword(crossword)) 
//				throws 
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
	public void updateBoard(Board board, CwEntry entry) {
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
}
