/**
 * TestPoints.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package lab2;

import java.util.LinkedList;
import javaIn.JIn;

/**
 * @author wukat
 * 
 */
public class TestPoints {
	/**
	 * points - list of 3d points
	 */
	private static LinkedList<Point3D> points = new LinkedList<Point3D>();

	/**
	 * Main function, with menu to operate on points.
	 * 
	 * @param args
	 *            - input program parameters
	 */
	public static void main(String[] args) {
		int temp = menu();
		while (temp != 4) {
			switch (temp) {
			case 1:
				System.out.println("Podaj 3 liczby double (enter po każdej):");
				points.add(new Point3D(JIn.getDouble(), JIn.getDouble(), JIn
						.getDouble()));
				break;
			case 2:
				if (points.size() > 0)
					for (int i = 0; i < points.size(); i++) {
						System.out.print(i + ". ");
						points.get(i).printPoint3D();
					}
				else
					System.out.print("Brak punktów do wyswietlenia!");
				System.out.print("\n");
				break;
			case 3:
				if (points.size() > 1) {
					System.out.println("Podaj dwie liczby (int) od 1 do "
							+ (points.size()));
					int i = JIn.getInteger();
					int j = JIn.getInteger();
					if ((i > 0) && (i <= points.size()) && (j > 0)
							&& (j <= points.size()))
						System.out
								.println("Odleglosc wynosi "
										+ points.get(i - 1).distance(
												points.get(j - 1)));
					else
						System.out.println("Bledny indeks!");
				} else
					System.out.println("Dodaj punkty!");
				break;
			default:
				System.out.println("Brak takiej opcji, wybierz ponownie.");
				break;
			}
			temp = menu();
		}
	}

	/**
	 * Menu
	 * 
	 * @return integer
	 */
	private static int menu() {
		System.out.println("1. Wczytaj punkt 3D");
		System.out.println("2. Wyświetl wszystkie punkty");
		System.out.println("3. Oblicz odległość");
		System.out.println("4. Zakończ");
		System.out.println("?");

		return JIn.getInteger();
	}
}