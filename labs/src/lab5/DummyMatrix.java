/**
 * DummyMatrix.java
 * @author - wukat
 * @data - 11 lis 2013
 */
package lab5;

import java.io.IOException;

/**
 * @author wukat
 * 
 */
public class DummyMatrix extends Matrix {

	/**
	 * Constructor
	 * 
	 * @param _n
	 * @param _m
	 * @throws MatrixDimensionsException
	 */
	public DummyMatrix(int _n, int _m) {
		super(_n, _m);
	}

	/**
	 * Constructor
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public DummyMatrix(String filePath) throws IOException {
		super(filePath);
	}

	/**
	 * @brief Adding
	 * @param other
	 *            - other matrix
	 * @return sum or 0,0 rows, cols matrix, if dimensions fail
	 */
	public DummyMatrix add(DummyMatrix other) throws MatrixDimensionsException {
		if (n == other.getN() && m == other.getM()) {
			DummyMatrix result = new DummyMatrix(n, m);
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					result.setValue(i, j, mat[i][j] + other.getValue(i, j));
			return result;
		} else {
			if (n < other.getN()) {
				if (m < other.getM()) {
					DummyMatrix result = new DummyMatrix(n, m);
					for (int i = 0; i < n; i++)
						for (int j = 0; j < m; j++)
							result.setValue(i, j,
									mat[i][j] + other.getValue(i, j));
					throw new MatrixDimensionsException(
							"Dimensions not match. Wynik po obcięciu macierzy",
							result);
				}
				DummyMatrix result = new DummyMatrix(n, other.getM());
				for (int i = 0; i < n; i++)
					for (int j = 0; j < other.getM(); j++)
						result.setValue(i, j, mat[i][j] + other.getValue(i, j));
				throw new MatrixDimensionsException(
						"Dimensions not match. Wynik po obcięciu macierzy",
						result);
			} else {
				if (m < other.getM()) {
					DummyMatrix result = new DummyMatrix(other.getN(), m);
					for (int i = 0; i < other.getN(); i++)
						for (int j = 0; j < m; j++)
							result.setValue(i, j,
									mat[i][j] + other.getValue(i, j));
					throw new MatrixDimensionsException(
							"Dimensions not match. Wynik po obcięciu macierzy",
							result);
				}
				DummyMatrix result = new DummyMatrix(other.getN(), other.getM());
				for (int i = 0; i < other.getN(); i++)
					for (int j = 0; j < other.getM(); j++)
						result.setValue(i, j, mat[i][j] + other.getValue(i, j));
				throw new MatrixDimensionsException(
						"Dimensions not match. Wynik po obcięciu macierzy",
						result);
			}
		}
	}

	/**
	 * @brief Multiplication
	 * @param other
	 *            - other matrix
	 * @throws MatrixDimensionsException
	 * @return result
	 */
	public DummyMatrix mul(DummyMatrix other) throws MatrixDimensionsException {
		if (m == other.getN()) {
			DummyMatrix result = new DummyMatrix(n, other.getM());
			for (int i = 0; i < n; i++)
				for (int j = 0; j < other.getM(); j++)
					for (int k = 0; k < m; k++)
						result.setValue(i, j, result.getValue(i, j) + mat[i][k]
								* other.getValue(k, j));
			return result;
		} else {
			if (m < other.getN()) {
				DummyMatrix result = new DummyMatrix(n, other.getM());
				for (int i = 0; i < n; i++)
					for (int j = 0; j < other.getM(); j++)
						for (int k = 0; k < m; k++)
							result.setValue(i, j, result.getValue(i, j)
									+ mat[i][k] * other.getValue(k, j));
				throw new MatrixDimensionsException(
						"Dimensions not match. Wynik po obcięciu macierzy: \n",
						result);
			} else {
				DummyMatrix result = new DummyMatrix(n, other.getM());
				for (int i = 0; i < n; i++)
					for (int j = 0; j < other.getM(); j++)
						for (int k = 0; k < other.getN(); k++)
							result.setValue(i, j, result.getValue(i, j)
									+ mat[i][k] * other.getValue(k, j));
				throw new MatrixDimensionsException(
						"Dimensions not match. Wynik po obcięciu macierzy: \n",
						result);
			}

		}
	}
}
