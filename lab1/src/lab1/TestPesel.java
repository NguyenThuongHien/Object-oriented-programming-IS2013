package lab1;

import javaIn.JIn;

public class TestPesel {
	public static void main(String[] argv) {
		System.out.println("Podaj pesel");
		Pesel pesel = new Pesel(JIn.getString());
		if (pesel.checkNumbers())
			if (pesel.checkCheckNumber()) 
				if (!(pesel.checkData()))
					System.out.println("Bledna data");
				else
					System.out.println("OK");
			 else
				System.out.println("Blad cyfry kontrolnej");
		else
			System.out.println("Blednie wpisany pesel");
	}
}
