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
			DummyMatrix A = new DummyMatrix(3, 3);
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 3; j++)
					A.setValue(i, j, i + j);
			System.out.println("Macierz A");
			A.printMatrix();
			System.out.println("\n");
			System.out.println("Macierz przeciwna A");
			A.opposite().printMatrix(); // opposite matrix
			System.out.println("\n");
			

			DummyMatrix B = new DummyMatrix("matrix.txt");
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 4; j++)
					B.setValue(i, j, i - j);
			System.out.println("Macierz B");
			B.printMatrix();
			System.out.println("\n");

			A.printMatrix();
			System.out.print("\n+");
			A.printMatrix();
			System.out.print("\n=");
			(A.add(A)).printMatrix(); // adding
			System.out.print("\n");
//			(A.sub(A)).printMatrix(); // subtracting
//			(A.mul(B)).printMatrix(); // multiplication
			B.printMatrix();
			System.out.print("\n*");
			A.printMatrix();
			System.out.print("\n=");
			(B.mul(A)).printMatrix();
		} catch (IOException e) {
			System.out.println("Błąd związany z plikiem");
		} catch (MatrixDimensionsException a) {
			System.out.println(a.getMessage());
			a.getThrowedMatrix().printMatrix();
		}
		catch (Exception e) {
			System.out.println("Błędny format pliku");
		}

	}
}
