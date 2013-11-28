package browser;

import Exceptions.FailedToGenerateCrosswordException;
import Exceptions.FailedToLoadAllCwsException;
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

    private final File file; // folder

    /**
     * Constructor
     *
     * @param path - folder with crosswords
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
    public void getAllCws(Strategy easyStrategy, Strategy hardStrategy) throws IOException, FailedToLoadAllCwsException {
        LinkedList<Crossword> crosswords = new LinkedList<>();
        String message = "Failed to read create crossword from every file in directory. This files failed: \n";
        if (file.listFiles().length == 0) {
            throw new IOException("Directory is empty!");
        } else {
            for (File f : file.listFiles()) {
                if (f.isHidden()) {
                    message = message + f.getName() + " - File is hidden! \n";
                } else if (!f.canRead()) {
                    message = message + f.getName() + " - File is not readable! \n";
                } else if (f.isDirectory()) {
                    message = message + f.getName() + " - It's a directory! \n";
                } else {
                    try {
                        crosswords.add(new Crossword(Long.parseLong(f.getName()), f, easyStrategy, hardStrategy));
                    } catch (NumberFormatException a) {
                        message = message + f.getName() + " - Wrong file name or format! \n";
                    } catch (FailedToGenerateCrosswordException a) {
                        message = message + f.getName() + " - " + a.getMessage() + "\n";
                    }
                }
            }
        }
        message = message + "\nLoaded " + crosswords.size() + " crosswords.";
        throw new FailedToLoadAllCwsException(message, crosswords);
    }
}
