/**
 * Crossword.java
 * @author - wukat
 * @data - 18 paź 2013
 */
package board;

import Exceptions.FailedToGenerateCrosswordException;
import Strategies.EasyStrategy;
import dictionary.CwEntry;
import dictionary.IntelLiCwDB;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
public class Crossword {

    private LinkedList<CwEntry> entries; // list of
    // entries
    // in
    // crossword
    private Board board; // crossword's board
    private IntelLiCwDB cwdb = null; // crossword's intelligent database
    private int strategyID = 0;

    public int getStrategyID() {
        return strategyID;
    }

    public void setStrategyID(int strategyID) {
        this.strategyID = strategyID;
    }
    private final Long ID; // ID, default set to -1

    /**
     * 
     * Constructor
     * 
     * @param width
     *            - width of board
     * @param height
     *            - height of board
     * @param cwDB
     *            - data base
     */
    public Crossword(int width, int height, IntelLiCwDB cwDB) {
        entries = new LinkedList<>();
        board = new Board(width, height);
        cwdb = cwDB;
        this.ID = new Long(-1);
    }

    /**
     * 
     * Constructor - crossword from file, format: width, height \n, filename of
     * cwDB \n, CwEntries;
     * 
     * @param ID
     * @param f
     * @param easyStrategy 
     * @param hardStraategy 
     * 
     * @throws IOException
     * @throws NullPointerException
     */
    public Crossword(Long ID, File f, Strategy easyStrategy, Strategy hardStraategy) throws IOException, NullPointerException {
        entries = new LinkedList<>();
        this.ID = ID;
        Strategy strategy = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String temp = reader.readLine();
            switch (temp) {
                case "EASY":
                    this.strategyID = 0;
                    strategy = easyStrategy;
                    break;
                case "HARD":
                    strategy = hardStraategy;
                    this.strategyID = 1;
                    break;
                default:
                    throw new NullPointerException();
            }
            temp = reader.readLine();
            String[] splited = temp.split(" ");
            board = new Board(Integer.parseInt(splited[0]),
                    Integer.parseInt(splited[1]));
            while ((temp = reader.readLine()) != null) {
                splited = temp.split(" ");
                switch (splited[2]) {
                    case "HORIZ":
                        addCwEntry(new CwEntry(reader.readLine(), reader.readLine(), Integer.parseInt(splited[0]), Integer.parseInt(splited[1]),
                                dictionary.CwEntry.Direction.HORIZ), strategy);
                        break;
                    case "VERT":
                        addCwEntry(new CwEntry(reader.readLine(), reader.readLine(), Integer.parseInt(splited[0]), Integer.parseInt(splited[1]),
                                dictionary.CwEntry.Direction.VERT), strategy);
                        break;
                }
            }
        }

    }

    /**
     * Getter
     * 
     * @return the entries
     */
    private LinkedList<CwEntry> getEntries() {
        return entries;
    }

    /**
     * entries setter
     * 
     * @param entries
     *            the entries to set
     */
    private void setEntries(LinkedList<CwEntry> entries) {
        this.entries = entries;
    }

    /**
     * Getter
     * 
     * @return the board
     */
    private Board getBoard() {
        return board;
    }

    /**
     * board setter
     * 
     * @param board
     *            the board to set
     */
    private void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Special getter
     * 
     * @return board's height
     */
    public int getBoardHeight() {
        return board.getHeight();
    }

    /**
     * Special getter
     * 
     * @return board's width
     */
    public int getBoardWidth() {
        return board.getWidth();
    }

    /**
     * Getter
     * 
     * @return the cwdb
     */
    public IntelLiCwDB getCwdb() {
        return cwdb;
    }

    /**
     * cwdb setter
     * 
     * @param cwdb
     *            the cwdb to set
     */
    public void setCwdb(IntelLiCwDB cwdb) {
        this.cwdb = cwdb;
    }

    /**
     * Getter
     * 
     * @return the iD
     */
    public Long getID() {
        return ID;
    }

    /**
     * Getter
     * 
     * @return read-only iterator
     */
    public Iterator<CwEntry> getROEntryIter() {
        return Collections.unmodifiableList(getEntries()).iterator();
    }

    /**
     * Checks if list of entries is empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Getter (copy)
     * 
     * @return copy of board
     */
    public Board getBoardCopy() {
        return board.copy();
    }

    /**
     * Checks if board cell is not empty
     * @param i
     * @param j
     * @return logical value
     */
    public boolean checkBoardCell(int i, int j) {
        return board.getCell(i, j).checkContent();
    }

    /**
     * Function checks if crossword contains given word
     * 
     * @param word
     *            - word to find
     * @return true if contains
     */
    public boolean contains(String word) {
        java.util.ListIterator<CwEntry> iter = getEntries().listIterator();
        while (iter.hasNext()) {
            CwEntry temp = iter.next();
            if (temp.getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function adds crossword entry to list of entries and updates board
     * 
     * @param cwe
     *            - entry
     * @param strategy
     */
    public final void addCwEntry(CwEntry cwe, Strategy strategy) {
        entries.add(cwe);
        strategy.updateBoard(getBoard(), cwe);
    }

    /**
     * Function generating crossword
     * 
     * @param strategy
     * @throws FailedToGenerateCrosswordException
     */
    public final void generate(Strategy strategy)
            throws FailedToGenerateCrosswordException {
        CwEntry entry = null;
        if (strategy instanceof EasyStrategy)
            setStrategyID(0);
        else
            setStrategyID(1);
        while ((entry = strategy.findEntry(this)) != null) {
            addCwEntry(entry, strategy);
        }
    }
}
