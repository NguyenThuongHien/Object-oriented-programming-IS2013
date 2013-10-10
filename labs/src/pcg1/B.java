/**
 * B.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package pcg1;

/**
 * @author wukat
 * 
 */
public class B extends A {
	public B(int argNumber, String argName) {
		super(argNumber, argName);
	}

	protected void decrement() {
		number -= 2;
	}

	void changeName() {
		name = "Binne";
	}

	private void increment() {
		number += 2;
	}
}
