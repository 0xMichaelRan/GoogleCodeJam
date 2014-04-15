package codejam.yr2014.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ran
 * @time
 */

public class MinesweeperMaster {

	public static void main(String[] args) throws Exception {
		MinesweeperMaster main = new MinesweeperMaster();
		String problemIndex = "C";
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
			System.out.println("Case #" + t);
			out.write("Case #" + t + ":\n");
			String[] lines = in.readLine().split("\\s");
			int r = Integer.parseInt(lines[0]);
			int c = Integer.parseInt(lines[1]);
			int m = Integer.parseInt(lines[2]);
			String[] ret = startRecurse(r, c, m);
			for (String str : ret) {
				System.out.println(str);
				out.write("" + str + "\n");
			}
		}
		in.close();
		out.close();
	}

	private String[] startRecurse(int r, int c, int mines) {
		int nonMine = r * c - mines;
		Pair[] ans = null;
		int clickX = 0, clickY = 0;
		for (int a = 0; a < r; a++) {
			for (int b = 0; b < c; b++) {
				Pair[] click = new Pair[1];
				click[0] = new Pair(a, b);
				ans = solve(nonMine, r, c, new Pair[0], click);
				if (ans != null) {
					clickY = b;
					break;
				}
			}
			if (ans != null) {
				clickX = a;
				break;
			}
		}
		if (ans != null) {
			// means I found the answer
			char[][] table = new char[r][c];
			for (int k = 0; k < r; k++) {
				for (int l = 0; l < c; l++) {
					if (k == clickX && l == clickY)
						table[k][l] = 'c';
					else if (include(ans, k, l)) {
						table[k][l] = '.';
					} else {
						table[k][l] = '*';
					}
				}
			}
			String[] formatAns = new String[r];
			for (int u = 0; u < formatAns.length; u++) {
				formatAns[u] = "";
				for (char ch : table[u]) {
					formatAns[u] += ch;
				}
			}
			return formatAns;
		} else {
			String[] notFound = new String[1];
			notFound[0] = "Impossible";
			return notFound;
		}
	}

	private Pair[] solve(int nonMine, int r, int c, Pair[] p0, Pair[] p1) {
		if (r == 2 || c == 2) {
			if (nonMine % 2 == 1) {
				return null;
			}
		}
		// p0 is the nodes that are numbered 0 (not adjacent to mine)
		// p1 is the nodes that are not numbered 0 (adjacent to some mine)
		int curTotal = p0.length + p1.length;
		if (nonMine == curTotal) {
			// return this configuration (which is p0 + p1)
			Pair[] ans = new Pair[curTotal];
			for (int i = 0; i < p0.length; i++)
				ans[i] = p0[i];
			for (int j = 0; j < p1.length; j++)
				ans[p0.length + j] = p1[j];
			return ans;
		}
		if (nonMine < curTotal)
			return null;
		// otherwise, it must be that nonMine > curTotal
		// repete the following procedure, until find nonMine = curTotal
		for (Pair p : p1) {
			List<Pair> adjacentNodes = new ArrayList<Pair>();
			for (int m = p.a - 1; m <= p.a + 1; m++) {
				if (m < 0 || m >= r)
					continue;
				for (int n = p.b - 1; n <= p.b + 1; n++) {
					if (n < 0 || n >= c)
						continue;
					// now the point (m, n) is a valid point
					// if (p.a == 0 && p.b == 5) {
					// System.out.println("why");
					// }
					if (include(p0, m, n) || include(p1, m, n))
						continue;
					else {
						adjacentNodes.add(new Pair(m, n));
					}
				}
			}
			// now adjacentNodes is found, do the following 3 things:
			// 1. add P to p0
			// 2. remove P from p1
			// 3. add adjacentNodes to p1
			int size0 = p0.length + 1;
			int size1 = p1.length - 1 + adjacentNodes.size();
			if (size1 < 0)
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			Pair[] newP0 = new Pair[size0];
			Pair[] newP1 = new Pair[size1];
			// 1. add P to p0
			for (int i = 0; i < newP0.length - 1; i++) {
				newP0[i] = p0[i];
			}
			newP0[newP0.length - 1] = p;
			// 2. remove P from p1
			int j = 0;
			for (int i = 0; i < p1.length; i++) {
				if (p.a == p1[i].a && p.b == p1[i].b) {
				} else {
					newP1[j] = p1[i];
					j++;
				}
			}
			// 3. add adjacentNodes to p1
			for (int i = p1.length - 1; i < newP1.length; i++) {
				newP1[i] = adjacentNodes.get(i - p1.length + 1);
			}
			Pair[] possibleConfig = solve(nonMine, r, c, newP0, newP1);
			if (possibleConfig == null)
				;// do nothing
			else
				return possibleConfig;
		}
		// executing until this point and still does not have solution
		return null;
	}

	private boolean include(Pair[] list, int a, int b) {
		for (Pair p : list) {
			if (p.a == a && p.b == b)
				return true;
		}
		return false;
	}

	public class Pair {
		int a, b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}