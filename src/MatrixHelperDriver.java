import java.util.Scanner;

public class MatrixHelperDriver {
	public static final Scanner SCANNER = new Scanner(System.in);
	private static MatrixGui gui;
	
	public static void main(String[] args) {
		gui = new MatrixGui();
		Matrix inputMatrix = gui.getMatrix();
		System.out.println("Starting with matrix:\n" + inputMatrix + "\n");
		int addOrSubtract, r1, r2, rNew;
		char operand;
		Fraction multipleA;
		Fraction multipleB;
		
		for(int i = 1; !inputMatrix.isRowReduced(); i++) {
			System.out.println("#" + i + "\nMultiply row or add/subtract? (0 = multiply, 1 = add/subtract)");
			addOrSubtract = SCANNER.nextInt();
			if(addOrSubtract == 0) {
				System.out.println("\npick a row for operation:");
				r1 = SCANNER.nextInt();
				System.out.println("What is the multiplier?");
				multipleA = Fraction.parseFraction(SCANNER.next());
				inputMatrix.rowMultiply(r1, multipleA);
			} else {
				System.out.println("pick the first row for operation:");
				r1 = SCANNER.nextInt();
				System.out.println("What is the r" + r1 + " multiplier?");
				multipleA = Fraction.parseFraction(SCANNER.next());
				System.out.println("Add or subtract?");
				operand = SCANNER.next().charAt(0);
				System.out.println("pick the second row for operation");
				r2 = SCANNER.nextInt();
				System.out.println("What is the r" + r2 + " multiplier?");
				multipleB = Fraction.parseFraction(SCANNER.next());
				System.out.println("Replace which row?");
				rNew = SCANNER.nextInt();
				inputMatrix.changeRow(r1, multipleA, operand, r2, multipleB, rNew);
			}
		}
		System.out.println("\nFinal matrix:\n" + inputMatrix + "\nThe matrix has been reduced! Good job!");
		System.out.println(inputMatrix.variables());
	}
	
	private static double fraction(int i, int i1) {
		return i * 1.0 / i1;
	}
}
