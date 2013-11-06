/**
 * zad2.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import lab1.Pesel;

/**
 * @author wukat
 *
 */
public class zad2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Kadry zespol = new Kadry();
		zespol.dodajPracownika(new Student(new Pesel("93070301513"), 930.5));
		zespol.dodajPracownika(new Student(new Pesel("93070301516"), 980.5));
		zespol.dodajPracownika(new PracownikEtatowy(new Pesel("93070301519"), 1380.5));
		
		zespol.posortujPoWynagrodzeniu();
		zespol.wyswietlWysztkich();
		
		System.out.println(zespol.getWynagrodzenieBrutto(new Pesel("93070301513")));
		System.out.print(zespol.getWynagrodzenieNetto(new Pesel("93070301513")));
	}

}
