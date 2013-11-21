/**
 * CwWriter.java
 *
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import board.Crossword;
import dictionary.CwEntry;

import java.io.File;
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
            cwFile.write(crossword.getBoardWidth() + " "
                    + crossword.getBoardHeight() + "\n");
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

}
