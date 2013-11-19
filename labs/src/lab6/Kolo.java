/**
 * Kolo.java
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
public class Kolo extends Shape {

	/**
	 * Constructor
	 */
	public Kolo() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see lab3.Shape#draw()
	 */
	@Override
	public void draw(Graphics g) {
		Random rand = new Random();  
		g.setColor(Color.RED);
		g.fillOval(Math.abs(rand.nextInt()) % 240 + 15, Math.abs(rand.nextInt()) % 240 + 15, 15, 15);
	}

}
