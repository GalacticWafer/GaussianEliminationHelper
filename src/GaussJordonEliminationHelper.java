import java.util.Arrays;

public class GaussJordonEliminationHelper {
	private final Matrix matrix;   // M-by-N array
	
	public GaussJordonEliminationHelper(Fraction[][] array) throws IllegalArgumentException {
		matrix = new Matrix(array);
	}
	
	@Override public String toString() {
		return matrix + "";
	}
	
	public boolean changeRow(int row1, String multiple1, char operand, int row2, String multiple2,
	 int newRow) {
		return changeRow(row1, Fraction.inputFraction(multiple1), operand, row2, Fraction
		 .inputFraction(multiple2), newRow);
	}
	
	public boolean changeRow(int r1, Fraction multiple1, char operand, int r2, Fraction multiple2,
	 int rNew
	) {
		if(r1 < 1 || r2 < 1 || rNew < 1 || r1 > matrix.M || r2 > matrix.M || rNew > matrix.M ||
		 (operand != '+' && operand != '-')) {
			throw new IllegalArgumentException();
		}
		System.out
		 .println(
		  multiple1 + " * r" + r1 + " " + operand + " " + multiple2 + " * r" + r2 + " --> r" +
		   rNew);
		r1--;
		r2--;
		Fraction[] tempA = matrix.rowMultiply(r1, multiple1);
		Fraction[] tempB = matrix.rowMultiply(r2, multiple2);
		switch(operand) {
			case '+': {
				matrix.setRow(rNew, matrix.add(tempA, tempB));
				return true;
			}
			case '-': {
				matrix.setRow(rNew, matrix.sub(tempA, tempB));
				return true;
			}
		}
		return false;
	}
	
	public void rowMultiply(int row, Fraction multiplier) {
		matrix.setMultipliedRow(--row, matrix.rowMultiply(row, multiplier));
		System.out.println("r" + (row + 1) + " multiplied by " + multiplier + "\n" + matrix);
	}
	
	public boolean isRowReduced() {
		return matrix.isRowReduced();
	}
	
	public Fraction[] row(int row) {
		return matrix.getRow(row - 1);
	}
	
	public String variables() {
		return matrix.variables();
	}
	
	//public void changeRow(String s) {
	//	//"2/1r1-1/1r3->r1"
	//	String r = "r";
	//	String fractionBar = "/";
	//	String plus = "+";
	//	String minus = "-";
	//	String arrow = "->";
	//	
	//	int newRow = Integer.parseInt(s.substring(s.indexOf(arrow) + 3));
	//	s = s.substring(0, s.indexOf(arrow));
	//	
	//	String tempString =
	//	int row2 = Integer.parseInt(s.substring(s.substring(s.indexOf(r,s.indexOf(r) + 1)), 1);
	//	Fraction multiplier1 = Fraction.inputFraction(s.substring(0, s.trim().indexOf('r')));
	//	s = s.substring(s.trim().indexOf('r') + 1).trim();
	//	char operand = (s.contains("+")) ? '+' : '-';
	//	String test = s.substring(0, s.trim().indexOf(operand)); 
	//	int row1 = Integer.parseInt(test);
	//	s = s.substring(s.indexOf(operand) + 1).trim();
	//	Fraction multiplier2 = Fraction.inputFraction(s.trim().substring(0, s.trim().indexOf('r')));
	//	s = s.substring(s.trim().indexOf('r') + 1).trim();
	//	s = s.substring(s.indexOf('r') + 1).trim();
	//	changeRow(row1, multiplier1, operand, row2, multiplier2, newRow);
	//}
	
	private class Matrix {
		private Fraction[][] array;
		private final int M;             // number of rows
		private final int N;             // number of columns
		
		public Matrix(Fraction[][] array) {
			if(array.length == 0) {
				throw new IllegalArgumentException();
			}
			int i = 0;
			int j = 0;
			for(Fraction[] fractions: array) {
				i++;
				for(Fraction f: fractions) {
					j++;
				}
			}
			if(i + 1 != j / i) {
				System.out.println(
				 "Not an augmented matrix. " + i + " rows should have " + (i + 1) +
				  " columns, but the following array has " + (j / i) + " columns:\n" +
				  Arrays.deepToString(array));
				throw new IllegalArgumentException();
			}
			M = i;
			N = j / i;
			this.array = array;
		}
		
		public void setMultipliedRow(int newRow, Fraction[] newArray) {
			array[newRow] = newArray;
			System.out.println(
			 "r" + (newRow + 1) + " is now " + Arrays.toString(newArray) + "\nFull matrix:\n" +
			  matrix + "\n\n");
		}
		
		public void setRow(int newRow, Fraction[] newArray) {
			array[--newRow] = newArray;
			System.out.println(
			 "r" + (newRow + 1) + " is now " + Arrays.toString(newArray) + "\nFull matrix:\n" +
			  matrix + "\n\n");
		}
		
		public Fraction[] add(Fraction[] R1, Fraction[] R2) {
			Fraction[] sumArray = new Fraction[R1.length];
			for(int i = 0; i < sumArray.length; i++) {
				sumArray[i] = R1[i].add(R2[i]);
				System.out.println(R1[i] + " + " + R2[i] + " = " + sumArray[i]);
			}
			return sumArray;
		}
		
		public Fraction[] rowMultiply(int row, Fraction multiplier) {
			Fraction[] newArray = new Fraction[array[row].length];
			for(int i = 0; i < newArray.length; ++i) {
				Fraction temp = array[row][i];
				newArray[i] = array[row][i].multiply(multiplier);
				System.out.println(multiplier + " * " + temp + " = " + newArray[i]);
			}
			return newArray;
		}
		
		public Fraction[] sub(Fraction[] R1, Fraction[] R2) {
			System.out.println();
			Fraction[] sumArray = new Fraction[R1.length];
			for(int i = 0; i < sumArray.length; i++) {
				sumArray[i] = R1[i].subtract(R2[i]);
				System.out.println(R1[i] + " - " + R2[i] + " = " + sumArray[i]);
			}
			System.out.println();
			return sumArray;
		}
		
		public Fraction[] getRow(int x) {
			return array[x];
		}
		
		@Override public String toString() {
			String s = "";
			for(int i = 0; i < matrix.M; ++i) {
				s += "[";
				for(int j = 0; j < matrix.N; ++j) {
					s += (array[i][j] + "") + (j == N - 1?"]":(j == N - 2?"|":", "));
				}
				s += i == M - 1?"":"\n";
			}
			return s;
		}
		
		public boolean isRowReduced() {
			for(int i = 0; i < M; i++) {
				if(!array[i][i].equals(1)) {
					return false;
				}
				int zeros = 0;
				int ones = 0;
				for(int j = 0; j < N - 1; j++) {
					if(array[i][j].equals(0)) {
						zeros++;
					}
					else if(array[i][j].equals(1)) {
						ones++;
					}
				}
				if(zeros != N - 2 || ones != 1) {
					return false;
				}
			}
			return true;
		}
		
		public String variables() {
			String variables = "";
			for(int i = 0; i < array.length; i++) {
				variables = ((char)('z' - i)) + " = " + (array[i][N - 1].toString()) + "\n" + variables;
			}
			return variables;
		}
	}
}
