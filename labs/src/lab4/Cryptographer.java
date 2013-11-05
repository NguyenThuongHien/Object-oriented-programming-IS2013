/**
 * Cryptographer.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wukat
 * 
 */
public class Cryptographer {

	/**
	 * Codes the file into second with given algorithm
	 * 
	 * @param in
	 *            - input reader
	 * @param out
	 *            - output writer
	 * @param doCrypt
	 *            - algorithm
	 * @throws IOException
	 */
	public static void cryptfile(BufferedReader in, FileWriter out,
			Algorythm doCrypt) throws IOException {
		String temp;
		while ((temp = in.readLine()) != null)
			for (String i : temp.split(" ")) {
				out.write(doCrypt.crypt(i));
				out.write(" ");
			}
	}

	/**
	 * Decodes the file.
	 * 
	 * @param in
	 *            - input reader
	 * @param out
	 *            - output writer
	 * @param doDecrypt
	 *            - decoding algorithm
	 * @throws IOException
	 */
	public static void decryptfile(BufferedReader in, FileWriter out,
			Algorythm doDecrypt) throws IOException {
		String temp;
		while ((temp = in.readLine()) != null)
			for (String i : temp.split(" ")) {
				out.write(doDecrypt.decrypt(i));
				out.write(" ");
			}
	}
}
