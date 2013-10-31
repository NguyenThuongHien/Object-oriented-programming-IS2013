/**
 * EasyStrategy.java
 * @author - wukat
 * @data - 29 paź 2013
 */
package Strategies;

import board.Board;
import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;

/**
 * @author wukat
 * 
 */
public class EasyStrategy extends Strategy {
	private String actualPattern;
	private String password;
	private int i = 0;

	/**
	 * Creates pattern - password can't include letters for which are no entries
	 * in data base
	 * 
	 * @param crossword
	 *            - input crossword
	 * @return pattern
	 */
	public String createPasswordPatter(Crossword crossword) {
		String tempPattern = "";
		for (char i = 'a'; i <= 'z'; i++) {
			if (crossword
					.getCwdb()
					.findAll(
							""
									+ i
									+ ".{1,"
									+ Integer.toString(crossword
											.getBoardWidth() - 1) + "}").size() == 0)
				tempPattern += i;
		}
		for (char i = 'A'; i <= 'Z'; i++) {
			if (crossword
					.getCwdb()
					.findAll(
							""
									+ i
									+ ".{1,"
									+ Integer.toString(crossword
											.getBoardWidth() - 1) + "}").size() == 0)
				tempPattern += i;
		}
		char tab[] = { 'ż', 'Ż', 'ś', 'Ś', 'ć', 'Ć' };
		for (char i : tab) {
			if (crossword
					.getCwdb()
					.findAll(
							""
									+ i
									+ ".{1,"
									+ Integer.toString(crossword
											.getBoardWidth() - 1) + "}").size() == 0)
				tempPattern += i;
		}
		return "[^óęąźńÓĄĘŹŃ" + tempPattern + "]{"
				+ Integer.toString(crossword.getBoardHeight()) + "}";
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
			rand = new CwEntry(crossword.getCwdb().getRandom(
					createPasswordPatter(crossword)), 0, 0,
					CwEntry.Direction.VERT);
			password = rand.getWord();
		} else if (i < password.length()) {
			actualPattern = password.charAt(i) + ".{1,"
					+ Integer.toString(crossword.getBoardWidth() - 1) + "}";
			rand = new CwEntry(crossword.getCwdb().getRandom(actualPattern), 0,
					i, CwEntry.Direction.HORIZ);
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
