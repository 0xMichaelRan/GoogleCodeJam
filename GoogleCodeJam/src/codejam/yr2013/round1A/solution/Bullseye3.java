package codejam.yr2013.round1A.solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.math.BigInteger;

public class Bullseye3 {

	private static final BigInteger TWO = BigInteger.valueOf(2);

	private static boolean isSqrt(BigInteger n, BigInteger root) {
		final BigInteger lowerBound = root.pow(2);
		final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
		return lowerBound.compareTo(n) <= 0 && n.compareTo(upperBound) < 0;
	}

	public static BigInteger sqrt(BigInteger n) {
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

	public static String getCircleCount(BigInteger r, BigInteger t) {
		BigInteger b = r.multiply(new BigInteger("2")).subtract(
				new BigInteger("1"));
		// BigInteger a = new BigInteger("2");
		BigInteger ans = b.multiply(b).add(t.multiply(new BigInteger("8")));
		ans = sqrt(ans).subtract(b).divide(new BigInteger("4"));
		return ans.toString();
	}

	public static void main(String[] argv) {
		try {
			long startTime = System.currentTimeMillis();
			Reader reader = new FileReader("test.in");
			BufferedReader bufReader = new BufferedReader(reader);
			String x = bufReader.readLine();
			int numOfTestCases = Integer.parseInt(x);
			int count = 0;

			File file = new File("ans.out");
			FileWriter writer = new FileWriter(file);
			for (count = 1; count <= numOfTestCases; count++) {
				String line = bufReader.readLine();
				BigInteger r = new BigInteger(line.substring(0,
						line.indexOf(" ")));
				BigInteger t = new BigInteger(
						line.substring(line.indexOf(" ") + 1));
				String output = getCircleCount(r, t);
				writer.write("Case #" + count + ": " + output + "\n");
				System.out.println("Case #" + count + ": " + output);
			}
			writer.close();
			System.out.println("Total time = "
					+ (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}