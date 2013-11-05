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
}
