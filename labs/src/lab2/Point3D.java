/**
 * Punkt3D.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package lab2;

/**
 * @author wukat
 * 
 */
public class Point3D extends Point2D {

	/**
	 * z coordinator
	 */
	double z;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	/**
	 * Getter
	 * 
	 * @return the z
	 */
	public double getZ() {
		return z;
	}

	/**
	 * z setter
	 * 
	 * @param z
	 *            the z to set
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Distance calculator
	 * 
	 * @param point
	 *            3D point
	 * @return distance between two points
	 */
	public double distance(Point3D point) {
		return Math.sqrt((getX() - point.getX()) * (getX() - point.getX())
				+ (getY() - point.getY()) * (getY() - point.getY())
				+ (getZ() - point.getZ()) * (getZ() - point.getZ()));
	}
	
	public void printPoint3D() {
		System.out.print(getX() + " " + getY() + " " + getZ() + "\n");
	}
}
