package codejam.yr2013.round1A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;

public class Bullseye {

	public static void main(String[] args) throws Exception {
		Bullseye main = new Bullseye();
		String problemIndex = "A";
		String problemDataSet = "large";
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
				Bullseye.class.getResource(input).toURI())));
		PrintWriter out = new PrintWriter(new FileWriter(output));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			BigDecimal radius, total;
			String[] line = in.readLine().split(" ");
			radius = new BigDecimal(line[0]);
			total = new BigDecimal(line[1]);
			String ret;
			// this method may not be good, but can use though
			// must read other people's solution and see
			if (radius.compareTo(new BigDecimal("10000000000")) > 0)
				ret = solve2(t, radius, total);
			else
				ret = solve(t, radius, total);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private static String solve(int index, BigDecimal radius, BigDecimal total) {
		BigDecimal part1 = radius.multiply(new BigDecimal("2")).subtract(
				new BigDecimal("1"));
		BigDecimal part2 = total.multiply(new BigDecimal("8"));
		double part3 = Math
				.sqrt(part1.multiply(part1).add(part2).doubleValue());
		BigDecimal part4 = new BigDecimal(part3, MathContext.DECIMAL64);
		BigDecimal part5 = part4.subtract(part1).divide(new BigDecimal("4"));
		int y = part5.intValue();
		// now I get the value of y, however it may not be accurate.
		// calculate the value of realTotal using y and radius, see if the
		// result is less than total
		BigDecimal realTotal = part1.multiply(new BigDecimal(y)).add(
				new BigDecimal(2).multiply(new BigDecimal(y)).multiply(
						new BigDecimal(y)));
		while (realTotal.compareTo(total) > 0) {
			y--;
			realTotal = part1.multiply(new BigDecimal(y)).add(
					new BigDecimal(2).multiply(new BigDecimal(y)).multiply(
							new BigDecimal(y)));
		}
		return "Case #" + index + ": " + y;
	}

	private static String solve2(int index, BigDecimal radius, BigDecimal total) {
		int y = 1;
		BigDecimal part1 = radius.multiply(new BigDecimal("2")).subtract(
				new BigDecimal("1"));
		BigDecimal realTotal = part1.multiply(new BigDecimal(y)).add(
				new BigDecimal(2).multiply(new BigDecimal(y)).multiply(
						new BigDecimal(y)));
		while (realTotal.compareTo(total) <= 0) {
			y++;
			realTotal = part1.multiply(new BigDecimal(y)).add(
					new BigDecimal(2).multiply(new BigDecimal(y)).multiply(
							new BigDecimal(y)));
		}
		return "Case #" + index + ": " + --y;
	}
}
