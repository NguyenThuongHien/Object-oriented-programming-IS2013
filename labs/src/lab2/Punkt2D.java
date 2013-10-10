/**
 * Punkt2D.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package lab2;

/**
 * @author wukat
 * 
 */
public class Punkt2D {
	/**
	 * Coordinates x and y
	 */
	protected double x, y;

	/**
	 * Getter
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setter x
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Getter
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Setter
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Punkt2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Distance calculator
	 * 
	 * @param point
	 *            - point 2d
	 * @return distance between points
	 */
	public double distance(Punkt2D point) {
		return Math.sqrt((getX() - point.getX()) * (getX() - point.getX())
				+ (getY() - point.getY()) * (getY() - point.getY()));
	}
}
