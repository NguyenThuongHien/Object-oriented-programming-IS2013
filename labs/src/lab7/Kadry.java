/**
 * Kadry.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab7;


import lab1.Pesel;
import lab4.Pracownik;

/**
 * @author wukat
 * 
 */
public class Kadry {
	public DataBase baza = new DataBase();
	
	/**
	 * 
	 * @param silaRobocza
	 */
	public void dodajPracownika(Pracownik silaRobocza) {
		baza.addEmpleyee(silaRobocza.getNumPESEL(), silaRobocza.getWynagrodzenieBrutto());
	}

	/**
	 * 
	 * @param pesel
	 */
	public void znajdz(Pesel pesel) {
		baza.findEmployee(pesel);
	}

	/**
	 * 
	 * @param pesel
	 */
	public void usunPracownika(Pesel pesel) {
		baza.removeEmployee(pesel);
	}

	/**
	 * get
	 * 
	 * @param pesel
	 * @return
	 */
	public double getWynagrodzenieBrutto(Pesel pesel) {
		String[] temp = baza.findEmployee(pesel).split("| ");
		Double wyn = Double.parseDouble(temp[1]);
		wyn = wyn * 100;
		Long res = Math.round(wyn);
		return res / 100;
	}

	/**
	 * set
	 * 
	 * @param pesel
	 * @param kwota
	 */
	public void setWynagrodzenieBrutto(Pesel pesel, double kwota) {
		baza.setValue(pesel, kwota);
	}

	/**
	 * 
	 */
	public void wyswietlWysztkich() {
		baza.printAllEmpleoyees();
	}

}
