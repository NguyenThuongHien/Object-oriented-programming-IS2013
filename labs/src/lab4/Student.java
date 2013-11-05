/**
 * Student.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import lab1.Pesel;

/**
 * @author wukat
 * 
 */
public class Student extends Pracownik {

	/**
	 * Constructor
	 * @param pesel
	 * @param wynagrodzenieBrutto
	 */
	public Student(Pesel pesel, double wynagrodzenieBrutto) {
		super();
		this.numPESEL = pesel;
		this.wynagrodzenieBrutto = wynagrodzenieBrutto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lab4.Pracownik#liczbNetto()
	 */
	@Override
	public double liczNetto() {
		return getWynagrodzenieBrutto()*0.8;
	}

}
