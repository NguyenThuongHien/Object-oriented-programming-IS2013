package javaIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class JIn {
	public static String getString() {
		String text = null;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);

			text = bfr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static Integer getInteger() {
		int a = 0;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			
			a = Integer.parseInt(bfr.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("To nie liczba!");
		}
		return a;
	}
}