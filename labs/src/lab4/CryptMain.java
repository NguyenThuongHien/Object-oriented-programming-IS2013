/**
 * CryptMain.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javaIn.JIn;

/**
 * @author wukat
 * 
 */
public class CryptMain {
	/**
	 * Main program
	 * @param arg
	 */
	public static void main(String[] arg) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(
					arg[0])));
			FileWriter output = new FileWriter(new File(arg[1]));

			System.out.println("1. Szyfruj");
			System.out.println("2. Deszyfruj");
			int input1;
			while ((input1 = JIn.getInteger()) != 1 && input1 != 2)
				;
			switch (input1) {
			case 1:
				System.out.println("Jaki algorytm?");
				System.out.println("1. ROT11");
				System.out.println("2. Polibiusz");
				int input2;
				while ((input2 = JIn.getInteger()) != 1 && input2 != 2)
					;
				switch (input2) {
				case 1:
					Cryptographer.cryptfile(input, output, new ROT11());
					break;
				case 2:
					Cryptographer.cryptfile(input, output, new Polibiusz());
					break;
				}
				break;
			case 2:
				System.out.println("Jaki algorytm?");
				System.out.println("1. ROT11");
				System.out.println("2. Polibiusz");
				int input3;
				while ((input3 = JIn.getInteger()) != 1 && input3 != 2)
					;
				switch (input3) {
				case 1:
					Cryptographer.decryptfile(input, output, new ROT11());
					break;
				case 2:
					Cryptographer.decryptfile(input, output, new Polibiusz());
					break;
				}
				break;
			}
			input.close();
			output.close();
		} catch (IOException e) {
			System.out.println("Brak pliku");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Brak argumentów programu");
		} 
	}
}
