/**
 * PracownikEtatowy.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import lab1.Pesel;

/**
 * @author wukat
 *
 */
public class PracownikEtatowy extends Pracownik {

	/**
	 * Constructor
	 * @param pesel
	 * @param wynagrodzenieBrutto
	 */
	public PracownikEtatowy(Pesel pesel, double wynagrodzenieBrutto) {
		super();
		this.numPESEL = pesel;
		this.wynagrodzenieBrutto = wynagrodzenieBrutto;
	}

	/* (non-Javadoc)
	 * @see lab4.Pracownik#liczbNetto()
	 */
	@Override
	public double liczNetto() {
		return getWynagrodzenieBrutto()*0.6;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PracownikEtatowy [numPESEL=" + numPESEL
				+ ", wynagrodzenieBrutto=" + wynagrodzenieBrutto + "]";
	}

}
