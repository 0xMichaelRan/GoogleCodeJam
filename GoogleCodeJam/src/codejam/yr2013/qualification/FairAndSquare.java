package codejam.yr2013.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

// Fair and Square
// https://code.google.com/codejam/contest/2270488/dashboard#s=p2

public class FairAndSquare {
	static BufferedWriter writer;
	static BufferedReader reader;

	public static void main(String[] args) throws IOException {

		File inFile = new File("test.in");
		File outFile = new File("test.out");
		StringBuilder solution = new StringBuilder();

		FileWriter fwriter = new FileWriter(outFile);
		writer = new BufferedWriter(fwriter);
		FileReader freader = new FileReader(inFile);
		reader = new BufferedReader(freader);

		int numCases = 0;
		numCases = Integer.parseInt(reader.readLine());
		for (int ca = 0; ca < numCases; ca++) {
			String[] range = reader.readLine().split(" ");
			BigInteger[] in = new BigInteger[2];
			in[0] = new BigInteger(range[0]);
			in[1] = new BigInteger(range[1]);
			String oneSolution = fairSquare(ca, in);
			solution.append(oneSolution + "\n");
			System.out.println("Print to output file " + oneSolution);
		}
		writer.write(solution.toString());
		writer.close();
	}

	private static String fairSquare(int index, BigInteger[] in) {
		long root[] = new long[2];
		int ans = 0;
		root[0] = (long) Math.ceil(sqrt(in[0]));
		root[1] = (long) Math.floor(sqrt(in[1]));
		for (long w = root[0]; w <= root[1]; w++) {
			if (!isPalindrome(w))
				continue;
			long possibleFS = w * w;
			if (isPalindrome(possibleFS)) {
				ans++;
				// System.out.println("found one - " + possibleFS);
			}
		}
		return "Case #" + ++index + ": " + ans;
	}

	private static Double sqrt(BigInteger n) {
		return Math.sqrt(n.doubleValue());
	}

	private static boolean isPalindrome(long w) {
		long copy = w, out = 0;
		while (copy != 0) {
			out = out * 10 + (copy % 10);
			copy = copy / 10;
		}
		return (w == out);
	}
}
