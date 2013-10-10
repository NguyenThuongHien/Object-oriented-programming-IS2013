package lab1;

import javaIn.JIn;

/**
 * @brief Class with functionality of finding prim's numbers
 * @author wukat
 * 
 */
public class ProrgamLP {
	/**
	 * @brief Main function
	 * @param argv
	 *            program input arguments
	 */
	public static void main(String[] argv) {
		System.out.println("Podaj liczbę");
		LiczbyPierwsze pierw = new LiczbyPierwsze(JIn.getInteger());
		pierw.znajdz();
	}
}
