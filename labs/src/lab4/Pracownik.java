/**
 * Pracownik.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import lab1.Pesel;

/**
 * @author wukat
 * 
 */
public abstract class Pracownik {
	Pesel numPESEL;
	double wynagrodzenieBrutto;

	abstract public double liczbNetto();

	/**
	 * Getter
	 * 
	 * @return the numPESEL
	 */
	public Pesel getNumPESEL() {
		return numPESEL;
	}

	/**
	 * numPESEL setter
	 * 
	 * @param numPESEL
	 *            the numPESEL to set
	 */
	public void setNumPESEL(Pesel numPESEL) {
		this.numPESEL = numPESEL;
	}

	/**
	 * Getter
	 * 
	 * @return the wynagrodzenieBrutto
	 */
	public double getWynagrodzenieBrutto() {
		return wynagrodzenieBrutto;
	}

	/**
	 * wynagrodzenieBrutto setter
	 * 
	 * @param wynagrodzenieBrutto
	 *            the wynagrodzenieBrutto to set
	 */
	public void setWynagrodzenieBrutto(double wynagrodzenieBrutto) {
		this.wynagrodzenieBrutto = wynagrodzenieBrutto;
	}

}
