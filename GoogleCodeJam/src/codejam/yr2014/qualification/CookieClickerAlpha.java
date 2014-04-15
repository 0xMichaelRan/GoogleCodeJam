package codejam.yr2014.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Ran
 * @time
 */
public class CookieClickerAlpha {

	public static void main(String[] args) throws Exception {
		CookieClickerAlpha main = new CookieClickerAlpha();
		String problemIndex = "B";
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
			String[] lines = in.readLine().split("\\s");
			double c = Double.parseDouble(lines[0]);
			double f = Double.parseDouble(lines[1]);
			double x = Double.parseDouble(lines[2]);
			double ret = solve(2, c, f, x);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private double solve(double rate, double farmPrice, double increase,
			double goal) {
		double threshold = (increase * goal - 2 * farmPrice)
				/ (increase * farmPrice);
		int numFarm = (int) threshold;
		// System.out.println("			Number of farm = " + numFarm);
		return totalTime(rate, farmPrice, increase, goal, numFarm);
	}

	private double totalTime(double rate, double farmPrice, double increase,
			double goal, int numFarm) {
		if (numFarm < 0)
			numFarm = 0;
		double sum = 0;
		for (int i = 0; i < numFarm; i++) {
			sum += (farmPrice / (2 + increase * (i)));
		}
		double finalSum = sum + (goal / (2 + increase * numFarm));
		// System.out.println("			finalSum = " + finalSum);
		return finalSum;
	}
}