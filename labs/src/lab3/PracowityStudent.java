/**
 * PracowityStudent.java
 * @author - wukat
 * @data - 16 paź 2013
 */
package lab3;

/**
 * @author wukat
 * 
 */
class PracowityStudent extends Student {
	private int sumaGodzin;

	public Pracownik rzutujNaPracownika() {
		return new InnerPracownik();
	}

	public void dodajZajecia() {
		iloscZajec++;
		sumaGodzin++;
	}

	class InnerPracownik extends Pracownik {
		public void dodajZajecia() {
			iloscZajec++;
			sumaGodzin++;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "InnerPracownik [iloscZajec=" + iloscZajec + "]";
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PracowityStudent [sumaGodzin=" + sumaGodzin + ", iloscZajec="
				+ iloscZajec + "]";
	}

	
}
