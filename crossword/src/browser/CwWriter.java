package browser;

import board.Crossword;
import board.Strategy;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import dictionary.CwEntry;
import graphicalInterface.DrawingPanel;
import java.awt.Graphics2D;

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

    private final File file; // directory

    /**
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

    /**
     * Creates PDF with given crossword
     *
     * @param crossword
     * @param panel - panel which shows crossword on the screen
     * @throws DocumentException
     * @throws IOException
     */
    public void createCrossowrdPDF(Crossword crossword, DrawingPanel panel) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        System.setProperty("file.encoding", "Cp852");
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath() + "/" + getUniqueID().toString() + ".pdf"));

        document.open();
        document.add(new Paragraph("Crossword"));
        PdfContentByte cb = writer.getDirectContent();
        PdfTemplate temp = cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        Graphics2D graphic = new PdfGraphics2D(cb, PageSize.A4.getWidth(), PageSize.A4.getHeight());

        int j = 1;
        int constXY = 50;
        if (crossword.getStrategyID() == Strategy.easyStrategyID) {
            while (j <= crossword.getBoardHeight()) {
                graphic.drawString(j + ". ", 10 + constXY,
                        50 + (j - 1) * 30 + constXY);
                j++;
            }
        } else {
            Iterator<CwEntry> iter = crossword.getROEntryIter();
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
        for (int i = 0; i < crossword.getBoardWidth(); i++) {
            for (j = 1; j <= crossword.getBoardHeight(); j++) {
                if (crossword.checkBoardCell(i, j - 1)) {
                    graphic.drawRect(35 + i * 30 + constXY, 30 + (j - 1) * 30 + constXY,
                            30, 30);
                }
            }
        }
        graphic.dispose();
        cb.addTemplate(temp, 0, 0);

        BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 15, Font.NORMAL);
        Paragraph pageParagraph = new Paragraph();
        for (j = 1; j <= crossword.getBoardHeight(); j++) {
            pageParagraph.add(new Chunk("\n").setLineHeight(30));
        }
        pageParagraph.add(new Chunk("\n\n", font).setLineHeight(15));
        for (String entry : crossword.printAllEntries().split("\n")) {
            pageParagraph.add(new Chunk(entry + "\n", font).setLineHeight(15));
        }
        document.add(pageParagraph);
        document.close();
    }
}
