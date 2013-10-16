/**
 * Zad1.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package lab3;

import java.util.LinkedList;

/**
 * @author wukat
 * 
 */
public class Zad1 {

	public final double var = 0;

	public void changeVar() {
		// var++; nie działa
	}

	public void changeList(final LinkedList<Double> list) {
		list.add(1.1);
		list.pop();
		// list = new LinkedList<Double>(); i tego nie wolno
	}

}
