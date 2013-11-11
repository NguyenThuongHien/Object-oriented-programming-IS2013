package lab5;

import java.io.IOException;

/**
 * @class Main program testing matrix class
 * @author wukat
 * 
 */
public class MainMatrix {
	/**
	 * @brief Main function
	 * @param argv
	 *            program input arguments
	 */
	public static void main(String[] argv) {
		try {
			Matrix A = new Matrix(4, 3);
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 3; j++)
					A.setValue(i, j, i + j);
			A.printMatrix();
			A.opposite().printMatrix(); // opposite matrix

			Matrix B = new Matrix("matrix.txt");
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 4; j++)
					B.setValue(i, j, i - j);
			B.printMatrix();

			(A.add(A)).printMatrix(); // adding
			(A.sub(A)).printMatrix(); // subtracting
			(A.mul(B)).printMatrix(); // multiplication
			(B.mul(A)).printMatrix();
		} catch (MatrixDimensionsException a) {
			System.out.println(a.getMessage());
		}
		catch (IOException e) {
			System.out.println("Błąd związany z plikiem");
		}
		catch (Exception e) {
			System.out.println("Błędny format pliku");
		}
		
	}
}