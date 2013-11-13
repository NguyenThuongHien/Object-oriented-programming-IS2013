/**
 * microDVDmain.java
 * @author - wukat
 * @data - 11 lis 2013
 */
package lab5;

import java.io.IOException;

/**
 * @author wukat
 * 
 */
public class MicroDVDmain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MicroDVDDelayer.microDVDDelayer(args[0], args[1], Integer.parseInt(args[2]),
					Integer.parseInt(args[3]));
		} catch (IndexOutOfBoundsException a) {
			System.out.println("Zbyt mało argumentów programu.");
		} catch (NumberFormatException a) {
			System.out
					.println("Błędne argumenty (3 i 4 powinny być liczbami).");
		} catch (IOException a) {
			System.out.println("Błąd wejścia/wyjścia.");
		} catch (UnproperDataException a) {
			System.out.println("Błąd w pliku.");
		}

	}

}
