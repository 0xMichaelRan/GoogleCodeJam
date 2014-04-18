package codejam.yr2013.round1A.solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.math.BigInteger;

import codejam.shared.CommonMethods;

/*
 * Comment from Ran:
 * 
 * This is same as my method
 * Except that he have a better sqrt(BigInteger) method but I don't have
 * 
 * I have put the sqrt method in a common class, for future use.
 */

public class Bullseye2 {

	private static final BigInteger TWO = BigInteger.valueOf(2);
	private static BufferedReader bufReader;

	public static String getCircleCount(BigInteger r, BigInteger t) {
		BigInteger b = r.multiply(TWO).subtract(new BigInteger("1"));
		BigInteger ans = b.multiply(b).add(t.multiply(new BigInteger("8")));
		ans = CommonMethods.sqrt(ans).subtract(b).divide(new BigInteger("4"));
		return ans.toString();
	}

	public static void main(String[] argv) {
		try {
			long startTime = System.currentTimeMillis();
			Reader reader = new FileReader("test.in");
			bufReader = new BufferedReader(reader);
			String x = bufReader.readLine();
			int numOfTestCases = Integer.parseInt(x);
			int count = 0;

			File file = new File("answer.out");
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