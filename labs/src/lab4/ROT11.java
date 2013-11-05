/**
 * ROT13.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

/**
 * @author wukat
 * 
 */
public class ROT11 implements Algorythm {

	static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };

	/*
	 * (non-Javadoc)
	 * 
	 * @see lab4.Algorythm#crypt(java.lang.String)
	 */
	@Override
	public String crypt(String toCrypt) {
		String result = new String();
		for (int i : toCrypt.toCharArray()) {
			if (i <= 'z' && i >= 'a') {
				result += (i + 11) % ('z' - 'a' + 1) + 'a';
			} else if (i <= 'Z' && i >= 'A') {
				result += (i + 11) % ('Z' - 'A' + 1) + 'A';
			} else {
				result += i;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lab4.Algorythm#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(String toDecrypt) {
		String result = new String();
		for (int i : toDecrypt.toCharArray()) {
			if (i <= 'z' && i >= 'a') {
				result += (i - 11) % ('z' - 'a' + 1) + 'a';
			} else if (i <= 'Z' && i >= 'A') {
				result += (i + 11) % ('Z' - 'A' + 1) + 'A';
			} else {
				result += i;
			}
		}
		return result;
	}

}
