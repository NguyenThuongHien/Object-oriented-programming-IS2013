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

	private int move = 11;
	private static final char[] alphabet= { 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	/**
	 * Constructor
	 * @param move
	 */
	public ROT11(int move) {
		this.move = move;
	}

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
				result += (i + getMove()) % ('z' - 'a' + 1) + 'a';
			} else if (i <= 'Z' && i >= 'A') {
				result += (i + getMove()) % ('Z' - 'A' + 1) + 'A';
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
				result += (i - getMove()) % ('z' - 'a' + 1) + 'a';
			} else if (i <= 'Z' && i >= 'A') {
				result += (i + getMove()) % ('Z' - 'A' + 1) + 'A';
			} else {
				result += i;
			}
		}
		return result;
	}

	/**
	 * Getter
	 * @return the move
	 */
	public int getMove() {
		return move;
	}

	/**
	 * move setter 
	 * @param move the move to set
	 */
	public void setMove(int move) {
		this.move = move;
	}

}
