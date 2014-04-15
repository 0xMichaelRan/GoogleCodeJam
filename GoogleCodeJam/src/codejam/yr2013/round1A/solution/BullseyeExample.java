package codejam.yr2013.round1A.solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

public class BullseyeExample {

	BigInteger two = new BigInteger("2");

	public static void main(String[] args) throws Exception {
		BullseyeExample main = new BullseyeExample();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("test.in"));
		PrintWriter out = new PrintWriter(new FileWriter("test.txt"));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			out.write("Case #" + t + ": ");
			String[] lines = in.readLine().split("\\s");
			long r = Long.parseLong(lines[0]);
			long amount = Long.parseLong(lines[1]);
			String ret = solve(r, amount);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private String solve(long r, long amount) {
		BigInteger rr = new BigInteger(Long.toString(r));
		BigInteger tt = new BigInteger(Long.toString(amount));
		BigInteger left = BigInteger.ZERO;
		BigInteger right = tt;
		while (left.compareTo(right) < 0) {
			BigInteger mid = (right.add(left).add(BigInteger.ONE)).divide(two);
			if (check(mid, rr, tt)) {
				left = mid;
			} else
				right = mid.subtract(BigInteger.ONE);
			// right = mid;
		}
		return left.toString();
	}

	private boolean check(BigInteger x, BigInteger r, BigInteger amount) {
		return x.multiply(
				x.multiply(two).add(
						r.multiply(two).subtract(new BigInteger("1"))))
				.compareTo(amount) <= 0;
		// return x * (2 * x + 2 * r - 1) <= amount;
	}
}