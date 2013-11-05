/**
 * Algorythm.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

/**
 * @author wukat
 *
 */
public interface Algorythm {
	
	/**
	 * Ciphers word
	 * @param toCrypt
	 * @return ciphered word
	 */
    public abstract String crypt(String toCrypt);
    
    /**
	 * Decodes word
	 * @param toCrypt
	 * @return deciphered word
	 */
    public abstract String decrypt(String toDecrypt);
}
