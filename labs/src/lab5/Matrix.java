package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	public Matrix(int _n, int _m) throws MatrixDimensionsException {
		n = _n;
		m = _m;
		if ((n > 0) && (m > 0))
			mat = new int[n][m];
		else
			throw new MatrixDimensionsException(
					"Matrix dimensions must be natural!");
	}

	/**
	 * 
	 * Constructor from file
	 * @param filePath
	 * @throws IOException
	 */
	public Matrix(String filePath) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new FileReader(new File(filePath)))) {
		    String[] temp = buffer.readLine().split(" ");
		    mat = new int[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
		}
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
	 * @param _n
	 *            - row
	 * @param _m
	 *            - column
	 * @return value in place n,m or 0 if place out of range
	 */
	public int getValue(int _n, int _m) throws MatrixDimensionsException {
		if ((_n < n) && (_n >= 0) && (_m < m) && (_m >= 0))
			return mat[_n][_m];
		else
			throw new MatrixDimensionsException("Wrong indexes given!");
	}

	/**
	 * @brief Value setter
	 * @param _n
	 *            - row
	 * @param _m
	 *            - column
	 * @param value
	 *            - value to set
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
	public Matrix opposite() throws MatrixDimensionsException {
		Matrix result = new Matrix(n, m);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				result.setValue(i, j, -mat[i][j]);
		return result;
	}

	/**
	 * @brief Adding
	 * @param other
	 *            - other matrix
	 * @return sum or 0,0 rows, cols matrix, if dimensions fail
	 */
	public Matrix add(Matrix other) throws MatrixDimensionsException {
		if (n == other.getN() && m == other.getM()) {
			Matrix result = new Matrix(n, m);
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					result.setValue(i, j, mat[i][j] + other.getValue(i, j));
			return result;
		} else
			throw new MatrixDimensionsException("Dimensions not match.");
	}

	/**
	 * @brief Subtracting
	 * @param other
	 *            - other matrix
	 * @return subtract or 0,0 rows, cols matrix, if dimensions fail
	 */
	public Matrix sub(Matrix other) throws MatrixDimensionsException {
		return add(other.opposite());
	}

	/**
	 * @brief Multiplication
	 * @param other
	 *            - other matrix
	 * @return result of multiplication or 0,0 rows, cols matrix, if dimensions
	 *         fail
	 */
	public Matrix mul(Matrix other) throws MatrixDimensionsException {
		if (n == other.getM()) {
			Matrix result = new Matrix(other.getN(), m);
			for (int i = 0; i < other.getN(); i++)
				for (int j = 0; j < m; j++)
					for (int k = 0; k < n; k++)
						result.setValue(i, j, result.getValue(i, j) + mat[k][j]
								* other.getValue(i, k));
			return result;
		} else
			throw new MatrixDimensionsException("Dimensions not match.");
	}

	/**
	 * @brief Simple function to print matrix
	 */
	public void printMatrix() throws MatrixDimensionsException {
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
