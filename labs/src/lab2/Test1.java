/**
 * Test1.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package lab2;

import pcg1.A;
import pcg1.B;
import pcg2.C;

/**
 * @author wukat
 *
 */
public class Test1 {
	public static void main(String [] argv) {
		A a = new A(5, "imie");
		B b = new B(9, "imie1");
		C c = new C(17, "nazwa");
		a.callChangeName();
		a.callDecrement();
		a.callIncrement();
		b.callChangeName();
		b.callDecrement();
		b.callIncrement();
		c.callChangeName();
		c.callDecrement();
		c.callIncrement();
		System.out.print(a.getName());
		System.out.print(a.getNumber());
		System.out.print(b.getName());
		System.out.print(b.getNumber());
		System.out.print(c.getName());
		System.out.print(c.getNumber());
		
	}

}
