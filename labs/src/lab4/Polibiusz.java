/**
 * Polibiusz.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

/**
 * @author wukat
 * 
 */
public class Polibiusz implements Algorythm {

	private final char[][] alphabet = { { 'A', 'B', 'C', 'D', 'E' },
			{ 'F', 'G', 'H', 'I', 'K' }, { 'L', 'M', 'N', 'O', 'P' },
			{ 'Q', 'R', 'S', 'T', 'U' }, { 'V', 'W', 'X', 'Y', 'Z' } };

	/*
	 * (non-Javadoc)
	 * 
	 * @see lab4.Algorythm#crypt(java.lang.String)
	 */
	@Override
	public String crypt(String toCrypt) {
		String result = new String();
		toCrypt.toUpperCase();
		toCrypt.replace('J', 'I');
		boolean flag;
		for (Character k : toCrypt.toCharArray()) {
			flag = false;
			for (int i = 0; i < alphabet.length && !flag; i++) {
				for (int j = 0; j < alphabet[0].length && !flag; j++) {
					if (k.equals(alphabet[i][j])) {
						result += i;
						result += j;
						flag = true;
					}
				}
			}
			if (!flag) {
				result += k;
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
		char[] toDecryptArray = toDecrypt.toCharArray();
		int step = 1;
		for (int i = 0; i < toDecrypt.length(); i += step) {
			if (toDecryptArray[i] - '0' >= 0
					|| toDecryptArray[i] - '0' < alphabet.length) {
				step = 2;
				if (toDecryptArray[i + 1] - '0' >= 0
						|| toDecryptArray[i] - '0' < alphabet[0].length)
					result += alphabet[toDecryptArray[i]][toDecryptArray[i + 1]];
				else {
					result += toDecryptArray[i];
					result += toDecryptArray[i + 1];
				}
			} else {
				result += toDecryptArray[i];
			}
		}
		return result;
	}

}
