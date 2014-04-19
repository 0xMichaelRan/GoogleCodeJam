package codejam.shared;

import java.math.BigInteger;

/* Methods in this class includes:
 * 
 * binomCoeff - binomial coefficient, which is the "n pick k" combinations. 
 * 				eg C(5,2) = 10 which means pick 2 out of 5
 * sqrt - return the BigInteger sqrt value
 * 
 */

public class CommonMethods {

	private static final BigInteger TWO = BigInteger.valueOf(2);

	public static double binomCoeff(double n, double k) {
		double result = 1;
		for (int i = 1; i < k + 1; i++) {
			result *= (n - i + 1) / i;
		}
		return result;
	}

	private static boolean isSqrt(BigInteger n, BigInteger root) {
		final BigInteger lowerBound = root.pow(2);
		final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
		return lowerBound.compareTo(n) <= 0 && n.compareTo(upperBound) < 0;
	}

	public static BigInteger sqrt(BigInteger n) {
		// TODO don't really understand this code
		// read this again when I'm not so sleepy ^D
		if (n.signum() >= 0) {
			final int bitLength = n.bitLength();
			BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

			while (!isSqrt(n, root)) {
				root = root.add(n.divide(root)).divide(TWO);
			}
			return root;
		} else {
			throw new ArithmeticException("square root of negative number");
		}
	}

	public static void main(String[] args) {
		System.out.println(binomCoeff(5, 2));
	}
}
