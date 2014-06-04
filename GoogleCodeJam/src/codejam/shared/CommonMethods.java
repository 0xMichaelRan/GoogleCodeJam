package codejam.shared;

import java.math.BigInteger;

/* Methods in this class includes:
 *
 * binomCoeff - binomial coefficient, which is the "n pick k" combinations.	
 * 				Eg C(5,2) = 10 which means pick 2 out of 5
 * sqrt - return the BigInteger sqrt value
 * findMaxValueFromArray
 * findMaxIndexFromArray
 * isPalindrome
 *
 */

public class CommonMethods {

	public static void main(String[] args) {
		System.out.println(sqrt(BigInteger.valueOf(16)));
	}

	public static double binomCoeff(double n, double k) {
		double result = 1;
		for (int i = 1; i < k + 1; i++) {
			result *= (n - i + 1) / i;
		}
		return result;
	}

	public static BigInteger sqrt(BigInteger n) {
		// TODO don't really understand this code
		// read this again when I'm not so sleepy ^D
		if (n.signum() >= 0) {
			final int bitLength = n.bitLength();
			BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

			while (!isSqrt(n, root)) {
				root = root.add(n.divide(root)).divide(BigInteger.valueOf(2));
			}
			return root;
		} else {
			throw new ArithmeticException("square root of negative number");
		}
	}

	private static boolean isSqrt(BigInteger n, BigInteger root) {
		final BigInteger lowerBound = root.pow(2);
		final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
		return lowerBound.compareTo(n) <= 0 && n.compareTo(upperBound) < 0;
	}

	public static int findMaxValueFromArray(int[] array) {
		return array[findMaxIndexFromArray(array)];
	}

	public static int findMaxIndexFromArray(int[] array) {
		int max = array[0];
		int maxIndex = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public boolean isPalindrome(String original) {
		String reverse = "";
		int length = original.length();
		for (int i = length - 1; i >= 0; i--)
			reverse = reverse + original.charAt(i);
		return original.equals(reverse);
	}
}
