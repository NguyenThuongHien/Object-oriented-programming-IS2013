/**
 * CwReader.java
 * @author - wukat
 * @data - 31 paź 2013
 */
package browser;

import board.Crossword;
import board.Strategy;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
public class CwReader implements Reader {

    private File file; // folder

    /**
     * 
     * Constructor
     * 
     * @param path
     *            - folder with crosswords
     * @throws IOException
     */
    public CwReader(String path) throws IOException {
        file = new File(path);
        if (!file.isDirectory() || !file.canRead() || !file.canExecute()) {
            throw new IOException();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see browser.Reader#getAllCws()
     */
    @Override
    public LinkedList<Crossword> getAllCws(Strategy easyStrategy, Strategy hardStrategy) throws IOException, NullPointerException {
        LinkedList<Crossword> crosswords = new LinkedList<>();
        for (File f : file.listFiles()) {
            if (f.canRead() && f.isFile()) {
                crosswords.add(new Crossword(Long.parseLong(f.getName()), f, easyStrategy, hardStrategy));
            }
        }
        return crosswords;
    }
}
