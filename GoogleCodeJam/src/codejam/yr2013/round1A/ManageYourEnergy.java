package codejam.yr2013.round1A;

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

public class ManageYourEnergy {

	/*
	 * Summary:
	 * 
	 * I spend a long time reading this algorithm, and spend almost 2 hours to
	 * finally write it down in code It takes too long for a question that I
	 * already know the algorithm. The speed needs improve very very much.
	 * 
	 * The main issue of the code is to determine when do I update
	 * currentEnergy. I spent a lot of time on this and in the end I somehow
	 * made it work.
	 * 
	 * Now there are 2 solutions in this class. One is written by referring to
	 * http://puzzlersworld.com/. The other one is written entirely by me, which
	 * works only for small input.
	 */

	public static void main(String[] args) throws Exception {
		ManageYourEnergy main = new ManageYourEnergy();
		String problemIndex = "B";
		String problemDataSet = "large";
		String input = problemIndex + "-" + problemDataSet + "-practice.in";
		// String input = "test.in";
		String output = "test.out";
		main.run(input, output);
		System.exit(0);
	}

	private void run(String input, String output) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(
				ManageYourEnergy.class.getResource(input).toURI())));
		PrintWriter out = new PrintWriter(new FileWriter(output));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			out.write("Case #" + t + ": ");
			String[] line = in.readLine().split("\\s");
			int E = Integer.parseInt(line[0]);
			int R = Integer.parseInt(line[1]);
			int N = Integer.parseInt(line[2]);
			int[] values = new int[N];
			String[] line2 = in.readLine().split("\\s");
			for (int i = 0; i < N; i++) {
				values[i] = Integer.parseInt(line2[i]);
			}
			BigInteger ret = solve(E, R, values);
//			int ret_small = solve_small(E, R, values);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private BigInteger solve(int E, int R, int[] values) {
		if (R > E)
			R = E;
		int n = (E - 1) / R + 1;
		// n is the times needed to regain a full amount of E
		int canConsume, nextLarge, currentEnergy = E;
		BigInteger totalEnergy = new BigInteger("0");
		for (int i = 0; i < values.length; i++) {
			nextLarge = this.findNextLarger(i, n, values);
			if (nextLarge != -1) {
				// this means that I must save energe from current (i)th
				// activity to (nextLarge)th, in order to give entire E to the
				// (nextLarge)th activity
				canConsume = currentEnergy + (R * (nextLarge - i)) - E;
				if (canConsume < 0)
					canConsume = 0;
				if (canConsume > E)
					canConsume = E;
				currentEnergy = currentEnergy + R - canConsume;
			} else {
				canConsume = currentEnergy;
				currentEnergy = R;
			}
			totalEnergy = totalEnergy.add(new BigInteger(Integer
					.toString(canConsume)).multiply(new BigInteger(Integer
					.toString(values[i]))));
			// totalEnergy += canConsume * values[i];
			// declare totalEnergy as int and do this, only work for small input
		}
		return totalEnergy;
	}

	private int solve_small(int E, int R, int[] values) {
		if (R > E)
			R = E;
		int canConsume, nextLarge, currentEnergy = E, totalEnergy = 0;
		int n = (E - 1) / R + 1;
		for (int i = 0; i < values.length; i++) {
			nextLarge = this.findNextLarger(i, n, values);
			if (nextLarge != -1) {
				canConsume = currentEnergy + (R * (nextLarge - i)) - E;
				if (canConsume < 0)
					canConsume = 0;
				if (canConsume > E)
					canConsume = E;
				totalEnergy += canConsume * values[i];
				currentEnergy = currentEnergy + R - canConsume;
			} else {
				canConsume = currentEnergy;
				totalEnergy += canConsume * values[i];
				currentEnergy = R;
			}
		}
		return totalEnergy;
	}

	private int findNextLarger(int start, int range, int[] array) {
		for (int i = 0; i < range; i++) {
			if (i + start >= array.length)
				return -1;
			if (array[i + start] > array[start]) {
				return i + start;
			}
		}
		return -1;
	}
}