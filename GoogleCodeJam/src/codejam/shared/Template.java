package codejam.shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Ran
 * @time
 */

public class Template {

	public static void main(String[] args) throws Exception {
		Template main = new Template();
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
				Template.class.getResource(input).toURI())));
		PrintWriter out = new PrintWriter(new FileWriter(output));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			System.out.print("Case #" + t + ": ");
			out.write("Case #" + t + ": ");
			String[] line = in.readLine().split("\\s");

			String ret = solve(line);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private String solve(String[] line) {
		return line[0];
	}
}