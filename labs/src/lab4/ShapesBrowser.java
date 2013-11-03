/**
 * ShapesBrowser.java
 * @author - wukat
 * @data - 3 lis 2013
 */
package lab4;

import java.util.LinkedList;
import java.util.ListIterator;

import javaIn.JIn;
import lab3.Kolo;
import lab3.Kwadrat;
import lab3.Shape;
import lab3.Trojkat;

/**
 * @author wukat
 * 
 */
public class ShapesBrowser {
	public static LinkedList<Shape> shapes = new LinkedList<Shape>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListIterator<Shape> iter = shapes.listIterator();
		boolean flag = false;
		while ((!flag)) {
			System.out.println("1. Dodaj");
			System.out.println("2. Nastepny");
			System.out.println("3. Poprzedni");
			System.out.println("4. Wyswietl wszystkie");
			System.out.println("5. Wyjdz");
			int input = JIn.getInteger();
			switch (input) {
			case 1:
				addShape();
				iter = shapes.listIterator();
				break;
			case 2:
				if (iter.hasNext()) {
					iter.next().draw();
				} else
					System.out.println("Brak nastepnego elementu");
				break;
			case 3:
				if (iter.hasPrevious())
					iter.previous().draw();
				else
					System.out.println("Brak poprzedniego elementu");
				break;
			case 4:
				iter = shapes.listIterator();
				while (iter.hasNext())
					iter.next().draw();
				break;
			case 5:
				flag = true;
			}
		}
	}

	public static void addShape() {
		System.out.println("Dodaj:");
		System.out.println("1. Kwadrat");
		System.out.println("2. Kolo");
		System.out.println("3. Trojkat");
		System.out.println("Inna liczba: anuluj");
		int input = JIn.getInteger();
		switch (input) {
		case 1:
			shapes.add(new Kwadrat());
			break;
		case 2:
			shapes.add(new Kolo());
			break;
		case 3:
			shapes.add(new Trojkat());
			break;
		}
	}

}
