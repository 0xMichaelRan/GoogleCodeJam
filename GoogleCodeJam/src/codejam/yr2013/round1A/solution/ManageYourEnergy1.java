package codejam.yr2013.round1A.solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * @author antonio081014
 * @time Apr 28, 2013, 2:28:09 PM
 */

public class ManageYourEnergy1 {
	BigInteger two = new BigInteger("2");

	public static void main(String[] args) throws Exception {
		ManageYourEnergy1 main = new ManageYourEnergy1();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("test.in"));
		PrintWriter out = new PrintWriter(new FileWriter("answer.out"));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			out.write("Case #" + t + ": ");
			String[] lines = in.readLine().split("\\s");
			long E = Long.parseLong(lines[0]);
			long R = Long.parseLong(lines[1]);
			int N = Integer.parseInt(lines[2]);
			lines = in.readLine().split("\\s");
			long[] array = new long[N];
			for (int i = 0; i < N; i++) {
				array[i] = Long.parseLong(lines[i]);
			}
			String ret = solve(E, R, array);
			out.write(ret + "\n");
		}
		in.close();
		out.close();
	}

	/**
	 * Solving each case;
	 * */
	private String solve(long E, long R, long[] array) {
		int N = array.length;
		long current = E;
		// This is tricky.
		if (R > E)
			R = E;
		BigInteger total = new BigInteger("0");
		long n = (E + R - 1) / R;
		for (int i = 0; i < N; i++) {
			int next = nextMax(array, i, n);
			// System.out.println(next);
			// When current position holds the max value;
			if (next == -1) {
				total = total.add(new BigInteger(Long.toString(array[i]))
						.multiply(new BigInteger(Long.toString(current))));
				current = R;
			} else {
				// the next position holds the max value, so current position
				// might need to save some energy for the next position.

				// Energy could be consumed at ith;
				long x = current + (next - i) * R - E;
				if (x < 0)
					x = 0;
				if (x > current)
					x = current;
				total = total.add(new BigInteger(Long.toString(array[i]))
						.multiply(new BigInteger(Long.toString(x))));
				current = Math.min(current - x + R, E);
			}
			// System.out.println("Total: " + total.toString());
		}
		return total.toString();
	}

	/**
	 * Finding the first position which holds an equal or greater value than the
	 * current position, ranges from current+1 to current +n;
	 * 
	 * @return -1, if current position holds the maximum value. <br/>
	 *         i, if ith value is the first value equal or greater than the
	 *         current value.
	 * */
	private int nextMax(long[] array, int current, long n) {
		for (int i = current + 1; i < array.length && i < current + 1 + n; i++) {
			if (array[i] >= array[current])
				return i;
		}
		return -1;
	}

}