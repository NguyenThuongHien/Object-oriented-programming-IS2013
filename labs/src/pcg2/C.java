/**
 * C.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package pcg2;

import pcg1.B;

/**
 * @author wukat
 * 
 */
public class C extends B {
	public C(int argNumber, String argName) {
		super(argNumber, argName);
	}

	void changeName() {
//         name = 'Cinne'; nie widzi zmiennej; package nie idzie z dziedziczeniem poza paczkę
    }
}
