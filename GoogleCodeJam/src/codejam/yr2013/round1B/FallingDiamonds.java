package codejam.yr2013.round1B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Ran
 * @time
 */
public class FallingDiamonds {

	public static void main(String[] args) throws Exception {
		FallingDiamonds main = new FallingDiamonds();
		String problemIndex = "B";
		String problemDataSet = "small";
		main.run(problemIndex + "-" + problemDataSet + "-practice.in",
				"answer.out");
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
			System.out.println(lines[0]);
			int n = Integer.parseInt(lines[0]);
			int x = Integer.parseInt(lines[1]);
			int y = Integer.parseInt(lines[2]);
			double ret = solve(n, x, y);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private double solve(int n, int x, int y) {
		return n * x * y;
	}

}