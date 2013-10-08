package lab1;

import javaIn.JIn;

public class ProrgamLP {
	public static void main(String[] argv) {
		System.out.println("Podaj liczbę");
		LiczbyPierwsze pierw = new LiczbyPierwsze(JIn.getInteger());
		pierw.znajdz();
	}
}
