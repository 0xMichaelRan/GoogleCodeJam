package codejam.yr2013.round1C;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * @author Ran
 * @time
 */

/*
 * Comment:
 * 
 * This solution works only for small input
 */
public class Consonants {

	public static void main(String[] args) throws Exception {
		Consonants main = new Consonants();
		String problemIndex = "A";
		String problemDataSet = "small";
		String input = problemIndex + "-" + problemDataSet + "-practice.in";
		// String input = "test.in";
		String output = "test.out";
		long startTime = System.currentTimeMillis();
		main.run(input, output);
		System.out.println("Total time = "
				+ (System.currentTimeMillis() - startTime));
		System.exit(0);
	}

	private void run(String input, String output) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(
				Consonants.class.getResource(input).toURI())));
		PrintWriter out = new PrintWriter(new FileWriter(output));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			System.out.print("Case #" + t + ": ");
			out.write("Case #" + t + ": ");
			String[] line = in.readLine().split("\\s");
			String name = line[0];
			int n = Integer.parseInt(line[1]);
			BigInteger ret = solve(name.toLowerCase(), n);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private BigInteger solve(String name, int n) {
		BigInteger total = BigInteger.valueOf(0);
		char[] letters = name.toCharArray();
		for (int j = 0; j < letters.length; j++) {
			int found = findThreeConsonants(j, n, letters);
			if (found == -1)
				return total;
			else {
				// System.out.println("currently total is " + total);
				total = total.add(BigInteger.valueOf(letters.length - found - n
						+ 1));
			}
		}
		return total;
	}

	private int findThreeConsonants(int start, int n, char[] array) {
		// find the position of the starting point of the nearest 3 consecutive
		// consonants after 'start' (inclusive)
		int num = 0;
		for (int i = start; i < array.length; i++) {
			char cur = array[i];
			if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o'
					|| cur == 'u')
				num = 0;
			else
				num++;
			if (num == n)
				return (i - n + 1);
		}
		return -1;
	}
}