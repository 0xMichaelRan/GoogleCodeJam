package codejam.yr2013.round1B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import codejam.shared.CommonMethods;

/**
 * @author Ran
 * @time
 */

public class FallingDiamonds {

	public static void main(String[] args) throws Exception {
		FallingDiamonds main = new FallingDiamonds();
		String problemIndex = "B";
		String problemDataSet = "large";
		String input = problemIndex + "-" + problemDataSet + "-practice.in";
		// String input = "test.in";
		String output = "answer.out";
		main.run(input, output);
		System.exit(0);
	}

	private void run(String input, String output) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(
				FallingDiamonds.class.getResource(input).toURI())));
		PrintWriter out = new PrintWriter(new FileWriter(output));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			out.write("Case #" + t + ": ");
			String[] lines = in.readLine().split("\\s");
			int n = Integer.parseInt(lines[0]);
			int x = Math.abs(Integer.parseInt(lines[1]));
			int y = Math.abs(Integer.parseInt(lines[2]));
			double ret = solve(n, x, y);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private double solve(int n, int x, int y) {
		int xy = x + y;
		int m = n - ((xy * xy - xy) / 2);
		int max = xy;
		int min = Math.max(0, m - xy);
		if (m <= 0)
			return 0;
		if (m >= 2 * xy + 1)
			return 1;
		// if the number of nodes in current level exceeds possible limit
		// then return either 0 or 1

		if (x == 0)
			return 0;
		// otherwise, if x=0, this node shall always be the last to place
		// so return 0

		if (y < min)
			return 1;
		if (y >= m)
			return 0;
		// this is 2 special case. Think about it.

		int combinations = 0;
		for (int i = 0; i <= (y - min); i++) {
			combinations += CommonMethods.binomCoeff(m - (2 * min), i);
		}
		System.out.println("xy is " + xy);
		System.out.println("m is " + m);
		System.out.println("max is " + max);
		System.out.println("min is " + min);
		System.out.println("	combinations is " + combinations);
		return 1 - (combinations / Math.pow(2, m - (2 * min)));
	}

}