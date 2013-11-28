package browser;

import Exceptions.FailedToGenerateCrosswordException;
import Exceptions.FailedToLoadAllCwsException;
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
    private IntelLiCwDB defaultCwDB; // crossword database
    private final EasyStrategy easyStrategy;
    private final HardStrategy hardStrategy;
    private final DrawingPanel drawingPane; // extension of JPanel, prints and paints crossword
    private Boolean lastUsedNextButton; // flag

    /**
     * Constructor
     *
     * @param cwDBpath - path to database
     * @throws IOException if path is wrong
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

    /**
     * Generates crossword (actual)
     *
     * @param width - board's width
     * @param height - board's height
     * @param strategyID - 0 or 1, depends on strategy kind
     * @throws FailedToGenerateCrosswordException
     */
    public void generateCw(int width, int height, int strategyID)
            throws FailedToGenerateCrosswordException {
        Crossword temp = new Crossword(width, height, defaultCwDB, strategyID);
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
     * Next crossword - sets actual next crossword on list
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
     * @return true if there is next element in the list
     */
    public boolean hasNext() {
        return iter.hasNext();
    }

    /**
     * Previous crossword - sets actual previous crossword on list
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
     * @return true if there is previous element in the list
     */
    public boolean hasPrevious() {
        return iter.hasPrevious();
    }

    /**
     * Checks if browser has actual crossword
     *
     * @return true if have, false if actual is null
     */
    public boolean hasActual() {
        return actual != null;
    }

    /**
     * Next index iterator
     *
     * @return index of next element
     */
    public int nextIndex() {
        return iter.nextIndex();
    }

    /**
     * Previous element index
     *
     * @return index of previous element
     */
    public int previousIndex() {
        return iter.previousIndex();
    }

    /**
     * Amount of crossword
     *
     * @return size of list - number of elements (generated and loaded
     * crosswords)
     */
    public int getAmountOfCrosswords() {
        return crosswords.size();
    }

    /**
     * Checks flag
     *
     * @return value of flag, true if last pressed button (choice of next/prev)
     * was next
     */
    public Boolean isLastUsedNextButton() {
        return lastUsedNextButton;
    }

    /**
     * Getter
     *
     * @return easyStrategy object
     */
    public EasyStrategy getEasyStrategy() {
        return easyStrategy;
    }

    /**
     * Getter
     *
     * @return hardStrategy object
     */
    public HardStrategy getHardStrategy() {
        return hardStrategy;
    }

    /**
     * Getter
     *
     * @return drawing panel
     */
    public DrawingPanel getDrawingPane() {
        return drawingPane;
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
     * Getter
     *
     * @return actual crossword
     */
    public Crossword getActual() {
        return actual;
    }

    /**
     * Saves actual crossword in file in given direcotry
     *
     * @param folderPath - directory to save crossword in
     * @throws IOException
     */
    public void saveActual(String folderPath) throws IOException,
            NullPointerException {
        new CwWriter(folderPath).write(actual);
    }

    /**
     * Creates PDF with crossword in given directory
     *
     * @param folderPath - directory to save PDF in
     * @throws DocumentException
     * @throws IOException
     */
    public void toPDF(String folderPath) throws DocumentException, IOException {
        new CwWriter(folderPath).createCrossowrdPDF(actual, drawingPane);
    }

    /**
     * Loads crosswords from given folder
     *
     * @param folderPath
     * @throws IOException
     * @throws Exceptions.FailedToLoadAllCwsException
     */
    public void loadFromFiles(String folderPath) throws IOException, FailedToLoadAllCwsException {
        CwReader reader = new CwReader(folderPath);
        String message = null;
        try {
            reader.getAllCws(easyStrategy, hardStrategy);
        } catch (FailedToLoadAllCwsException e) {
            crosswords.addAll(e.getCrosswords());
            iter = crosswords.listIterator();
            while (iter.hasNext()) {
                actual = iter.next();
            }
            paintCrossowrd();
            message = e.getMessage();
        }
        if (message != null) {
            throw new FailedToLoadAllCwsException(message);
        }
    }

    /**
     * Function shows actual crossword on the screen.
     */
    public void paintCrossowrd() {
        drawingPane.setActual(actual);
        drawingPane.removeAll();
        drawingPane.paint(drawingPane.getGraphics());
        drawingPane.revalidate();
        drawingPane.repaint();
    }

    /**
     * Function paints crossword with text fields
     */
    public void paintSolveable() {
        drawingPane.paintSolveable(drawingPane.getGraphics());
    }

    /**
     * Function prints crossword
     *
     * @throws PrinterException
     */
    public void print() throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((Printable) drawingPane);
        if (job.printDialog()) {
            job.print();
        }
    }
}
