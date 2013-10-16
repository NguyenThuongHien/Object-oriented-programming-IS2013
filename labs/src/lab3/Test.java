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
	public static PracowityStudent ja = new PracowityStudent();

	public static void main(String[] args) {
		ja.rzutujNaPracownika().dodajZajecia();
		System.out.println(ja); //ilość zajęć w pracowniku, suma godzin w pracowitym studencie
		LinkedList<Shape> Shapes = new LinkedList<Shape>();
		Shapes.add(new Kwadrat());
		Shapes.add(new Kolo());
		Shapes.add(new Trojkat());
		for (java.util.ListIterator<Shape> iter = Shapes.listIterator(); iter.hasNext();)
			iter.next().draw();
		
	}
}
