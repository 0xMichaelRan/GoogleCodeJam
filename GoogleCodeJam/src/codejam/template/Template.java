package codejam.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import codejam.yr2014.qualification.MinesweeperMaster;

/**
 * @author Ran
 * @time
 */
public class Template {

	public static void main(String[] args) throws Exception {
		Template main = new Template();
		String problemIndex = "A";
		String problemDataSet = "small";
		main.run(problemIndex + "-" + problemDataSet + "-practice.in",
				"answer.out");
		System.exit(0);
	}

	private void run(String input, String output) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(
				MinesweeperMaster.class.getResource(input).toURI())));
		PrintWriter out = new PrintWriter(new FileWriter(output));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			out.write("Case #" + t + ": ");
			String[] lines = in.readLine().split("\\s");

			String ret = solve();
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private String solve() {
		return "";
	}

}