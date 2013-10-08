package lab1;

/**
 * @class Implementation of Matrix
 * @author wukat
 * 
 */
public class Matrix {
	private int[][] mat; // integer 2D array
	private int n, m; // dimensions

	/**
	 * @brief Constructor
	 * @param _n
	 *            - number of rows
	 * @param _m
	 *            - number of columns
	 */
	public Matrix(int _n, int _m) {
		n = _n;
		m = _m;
		if ((n >= 0) && (m >= 0))
			mat = new int[n][m];
	}

	/**
	 * @brief Simple n getter
	 * @return n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @brief Simple m getter
	 * @return m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @brief Value getter
	 * @param _n - row
	 * @param _m - column
	 * @return value in place n,m or 0 if place out of range
	 */
	public int getValue(int _n, int _m) {
		if ((_n < n) && (_n >= 0) && (_m < m) && (_m >= 0))
			return mat[_n][_m];
		return 0;
	}

	/**
	 * @brief Value setter
	 * @param _n - row
	 * @param _m - column
	 * @param value - value to set
	 * @return true if place in range, false otherwise
	 */
	public boolean setValue(int _n, int _m, int value) {
		if ((_n < n) && (_n >= 0) && (_m < m) && (_m >= 0)) {
			mat[_n][_m] = value;
			return true;
		}
		return false;
	}

	/**
	 * @brief Multiplication with -1 scalar
	 * @return Opposite matrix
	 */
	public Matrix opposite() {
		Matrix result = new Matrix(n, m);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				result.setValue(i, j, -mat[i][j]);
		return result;
	}

	/**
	 * @brief Adding
	 * @param other - other matrix
	 * @return sum or 0,0 rows, cols matrix, if dimensions fail
	 */
	public Matrix add(Matrix other) {
		if (n == other.getN() && m == other.getM()) {
			Matrix result = new Matrix(n, m);
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					result.setValue(i, j, mat[i][j] + other.getValue(i, j));
			return result;
		}
		return new Matrix(0, 0);
	}

	/**
	 * @brief Subtracting
	 * @param other - other matrix
	 * @return subtract or 0,0 rows, cols matrix, if dimensions fail
	 */
	public Matrix sub(Matrix other) {
		return add(other.opposite());
	}

	/**
	 * @brief Multiplication
	 * @param other - other matrix
	 * @return result of multiplication or 0,0 rows, cols matrix, if dimensions fail
	 */
	public Matrix mul(Matrix other) {
		if (n == other.getM()) {
			Matrix result = new Matrix(other.getN(), m);
			for (int i = 0; i < other.getN(); i++)
				for (int j = 0; j < m; j++)
					for (int k = 0; k < n; k++)
						result.setValue(i, j, result.getValue(i, j) + mat[k][j]
								* other.getValue(i, k));
			return result;
		}
		return new Matrix(0, 0);
	}

	/**
	 * @brief Simple function to print matrix
	 */
	public void printMatrix() {
		for (int j = 0; j < m; j++) {
			System.out.print('\n');
			for (int i = 0; i < n; i++) {
				System.out.print(getValue(i, j));
				System.out.print(' ');
			}
		}
		System.out.print('\n');
	}
}
