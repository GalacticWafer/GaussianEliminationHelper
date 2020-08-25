import java.util.Scanner;

public class GaussJordonEliminationDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GaussJordonEliminationHelper matrix = new GaussJordonEliminationHelper(
		 new Fraction[][] {
		  new Fraction[]{new Fraction(3, 1), new Fraction(1,1),  new Fraction(1,1), new Fraction(3,1)},
		  new Fraction[]{new Fraction(4, 1), new Fraction(-2,1), new Fraction(2,1), new Fraction(2,1)},
		  new Fraction[]{new Fraction(1, 1), new Fraction(1,1),  new Fraction(3,1), new Fraction(4,1)}
		 });
		System.out.println("Starting with matrix:\n" + matrix + "\n");
		//matrix.changeRow("2/1r1-1/1r3->r1");
		matrix.changeRow(1, "1/1", '-', 3, "2/1", 1);
		matrix.changeRow(2, "1/1", '-', 3, "4/1", 2);
		matrix.changeRow(1, "1/1", '-', 3, "1/1", 3);
		matrix.rowMultiply(2, new Fraction(-1,6));
		matrix.changeRow(1, "1/1", '+', 2, "1/1", 1);
		matrix.changeRow(2, new Fraction(2,1), '+', 3, new Fraction(1,1), 3);
		matrix.changeRow(2, new Fraction(2,1), '+', 3, new Fraction(1,1), 3);
		matrix.changeRow(3,new Fraction(1,1), '-', 2, new Fraction(2,1), 3);
		matrix.rowMultiply(3, new Fraction(-3,14));
		matrix.changeRow(3, new Fraction(5,3), '-', 2, new Fraction(1,1), 2);
		matrix.changeRow(1, new Fraction(1,1), '+', 3, new Fraction(10,3), 1);
		matrix.rowMultiply(2, new Fraction(-1,1));
		
		
		
		int addOrSubtract, r1, r2, rNew;
		char operand;
		Fraction multipleA;
		Fraction multipleB;
		
		for(int i = 1; !matrix.isRowReduced(); i++) {
			System.out.println("#" + i + "\nMultiply row or add/subtract? (0 = multiply, 1 = add/subtract)");
			addOrSubtract = scanner.nextInt();
			if(addOrSubtract == 0) {
				System.out.println("\npick a row for operation:");
				r1 = scanner.nextInt();
				System.out.println("What is the multiplier?");
				multipleA = Fraction.inputFraction(scanner.next());
				matrix.rowMultiply(r1, multipleA);
			} else {
				System.out.println("pick the first row for operation:");
				r1 = scanner.nextInt();
				System.out.println("What is the r" + r1 + " multiplier?");
				multipleA = Fraction.inputFraction(scanner.next());
				System.out.println("Add or subtract?");
				operand = scanner.next().charAt(0);
				System.out.println("pick the second row for operation");
				r2 = scanner.nextInt();
				System.out.println("What is the r" + r2 + " multiplier?");
				multipleB = Fraction.inputFraction(scanner.next());
				System.out.println("Replace which row?");
				rNew = scanner.nextInt();
				matrix.changeRow(r1, multipleA, operand, r2, multipleB, rNew);
			}
		}
	}
	
	private static double fraction(int i, int i1) {
		return i * 1.0 / i1;
	}
}
