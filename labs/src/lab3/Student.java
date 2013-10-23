/**
 * Student.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package lab3;

/**
 * @author wukat
 * 
 */
public abstract class Student {
	protected int iloscZajec;

	public abstract void dodajZajecia();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [iloscZajec=" + iloscZajec + "]";
	}

}
