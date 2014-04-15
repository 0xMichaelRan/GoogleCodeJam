package codejam.yr2013.round1B;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Ran
 * @time
 */
public class Osmos {

	public static void main(String[] args) throws Exception {
		Osmos main = new Osmos();
		main.run();
		System.exit(0);
	}

	private void run() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("test.in"));
		PrintWriter out = new PrintWriter(new FileWriter("test.out"));
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			out.write("Case #" + t + ": ");
			String[] lines = in.readLine().split("\\s");
			int curSize = Integer.parseInt(lines[0]);
			int[] moteSizes = new int[Integer.parseInt(lines[1])];
			lines = in.readLine().split("\\s");
			for (int i = 0; i < moteSizes.length; i++) {
				moteSizes[i] = Integer.parseInt(lines[i]);
			}
			Arrays.sort(moteSizes);
			int ret = solve(curSize, moteSizes, 0);
			System.out.println("Solution: " + ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private int solve(int cur, int[] motes, int ops) {
		if (motes.length == 0)
			return ops;
		if (cur == 1)
			return motes.length;
		int y = 0;
		while (y < motes.length && motes[y] < cur) {
			y++;
		}
		if (y == motes.length) {
			// if all the rest of elements can be absorbed, just return # of ops
			return ops;
		} else if (y > 0) {
			// if there are elements in motes that can be absorbed by current
			// size (but not all)
			int newCur = cur;
			int[] newMotes = new int[motes.length - y];
			for (int i = 0; i < newMotes.length; i++) {
				newMotes[i] = motes[i + y];
			}
			for (int i = 0; i < y; i++) {
				newCur += motes[i];
			}
			return solve(newCur, newMotes, ops);
		} else {
			// if no elements can be absorbed, make the following decision
			int deleteAll = ops + motes.length;
			int keepAdding = solve(cur * 2 - 1, motes, ++ops);
			// I made a stupid mistake here. I must calculate the deleteAll
			// first, then calculate keepAdding
			// otherwise, the ops is being added by 1, which result in wrong
			// answer.
			return Math.min(keepAdding, deleteAll);
		}
	}
}