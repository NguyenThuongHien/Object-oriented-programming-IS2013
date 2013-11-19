/**
 * Kwadrat.java
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
public class Kwadrat extends Shape {

	/**
	 * Constructor
	 */
	public Kwadrat() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see lab3.Shape#draw()
	 */
	@Override
	public void draw(Graphics g) {
		Random rand = new Random();
		g.setColor(Color.GREEN);
		g.fillRect(Math.abs(rand.nextInt()) % 200, Math.abs(rand.nextInt()) % 200, 50, 50);
	}
}
