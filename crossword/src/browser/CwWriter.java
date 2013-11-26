/**
 * CwWriter.java
 *
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import board.Crossword;
import board.Strategy;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import dictionary.CwEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * @author wukat
 *
 */
public class CwWriter implements Writer {

    private final File file; // folder

    /**
     *
     * Constructor
     *
     * @param path - path to destination folder
     * @throws java.io.IOException
     */
    public CwWriter(String path) throws IOException {
        file = new File(path);
        file.mkdirs();
        if (!file.isDirectory() || !file.canWrite()) {
            throw new IOException();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see browser.Writer#write(board.Crossword)
     */
    @Override
    public void write(Crossword crossword) throws IOException {
        Long ID = getUniqueID();
        try (FileWriter cwFile = new FileWriter(file.getAbsolutePath() + "/"
                + Long.toString(ID))) {
            if (crossword.getStrategyID() == 0) {
                cwFile.write("EASY\n");
            } else {
                cwFile.write("HARD\n");
            }
            cwFile.write(crossword.getBoardCopy().getWidth() + " "
                    + crossword.getBoardCopy().getHeight() + "\n");
            Iterator<CwEntry> iter = crossword.getROEntryIter();
            while (iter.hasNext()) {
                cwFile.write(iter.next().toString());
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see browser.Writer#getUniqueID()
     */
    @Override
    public Long getUniqueID() {
        return new Long(new Date().getTime());
    }

    public void createCrossowrdPDF(Crossword crossword) throws DocumentException, IOException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath() + "/" + getUniqueID().toString() + ".pdf"));
        document.open();
        document.add(new Paragraph("Crossword"));

        PdfContentByte cb = writer.getDirectContent();
        cb.setColorStroke(GrayColor.BLACK);
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED), 12);
        cb.setColorFill(BaseColor.BLACK);
        cb.saveState();

        if (crossword.getStrategyID() == Strategy.easyStrategyID) {
            int j = 1;
            while (j <= crossword.getBoardHeight()) {
                cb.moveTo(30, 775 - 20 * j);
                cb.showText(j + ". ");
                j++;
            }
        } else {
            Iterator<CwEntry> iter = crossword.getROEntryIter();
            int k = 1;
            int l = 1;
            while (iter.hasNext()) {
                CwEntry entry = iter.next();
                if (entry.getDir() == CwEntry.Direction.VERT) {
                    cb.moveTo(entry.getX() * 20 + 55, 775 - 20 * entry.getY());
                    cb.showText(k + ". ");
                    k++;
                } else {
                    cb.moveTo(entry.getX() * 20 + 35, 755 - 20 * entry.getY());
                    cb.showText(l + ". ");
                    l++;
                }
            }
        }
        for (int i = 0; i < crossword.getBoardWidth(); i++) {
            for (Integer j = 1; j <= crossword.getBoardHeight(); j++) {
                cb.setColorFill(GrayColor.WHITE);
                if (crossword.checkBoardCell(i, j - 1)) {
                    cb.rectangle(50 + i * 20, 840 - 20 * j - 70,
                            20, 20);
                    cb.fillStroke();
                }
            }
        }

        cb.setColorFill(BaseColor.BLACK);
        int j = 0;
        for (String entry : crossword.printAllEntries().split("\n")) {
            cb.moveTo(30, 750 - crossword.getBoardHeight() * 20 - j * 15);
            cb.showText(entry);
            j++;
        }
        cb.restoreState();
        document.close();
    }
}
