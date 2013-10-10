/**
 * 
 */
package pcg1;

/**
 * 
 * @author wukat
 * 
 */
public class A {
	protected int number;
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name;

	public A(int argNumber, String argName) {
		number = argNumber;
		name = argName;
	}

	public void callDecrement() {
		decrement();
	}

	public void callChangeName() {
		changeName();
	}

	public void callIncrement() {
		increment();
	}

	private void increment() {
		number += 1;
	}

	protected void decrement() {
		number -= 1;
	}

	void changeName() {
		name = "inne";
	}
}
