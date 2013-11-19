/**
 * Trojkat.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package lab6;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author wukat
 * 
 */
public class Trojkat extends Shape {

	/**
	 * Constructor
	 */
	public Trojkat() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lab3.Shape#draw()
	 */
	@Override
	public void draw(Graphics g) {
		Random rand = new Random();
		int a = Math.abs(rand.nextInt()) % 270;
		int b = Math.abs(rand.nextInt()) % 200;
		int[] x = { a, a + 20, a + 30, a };
		int[] y = { b + 10, b + 80, b + 10, b + 10 };
		g.setColor(Color.BLUE);
		g.fillPolygon(x, y, 4);
	}
}
