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
public class ROT11 implements Algorithm {

	private final int move = 11;
	private static final char[] alphabet= { 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


	/*
	 * (non-Javadoc)
	 * 
	 * @see lab4.Algorythm#crypt(java.lang.String)
	 */
	@Override
	public String crypt(String toCrypt) {
		String result = "";
		for (char i : toCrypt.toCharArray()) {
			if (i <= 'z' && i >= 'a') {
				result = result + (char) ((i + getMove() - 'a') % ('z' - 'a' + 1) + 'a');
			} else if (i <= 'Z' && i >= 'A') {
				result = result + (char) ((i + getMove() - 'A') % ('Z' - 'A' + 1) + 'A');
			} else {
				result = result + i;
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
		String result = new String();
		for (char i : toDecrypt.toCharArray()) {
			if (i <= 'z' && i >= 'a') {
				if (i - getMove() >= 'a')
				    result = result + (char) ((i - getMove() - 'a') % ('z' - 'a' + 1) + 'a');
				else
					result = result + (char) (i - getMove() + 'z' - 'a' + 1);
			} else if (i <= 'Z' && i >= 'A') {
				if (i - getMove() >= 'A')
				    result = result + (char) ((i - getMove() - 'A') % ('Z' - 'A' + 1) + 'A');
				else
					result = result + (char) (i - getMove() + 'Z' - 'A' + 1);
			} else {
				result = result + i;
			}
		}
		result += '\n';
		return result;
	}

	/**
	 * Getter
	 * @return the move
	 */
	public int getMove() {
		return move;
	}
}
