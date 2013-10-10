package lab1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;

/**
 * 
 * @author Szymon
 */
public class LoginProgram {
	/**
	 * @brief Main function which checks login and password
	 * @param argv
	 *            - input arguments
	 */
	public static void main(String[] argv) {
		Login log = new Login("ala", "makota");
		try {
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);

			// prosba o wpisanie hasła i loginu
			System.out.println("Podaj login:");
			String login = bfr.readLine();
			System.out.println("Podaj hasło:");
			String haslo = bfr.readLine();

			/*
			 * sprawdzenie czy podane hasło i login zgadzaja sie z tymi
			 * przechowywanymi w obiekcie log Jeśli tak, to ma zostać
			 * wyswietlone "OK", jesli nie - prosze wyswietlić informacje o
			 * błedzie
			 */
			if (log.check(login, haslo))
				System.out.println("OK");
			else
				System.out.println("Bledne dane");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/* TODO javadoc */
