package graphicalInterface;

import board.Crossword;
import board.Strategy;
import dictionary.CwEntry;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Printable class extending JPanel - used to draw crossword on it and print it.
 */
public class DrawingPanel extends JPanel implements Printable {

    Crossword actual = null; // actual crossword showed on panel

    /**
     * Sets actual crossword to paint/print/solve&paint
     *
     * @param actual - crossword to set
     */
    public void setActual(Crossword actual) {
        this.actual = actual;
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        if (actual != null) {
            paintCw(graphic, 0);
            int j = 0;
            int maxWidth = actual.getBoardWidth() * 30 + 45;
            for (String entry : actual.printAllEntries().split("\n")) {
                graphic.drawString(entry, 10, actual.getBoardHeight() * 30 + 50 + j * 15);
                if (maxWidth < entry.length() * 7) {
                    maxWidth = entry.length() * 7;
                }
                j++;
            }
            if (this.getSize().width != maxWidth
                    || this.getSize().height != j * 16
                    + actual.getBoardHeight() * 30
                    + 18) {
                this.setPreferredSize(new Dimension(maxWidth, j * 16
                        + actual.getBoardHeight() * 30 + 38));
            }
        }
    }

    @Override
    public int print(Graphics graphic, PageFormat pf, int page)
            throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        int constX = 70;
        int constY = 90;

        graphic.drawString("Crossword", constX, constY);
        int j = 1;
        if (actual.getStrategyID() == Strategy.easyStrategyID) {
            while (j <= actual.getBoardHeight()) {
                graphic.drawString(j + ". ", 10,
                        50 + (j - 1) * 30);
                j++;
            }
        } else {
            Iterator<CwEntry> iter = actual.getROEntryIter();
            int k = 1;
            int l = 1;
            while (iter.hasNext()) {
                CwEntry entry = iter.next();
                if (entry.getDir() == CwEntry.Direction.VERT) {
                    graphic.drawString(k + ". ", entry.getX() * 30 + 45,
                            20 + entry.getY() * 30);
                    k++;
                } else {
                    graphic.drawString(l + ". ", entry.getX() * 30 + 15,
                            50 + entry.getY() * 30);
                    l++;
                }
            }
        }
        for (int i = 0; i < actual.getBoardWidth(); i++) {
            for (j = 1; j <= actual.getBoardHeight(); j++) {
                if (actual.checkBoardCell(i, j - 1)) {
                    graphic.drawRect(35 + i * 30 + constX, 30 + (j - 1) * 30 + constY,
                            30, 30);
                }
            }
        }
        j = 0;
        for (String entry : actual.printAllEntries().split("\n")) {
            int temp = j;
            while (entry.length() - (temp - j) * 70 > 70) {
                graphic.drawChars(entry.toCharArray(), (temp - j) * 70, 70, 10 + constX, actual.getBoardHeight() * 30 + 50 + j * 15 + constY);
                j++;
                temp += 2;
            }
            graphic.drawChars(entry.toCharArray(), (temp - j) * 70, entry.length() - (temp - j) * 70, 10 + constX, actual.getBoardHeight() * 30 + 50 + j * 15 + constY);
            j++;
            temp += 2;
        }

        return PAGE_EXISTS;
    }

    /**
     * Paints text fields on the screen
     *
     * @param graphic
     */
    public void paintSolveable(Graphics graphic) {
        graphic.setColor(Color.BLACK);
        for (int i = 0; i < actual.getBoardWidth(); i++) {
            for (Integer j = 1; j <= actual
                    .getBoardHeight(); j++) {
                if (actual.checkBoardCell(i, j - 1)) {
                    final JTextField a = new JTextField(1);
                    a.setBounds(i * 30 + 36, (j - 1) * 30 + 30, 30, 30);
                    a.setHorizontalAlignment(JTextField.CENTER);
                    this.add(a);
                }
            }
        }
        this.repaint();
    }

    /**
     * Function paints only cw, without text
     *
     * @param graphic
     * @param constXY - shift in X and Y.
     */
    public void paintCw(Graphics graphic, int constXY) {
        graphic.setColor(Color.BLACK);
        int j = 1;
        if (actual.getStrategyID() == Strategy.easyStrategyID) {
            while (j <= actual.getBoardHeight()) {
                graphic.drawString(j + ". ", 10 + constXY,
                        50 + (j - 1) * 30 + constXY);
                j++;
            }
        } else {
            Iterator<CwEntry> iter = actual.getROEntryIter();
            int k = 1;
            int l = 1;
            while (iter.hasNext()) {
                CwEntry entry = iter.next();
                if (entry.getDir() == CwEntry.Direction.VERT) {
                    graphic.drawString(k + ". ", entry.getX() * 30 + 45 + constXY,
                            20 + entry.getY() * 30 + constXY);
                    k++;
                } else {
                    graphic.drawString(l + ". ", entry.getX() * 30 + 15 + constXY,
                            50 + entry.getY() * 30 + constXY);
                    l++;
                }
            }
        }
        for (int i = 0; i < actual.getBoardWidth(); i++) {
            for (j = 1; j <= actual.getBoardHeight(); j++) {
                if (actual.checkBoardCell(i, j - 1)) {
                    graphic.drawRect(35 + i * 30 + constXY, 30 + (j - 1) * 30 + constXY,
                            30, 30);
                }
            }
        }
    }
}
