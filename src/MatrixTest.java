
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author Malcolm Johnson
 * @version 1.1
 * <p>
 * 3x3 matrix
 * <p>
 * 3x2 matrix (should throw an exception)
 */
/*public class GussJordonEliminationHelperTest {
	static final double[][] threeUnknowns = new double[][] {
	 {8, 1, 1, 3},
	 {4, -2, 2, 2},
	 {1, 1, 3, 4}
	};*/
	/**
	 * 3x2 matrix (should throw an exception)
	 */
//	static final double[][] nonSquareMatrix = new double[][] {
//	 {3, 1, 3},
//	 {4, -2, 2},
//	 {1, 1, 4}
//	};
//	static final double[][] reducedMatrix = new double[][] {
//	 {1, 0, 0, 3},
//	 {0, 1, 0, 2},
//	 {0, 0, 1, 4}
//	};
//	static GaussJordonEliminationHelper matrix1 = new GaussJordonEliminationHelper(squareMatrix);
//	static GaussJordonEliminationHelper matrix2 = new GaussJordonEliminationHelper(reducedMatrix);
//	
//	@Test
//	public void acceptMatrixTest() {
//		assertThrows(IllegalArgumentException.class, ()->{
//			new Matrix(nonSquareMatrix);
//		});
//	}
//	
//	@Test
//	public void isRowReducedTest() {
//		assertFalse(new GaussJordonEliminationHelper(
//		 squareMatrix).isRowReduced());
//		
//		assertTrue(new GaussJordonEliminationHelper(
//		 reducedMatrix).isRowReduced());
//	}
//	
//	@Test
//	public void changeRowTest() {
//		matrix1.changeRow(1, 1, '-', 2, 2, 1);
//		assertArrayEquals(matrix1.row(1),
//		 new double[] {0, 5, -3, -1}, 0.0);
//		
//		matrix1.changeRow(3, 2, '+', 2, 5, 3);
//		assertArrayEquals(matrix1.row(3),
//		 new double[] {22, -8, 16, 18}, 0.0);
//		
//		matrix1.rowMultiply(3, 9.0 / 10);
//		assertArrayEquals(matrix1.row(3),
//		 new double[] {19.8, -7.2, 14.4, 16.2}, 0.0);
//	}
//}

