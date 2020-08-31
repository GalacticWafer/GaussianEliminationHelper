import java.util.Scanner;

public class Fraction {
	int numerator;
	int denominator;
	
	/**
	 * Constructor
	 *
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		reduce();
	}
	
	public static Fraction[][] array(Fraction[][] array2d) {
		Fraction[][] fractions;
		int i = 0;
		int j = 0;
		for(Fraction[] doubles: array2d) {
			i++;
			for(Fraction d: doubles) {
				if(i == 0) {
					j++;
				}
			}
		}
		fractions = new Fraction[i][j];
		for(int k = 0; k < array2d.length; k++) {
			for(int l = 0; l < j; l++) {
				fractions[k][l] =new Fraction((int)array2d[k][l].numerator, 1);
			}
		}
		return fractions;
	}
	
	public static Fraction parseFraction(String input) {
		try{
			if(input.contains("/")){
				String[] split = input.split("/");
				return new Fraction(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			} else {
				return new Fraction(Integer.parseInt(input), 1);
			}
		} catch(Exception e) {
			System.out.println("That's not a valid fraction... try again");
			return new Fraction(1,1);
		}
	}
	
	public static Fraction nextFraction(Scanner scanner) {
		return parseFraction(scanner.next());
	}
	
	public static Fraction[][] toFractions(String[][] matrix) {
		return null;
	}
	
	public int getNumerator() {
		return numerator;
	}
	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
	public int getDenominator() {
		return denominator;
	}
	@Override public boolean equals(Object obj) {
		if(obj instanceof Fraction) {
			Fraction fraction = (Fraction)obj;
			return fraction.denominator == denominator && fraction.numerator == numerator;
		} else if (obj instanceof Double) {
			return Math.abs(numerator * 1.0 / denominator - (Double)obj) <= 0.000000;
		} else if (obj instanceof Integer) {
			return Math.abs(numerator * 1.0 / denominator - (Integer)obj) <= 0.000000; 
		}
		return false;
	}
	
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	/**
	 * Calculates gcd of two numbers
	 *
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public int calculateGCD(int numerator, int denominator) {
		if(numerator % denominator == 0) {
			return denominator;
		}
		return calculateGCD(denominator, numerator % denominator);
	}
	
	/**
	 * Reduce the fraction to lowest form
	 */
	void reduce() {
		int gcd = calculateGCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}
	
	/**
	 * Adds two fractions
	 *
	 * @param fractionTwo
	 * @return
	 */
	public Fraction add(Fraction fractionTwo) {
		int numer = (numerator * fractionTwo.getDenominator()) +
		 (fractionTwo.getNumerator() * denominator);
		int denr = denominator * fractionTwo.getDenominator();
		return new Fraction(numer, denr);
	}
	
	/**
	 * Subtracts two fractions
	 *
	 * @param fractionTwo
	 * @return
	 */
	public Fraction subtract(Fraction fractionTwo) {
		int newNumerator = (numerator * fractionTwo.denominator) -
		 (fractionTwo.numerator * denominator);
		int newDenominator = denominator * fractionTwo.denominator;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}
	
	/**
	 * Multiples two functions
	 *
	 * @param fractionTwo
	 * @return
	 */
	public Fraction multiply(Fraction fractionTwo) {
		int newNumerator = numerator * fractionTwo.numerator;
		int newDenominator = denominator * fractionTwo.denominator;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}
	
	/**
	 * Divides two fractions
	 *
	 * @param fractionTwo
	 * @return
	 */
	public Fraction divide(Fraction fractionTwo) {
		int newNumerator = numerator * fractionTwo.getDenominator();
		int newDenominator = denominator * fractionTwo.numerator;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}
	
	/**
	 * Returns string representation of the fraction
	 */
	@Override
	public String toString() {
		if(this.denominator == 1) {
			return this.numerator + "";
		}
		return this.numerator + "/" + this.denominator;
	}
	
	public static void main(String[] args) {
		Fraction f1 = new Fraction(25, 35);
		System.out.println(f1.toString());
		Fraction f2 = new Fraction(2, 7);
		Fraction f3 = f1.add(f2);
		System.out.println("Result of addition of "
		 + f1 + " and " + f2 + " is : " + f3);
		f3 = f1.subtract(f2);
		System.out.println("Result of subtraction of "
		 + f1 + " and " + f2 + " is : " + f3);
		f3 = f1.divide(f2);
		System.out.println("Result of division of "
		 + f1 + " and " + f2 + " is : " + f3);
		f3 = f1.multiply(f2);
		System.out.println("Result of multiplication of "
		 + f1 + " and " + f2 + " is : " + f3);
	}
}
