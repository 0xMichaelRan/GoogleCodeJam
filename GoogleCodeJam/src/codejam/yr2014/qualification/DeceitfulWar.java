package codejam.yr2014.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Ran
 * @time
 */
public class DeceitfulWar {

	public static void main(String[] args) throws Exception {
		DeceitfulWar main = new DeceitfulWar();
		String problemIndex = "D";
		String problemDataSet = "large";
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

			int len = Integer.parseInt(in.readLine());
			String[] lines = in.readLine().split(" ");
			double[] w1 = new double[len];
			for (int i = 0; i < len; i++) {
				w1[i] = Double.parseDouble(lines[i]);
			}
			Arrays.sort(w1);
			lines = in.readLine().split(" ");
			double[] w2 = new double[len];
			for (int i = 0; i < len; i++) {
				w2[i] = Double.parseDouble(lines[i]);
			}
			Arrays.sort(w2);
			String ret = solve(t, w1, w2);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private String solve(int index, double[] w1, double[] w2) {
		int pointer = 0;
		int firstResult = 0;
		while (pointer < w1.length) {
			int counter = 0;
			for (int i = pointer; i < w1.length; i++) {
				if (w1[i] > w2[i - pointer])
					counter++;
			}
			if (counter > firstResult)
				firstResult = counter;
			pointer++;
		}
		pointer = 0;
		int secondResult = 0;
		while (pointer < w2.length) {
			int counter = 0;
			for (int i = pointer; i < w2.length; i++) {
				if (w2[i] > w1[i - pointer])
					counter++;
			}
			if (counter > secondResult)
				secondResult = counter;
			pointer++;
		}
		return firstResult + " " + (w1.length - secondResult);
	}

}