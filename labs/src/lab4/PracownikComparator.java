/**
 * PracownikComparator.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import java.util.Comparator;

/**
 * @author wukat
 *
 */
public class PracownikComparator implements Comparator<Pracownik> {
	@Override
	public int compare(Pracownik p1, Pracownik p2) {
		return p1.getWynagrodzenieBrutto().compareTo(p2.getWynagrodzenieBrutto());
	}
}
