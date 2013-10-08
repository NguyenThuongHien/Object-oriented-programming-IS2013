package lab1;

/**
 * @class Pesel - implementation of pesel number
 * @author wukat
 *
 */
public class Pesel {
	private int [] pesel = null; //int array pesel
	/**
	 * @brief Constructor
	 * @param PESEL - number as string
	 */
    public Pesel(String PESEL) {
    	pesel = new int [PESEL.length()];
    	for (int i = 0; i < PESEL.length(); i++) {
    		if (PESEL.charAt(i) >= '0' && PESEL.charAt(i) <= '9')
    			pesel[i] = PESEL.charAt(i) - '0';
    			
    	}
    }
}
