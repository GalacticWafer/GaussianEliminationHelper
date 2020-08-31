import java.awt.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Matrix implements Iterable<Fraction[]> {
	private int[][] columnWidths;
	private int inputMode;
	private Fraction[][] data;
	private MatrixGui gui;
	private Dimension size;
	
	public Matrix(Fraction[][] array) {
	}
	
	public static Fraction[][] fromConsole() {
		String[] s =
		 new String[] {"1/6 5 5/3 4\n", "		2 9/2 8/9 1\n", "		1 5/11 7 3\n"};
		Scanner scanner = new Scanner(System.in);
		System.out.println("how many variables?");
		int n = 3/*scanner.nextInt()*/;
		/*scanner.nextLine();*/
		Fraction[][] matrix = new Fraction[n][n + 1];
		for(int i = 0; i < n; i++) {
			System.out.println("type row " + (i + 1) + " of the matrix (use spaces, no commas)");
			String[] line = s[i].trim() /*scanner.nextLine()*/.split("\\s");
			if(line.length > n + 1) {
				System.out.println("Incorrect size matrix");
				throw new InputMismatchException();
			}
			for(int j = 0; j < n + 1; j++) {
				matrix[i][j] = Fraction.parseFraction(line[j]);
			}
		}
		return matrix;
	}
	public Dimension size() {
		return size;
	}
	public boolean changeRow(int row1, String multiple1, char operand, int row2, String multiple2,
	 int newRow) {
		return changeRow(row1, Fraction.parseFraction(multiple1), operand, row2, Fraction
		 .parseFraction(multiple2), newRow);
	}
	
	public boolean changeRow(int r1, Fraction multiple1, char operand, int r2, Fraction multiple2,
	 int rNew
	) {
		if(r1 < 1 || r2 < 1 || rNew < 1 || r1 > M || r2 > M || rNew > M ||
		 (operand != '+' && operand != '-')) {
			throw new IllegalArgumentException();
		}
		System.out
		 .println(
		  multiple1 + " * r" + r1 + " " + operand + " " + multiple2 + " * r" + r2 + " --> r" +
		   rNew);
		r1--;
		r2--;
		Fraction[] tempA = rowMultiply(r1, multiple1);
		Fraction[] tempB = rowMultiply(r2, multiple2);
		switch(operand) {
			case '+': {
				setRow(rNew, add(tempA, tempB));
				return true;
			}
			case '-': {
				setRow(rNew, sub(tempA, tempB));
				return true;
			}
		}
		return false;
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
	
	public Fraction[] row(int row) {
		return getRow(row - 1);
	}
	
	public String variables() {
		String variables = "";
		for(int i = 0; i < array.length; i++) {
			variables =
			 ((char)('z' - i)) + " = " + (array[i][N - 1].toString()) + "\n" + variables;
		}
		return variables;
	}
	
	private Fraction[][] array;
	private int M;             // number of rows
	private int N;             // number of columns
	
	public void setMultipliedRow(int newRow, Fraction[] newArray) {
		array[newRow] = newArray;
		System.out.println(
		 "r" + (newRow + 1) + " is now " + Arrays.toString(newArray) + "\nFull matrix:\n" +
		  toString() + "\n\n");
	}
	
	public void setRow(int newRow, Fraction[] newArray) {
		array[--newRow] = newArray;
		System.out.println(
		 "r" + (newRow + 1) + " is now " + Arrays.toString(newArray) + "\nFull matrix:\n" +
		  toString() + "\n\n");
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
		setMultipliedRow(--row, multiply(row, multiplier));
		System.out.println("r" + (row + 1) + " multiplied by " + multiplier + "\n" + this);
		return array[row];
	}
	
	public Fraction[]
	multiply(int row, Fraction multiplier) {
		Fraction[] newArray = new Fraction[array[row].length];
		for(int i = 0; i < newArray.length; ++i) {
			Fraction temp =
			 array[row][i];
			newArray[i] = array[row][i].multiply(multiplier);
			System.
			 out.println(multiplier + " * " + temp + " = " + newArray[i]);
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
		int[] widths = new int[N];
		for(Iterator<Fraction[]> it = iterator(); it.hasNext(); ) {
			Fraction[] array = it.next();
			for(int i = 0; i < array.length; i++) {
				widths[i] = Math.max(widths[i], array[i].toString().length());
			}
		}
		StringBuilder string = new StringBuilder();
		Iterator<Fraction[]> it1 = iterator();
		for(Iterator<Fraction[]> it = iterator(); it.hasNext(); ) {
			int j = 0;
			Fraction[] array = it.next();
			string.append("[");
			for(int i = 0; i < array.length; i++) {
				string.append(array[i].toString());
				String space = " ".repeat(widths[j++] - array[i].toString().length());
				if(i < array.length - 1) {
					string.append(space).append("   ");
				}
				else {
					string.append(space).append("]\n");
				}
			}
		}
		return string.toString();
	}
	
	@Override public Iterator<Fraction[]> iterator() {
		return Arrays.stream(array).iterator();
	}
	//}
}
