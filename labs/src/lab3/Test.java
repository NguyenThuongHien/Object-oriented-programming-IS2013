/**
 * Test.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package lab3;

import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
public class Test {
	public static PracowityStudent pierwsza = new PracowityStudent();

	public static void main(String[] args) {
		Pracownik druga = pierwsza.rzutujNaPracownika();
		druga.dodajZajecia();
		pierwsza.dodajZajecia();
		System.out.println(pierwsza); //ilość zajęć w studencie
		System.out.println(druga);
		LinkedList<Shape> Shapes = new LinkedList<Shape>();
		Shapes.add(new Kwadrat());
		Shapes.add(new Kolo());
		Shapes.add(new Trojkat());
//		for (java.util.ListIterator<Shape> iter = Shapes.listIterator(); iter.hasNext();)
//			iter.next().draw();
		
	}
}
