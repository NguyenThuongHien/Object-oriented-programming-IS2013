/**
 * CwBrower.java
 *
 * @author - wukat
 * @data - 2 lis 2013
 */
package browser;

import Exceptions.FailedToGenerateCrosswordException;
import Strategies.EasyStrategy;
import Strategies.HardStrategy;
import board.Crossword;
import board.Strategy;
import com.itextpdf.text.DocumentException;
import dictionary.IntelLiCwDB;
import graphicalInterface.DrawingPanel;
import java.awt.Dimension;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author wukat
 *
 */
public class CwBrowser {

    private final LinkedList<Crossword> crosswords; // vector of read crosswords
    private ListIterator<Crossword> iter; // vector iterator
    private Crossword actual; // actual crossword
    private IntelLiCwDB defaultCwDB;
    private final EasyStrategy easyStrategy;
    private final HardStrategy hardStrategy;
    private final DrawingPanel drawingPane;
    private Boolean lastUsedNextButton;

    public Boolean isLastUsedNextButton() {
        return lastUsedNextButton;
    }

    public EasyStrategy getEasyStrategy() {
        return easyStrategy;
    }

    public HardStrategy getHardStrategy() {
        return hardStrategy;
    }

    /**
     * Setter
     *
     * @param defaultCwDB
     */
    public void setDefaultCwDB(IntelLiCwDB defaultCwDB) {
        this.defaultCwDB = defaultCwDB;
    }

    /**
     * getter
     *
     * @return actual crossword
     */
    public Crossword getActual() {
        return actual;
    }

    /**
     *
     * Constructor
     *
     * @param cwDBpath - path to database
     * @throws IOException
     */
    public CwBrowser(String cwDBpath) throws IOException {
        if (cwDBpath == null) {
            cwDBpath = "cwdb.txt";
        }
        defaultCwDB = new IntelLiCwDB(cwDBpath);
        crosswords = new LinkedList<>();
        iter = crosswords.listIterator();
        actual = null;
        easyStrategy = new EasyStrategy();
        hardStrategy = new HardStrategy();
        drawingPane = new DrawingPanel();
        drawingPane.setPreferredSize(new Dimension(0, 0));
        lastUsedNextButton = Boolean.TRUE;
    }

    public DrawingPanel getDrawingPane() {
        return drawingPane;
    }

    /**
     * Generates crossword (actual)
     *
     * @param width - board's width
     * @param height - board's height
     * @param strategyID
     * @throws FailedToGenerateCrosswordException
     */
    public void generateCw(int width, int height, int strategyID)
            throws FailedToGenerateCrosswordException {
        Crossword temp = new Crossword(width, height, defaultCwDB);
        temp.setStrategyID(strategyID);
        if (temp.getStrategyID() == Strategy.easyStrategyID) {
            temp.generate(easyStrategy);
        } else {
            temp.generate(hardStrategy);
        }
        actual = temp;
        crosswords.add(actual);
        iter = crosswords.listIterator();
        while (iter.hasNext()) {
            iter.next();
        }
        paintCrossowrd();
    }

    /**
     * Next crossword
     */
    public void next() {
        if (lastUsedNextButton) {
            if (iter.hasNext()) {
                actual = iter.next();
            }
        } else {
            actual = iter.next();
            actual = iter.next();
        }
        lastUsedNextButton = Boolean.TRUE;
        paintCrossowrd();
    }

    /**
     * Iter.hasNext()
     *
     * @return
     */
    public boolean hasNext() {
        return iter.hasNext();
    }

    /**
     * Previous crossword
     */
    public void previous() {
        if (lastUsedNextButton) {
            actual = iter.previous();
            actual = iter.previous();
        } else if (iter.hasPrevious()) {
            actual = iter.previous();
        }
        lastUsedNextButton = Boolean.FALSE;
        paintCrossowrd();
    }

    /**
     * iter.hasPrevious()
     *
     * @return
     */
    public boolean hasPrevious() {
        return iter.hasPrevious();
    }

    /**
     * Checks if browser has actual crossword
     *
     * @return logical value
     */
    public boolean hasActual() {
        return actual != null;
    }

    /**
     * Index
     *
     * @return
     */
    public int getIndexOfIterator() {
        return iter.nextIndex();
    }

    /**
     * Next index iterator
     *
     * @return
     */
    public int nextIndex() {
        return iter.nextIndex();
    }

    /**
     * Previous element index
     *
     * @return
     */
    public int previousIndex() {
        return iter.previousIndex();
    }

    /**
     * Amount of crossword
     *
     * @return
     */
    public int getAmountOfCrosswords() {
        return crosswords.size();
    }

    /**
     * Saves actual crossword in file
     *
     * @param folderPath
     * @throws IOException
     */
    public void saveActual(String folderPath) throws IOException,
            NullPointerException {
        new CwWriter(folderPath).write(actual);
    }

    public void toPDF(String folderPath) throws DocumentException, IOException {
        new CwWriter(folderPath).createCrossowrdPDF(actual);
    }

    /**
     * Loads crosswords from given folder
     *
     * @param folderPath
     * @throws IOException
     */
    public void loadFromFiles(String folderPath) throws IOException, NullPointerException {
        CwReader reader = new CwReader(folderPath);
        crosswords.addAll(reader.getAllCws(easyStrategy, hardStrategy));
        iter = crosswords.listIterator();
        while (iter.hasNext()) {
            actual = iter.next();
        }
        paintCrossowrd();
    }

    /**
     * Function shows actual crossword on the screen.
     */
    public void paintCrossowrd() {
        drawingPane.setActual(actual);
        drawingPane.paint(drawingPane.getGraphics());
        drawingPane.revalidate();
        drawingPane.repaint();
    }

    public void paintSolved() {
        drawingPane.paintSolved(drawingPane.getGraphics());
    }

    public void print() throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((Printable) drawingPane);
        if (job.printDialog()) {
            job.print();
        }
    }
}
