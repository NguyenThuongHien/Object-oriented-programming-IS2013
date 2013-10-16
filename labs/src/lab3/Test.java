/**
 * Test.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package lab3;

/**
 * @author wukat
 * 
 */
public class Test {
	public static PracowityStudent ja = new PracowityStudent();

	public static void main(String[] args) {
		ja.rzutujNaPracownika().dodajZajecia();
		System.out.print(ja); //ilość zajęć w pracowniku, suma godzin w pracowitym studencie
	}
}
