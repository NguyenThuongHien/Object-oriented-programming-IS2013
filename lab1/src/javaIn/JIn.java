package javaIn;

import java.io.*;

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
		}
		return a;
	}
}