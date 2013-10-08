package lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
	public static void main(String[] argv) {
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(rd);

		System.out.print("Wpisz jakis tekst: ");
		String text = null;
		try {
			text = bfr.readLine();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wpisales: " + text);
	}
}

// paczka

// wyhaczenie wyjątków
