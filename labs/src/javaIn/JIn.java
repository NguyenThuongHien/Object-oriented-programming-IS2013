package javaIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JIn {
	public static String getString() {
		String text = null;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);

			text = bfr.readLine();
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static int getInteger() {
		int a = 0;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			
			a = Integer.parseInt(bfr.readLine());
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("To nie liczba! Wpisz ponownie!");
			a = getInteger();
		}
		return a;
	}
	
	public static double getDouble() {
		double a = 0.0;
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			
			a = Double.parseDouble(bfr.readLine());
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("To nie liczba! Wpisz ponownie!");
			a = getDouble();
		}
		return a;
	}
}