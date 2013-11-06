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
public class Polibiusz implements Algorithm {

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
		toCrypt = toCrypt.toUpperCase();
		toCrypt.replace('J', 'I');
		boolean flag;
		for (Character k : toCrypt.toCharArray()) {
			flag = false;
			for (int i = 0; i < alphabet.length && !flag; i++) {
				for (int j = 0; j < alphabet[0].length && !flag; j++) {
					if (k.equals(alphabet[i][j])) {
						result += i + 1;
						result += j + 1;
						result += ' ';
						flag = true;
					}
				}
			}
			if (!flag) {
				result += k;
				if (k != ' ')
					result += ' ';
			}
		}
		result += '\n';
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lab4.Algorythm#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(String toDecrypt) {
		String result = "";
		char[] toDecryptArray = toDecrypt.toCharArray();
		int step = 1;
		for (int i = 0; i < toDecryptArray.length; i += step) {
			step = 1;
			if (toDecryptArray[i] - '0' >= 0
					&& toDecryptArray[i] - '0' < alphabet.length) {
				step = 2;
				if ((toDecryptArray[i + 1] - 1 - '0' >= 0 && toDecryptArray[i + 1] - 1 - '0' < alphabet[0].length)
						&& i + 1 < toDecryptArray.length) {
					result = result
							+ alphabet[toDecryptArray[i] - 1 - '0'][toDecryptArray[i + 1] - 1 - '0'];
				} else {
					result = result + toDecryptArray[i];
					if (i + 1 < toDecrypt.length())
						if (toDecrypt.charAt(i + 1) != ' ') 
							result = result + toDecryptArray[i + 1];
						else {
							if (i + 2 < toDecrypt.length()) {
								if (toDecrypt.charAt(i + 2) == ' ')
									result = result + toDecryptArray[i + 1];
							}
						}
				}
			} else {
				if (i + 1 < toDecrypt.length())
					if (toDecrypt.charAt(i + 1) == ' ')
						result = result + toDecryptArray[i];
			}
		}
		result += '\n';
		return result;
	}

}
