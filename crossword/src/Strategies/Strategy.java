package Strategies;

import Exceptions.FailedToGenerateCrosswordException;
import board.Board;
import board.Crossword;
import dictionary.CwEntry;

/**
 * @author wukat
 *
 */
public abstract class Strategy {
    public static final int hardStrategyID = 1;
    public static final int easyStrategyID = 0;
    
    /**
     * Finds entry that can be put into the crossword
     *
     * @param crossword - input crossword
     * @throws FailedToGenerateCrosswordException
     * @return crossword entry or null if there's no matching
     */
    public abstract CwEntry findEntry(Crossword crossword) throws FailedToGenerateCrosswordException;

    /**
     * Updates board, inserts crossword entry to board and changes abilities of cells
     *
     * @param board - board to update
     * @param entry - value to insert
     */
    public abstract void updateBoard(Board board, CwEntry entry);
}
