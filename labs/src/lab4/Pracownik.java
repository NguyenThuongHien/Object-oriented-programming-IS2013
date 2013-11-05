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
	Double wynagrodzenieBrutto;

	abstract public double liczNetto();

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
	public Double getWynagrodzenieBrutto() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pracownik [numPESEL=" + numPESEL + ", wynagrodzenieBrutto="
				+ wynagrodzenieBrutto + "]";
	}
	
	

}
