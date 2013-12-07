package graphicalInterface;

import board.Board;
import board.Crossword;
import Strategies.Strategy;
import dictionary.CwEntry;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.ParseException;
import java.util.Iterator;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 * Printable class extending JPanel - used to draw crossword on it and print it.
 */
public class DrawingPanel extends JPanel implements Printable {

    Crossword actual = null; // actual crossword showed on panel
    JFormattedTextField[][] textBoard = null; // text fields shown on board

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
            int j = 0;
            int maxWidth = actual.getBoardWidth() * 30 + 45;
            graphic.setColor(Color.black);
            for (String entry : actual.printAllEntries().split("\n")) {
                graphic.drawString(entry, 10, actual.getBoardHeight() * 30 + 50 + j * 15);
                if (maxWidth < entry.length() * 7) {
                    maxWidth = entry.length() * 7;
                }
                j++;
            }
            if (this.getPreferredSize().width >= maxWidth && this.getPreferredSize().height >= j * 16
                    + actual.getBoardHeight() * 30
                    + 38) {
                this.revalidate();
                this.setPreferredSize(new Dimension(maxWidth, j * 16
                        + actual.getBoardHeight() * 30 + 38));
                this.paintCw(graphic, 0);
            } else if (this.getSize().width != maxWidth
                    || this.getSize().height != j * 16
                    + actual.getBoardHeight() * 30
                    + 38) {
                this.setPreferredSize(new Dimension(maxWidth, j * 16
                        + actual.getBoardHeight() * 30 + 38));
                this.revalidate();
                this.paintCw(graphic, 0);
                this.repaint();
            }
        }
    }

    @Override
    public int print(Graphics graphic, PageFormat pf, int page)
            throws PrinterException {

        if (page > 1) {
            return NO_SUCH_PAGE;
        }

        int constX = 70;
        int constY = 90;

        if (page == 0) {
            paintCw(graphic, 60);
        } else {
            int j = 0;
            for (String entry : actual.printAllEntries().split("\n")) {
                int temp = j;
                while (entry.length() - (temp - j) * 70 > 70) {
                    graphic.drawChars(entry.toCharArray(), (temp - j) * 70, 70, 10 + constX, constY + j * 15);
                    j++;
                    temp += 2;
                }
                graphic.drawChars(entry.toCharArray(), (temp - j) * 70, entry.length() - (temp - j) * 70, 10 + constX, constY + j * 15);
                j++;
                temp += 2;
            }
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
        textBoard = new JFormattedTextField[actual.getBoardWidth()][actual.getBoardHeight()];
        for (int i = 0; i < actual.getBoardWidth(); i++) {
            for (Integer j = 1; j <= actual
                    .getBoardHeight(); j++) {
                if (actual.checkBoardCell(i, j - 1)) {
                    try {
                        textBoard[i][j - 1] = new JFormattedTextField(new MaskFormatter("U"));
                        textBoard[i][j - 1].setBounds(i * 30 + 36 + 1, (j - 1) * 30 + 30 + 1, 28, 28);
                        textBoard[i][j - 1].setHorizontalAlignment(JTextField.CENTER);
                        this.add(textBoard[i][j - 1]);
                    } catch (ParseException e) {
                    }
                }
            }
        }
    }

    /**
     * Paints solved crossword, green color when the field is filled properly,
     * red otherwise.
     *
     * @param graphic
     */
    public void paintSolved(Graphics graphic) {
        graphic.setColor(Color.BLACK);
        Board tempBoard = actual.getBoardCopy();
        for (int i = 0; i < actual.getBoardWidth(); i++) {
            for (Integer j = 1; j <= actual
                    .getBoardHeight(); j++) {
                if (tempBoard.getCell(i, j - 1).checkContent()) {
                    String temp = tempBoard.getCell(i, j - 1).getContent();
                    if (temp.equals(textBoard[i][j - 1].getText())) {
                        textBoard[i][j - 1].setForeground(Color.green);
                    } else {
                        textBoard[i][j - 1].setForeground(Color.red);
                    }
                    textBoard[i][j - 1].setText(temp);
                    this.add(textBoard[i][j - 1]);
                }
            }
        }
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
                    paintCell(graphic, i, j - 1, constXY);
                }
            }
        }
    }

    /**
     * Paints single cell
     *
     * @param graphic
     * @param i - position
     * @param j - position
     * @param constXY - shift
     */
    private void paintCell(Graphics graphic, int i, int j, int constXY) {
        graphic.setColor(Color.black);
        graphic.drawRect(35 + i * 30 + constXY, 30 + j * 30 + constXY,
                30, 30);
        graphic.setColor(Color.gray);
        graphic.drawRect(35 + i * 30 + constXY + 2, 30 + j * 30 + constXY + 2,
                26, 26);
    }
}
