/**
 * MicroDVDDelayer.java
 * @author - wukat
 * @data - 11 lis 2013
 */
package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

/**
 * @author wukat
 * 
 */
public class MicroDVDDelayer {

	private BufferedReader input;
	private FileWriter output;
	private Integer delay;
	private Integer fps;

	/**
	 * Constructor
	 * 
	 * @param inputFilePath
	 * @param outputFilePath
	 * @param delay
	 * @param fps
	 * @throws IOException
	 * @throws UnproperDataException
	 */
	public MicroDVDDelayer(String inputFilePath, String outputFilePath,
			int delay, int fps) throws IOException, UnproperDataException {
		try (BufferedReader inputBuf = new BufferedReader(new FileReader(
				new File(inputFilePath)))) {
			this.input = inputBuf;
			try (FileWriter outputWriter = new FileWriter(new File(
					outputFilePath))) {
				this.output = outputWriter;
				this.delay = delay;
				this.fps = fps;
				String temp;
				while ((temp = input.readLine()) != null) {
					output.write(delay(temp));
				}
			}
		}
	}

	/**
	 * Delay function
	 * @param in - string
	 * @throws UnproperDataException
	 * @return delayed string
	 * 
	 */
	public String delay(String in) throws UnproperDataException {
		String[] temp = in.split("}");
		Integer begin, end;
		if ((Pattern.matches(".[[0-9]]*", temp[0]))
				&& (Pattern.matches(".[[0-9]]*", temp[1]))) {
			temp[0] = temp[0].substring(1);
			temp[1] = temp[1].substring(1);
			begin = Integer.parseInt(temp[0]);
			end = Integer.parseInt(temp[1]);
			if (begin >= end) {
				throw new UnproperDataException();
			}
			begin += fps * delay / 1000;
			end += fps * delay / 1000;
			return "{" + begin.toString() + "}{" + end.toString() + "}"
					+ temp[2] + "\n";
		}
        else
		    throw new UnproperDataException();
	}
}
