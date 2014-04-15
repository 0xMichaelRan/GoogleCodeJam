package codejam.yr2013.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Lawnmower
// https://code.google.com/codejam/contest/2270488/dashboard#s=p1

public class Lawnmower {
	static BufferedWriter writer;
	static BufferedReader reader;

	public static void main(String[] args) throws IOException {

		File inFile = new File("test.in");
		File outFile = new File("test.out");
		StringBuilder solution = new StringBuilder();

		FileWriter fwriter = new FileWriter(outFile);
		writer = new BufferedWriter(fwriter);
		FileReader freader = new FileReader(inFile);
		reader = new BufferedReader(freader);

		int numCases = 0;
		numCases = Integer.parseInt(reader.readLine());
		for (int ca = 0; ca < numCases; ca++) {
			String[] mn = reader.readLine().split(" ");
			int m = Integer.parseInt(mn[0]);
			int n = Integer.parseInt(mn[1]);
			int[][] oneCase = new int[m][n];
			for (int k = 0; k < m; k++) {
				String[] inputLineM = reader.readLine().split(" ");
				for (int l = 0; l < n; l++) {
					oneCase[k][l] = Integer.parseInt(inputLineM[l]);
				}
			}
			String oneSolution = mow(ca, oneCase);
			solution.append(oneSolution + "\n");
			System.out.println("Print to output file " + oneSolution);
		}
		writer.write(solution.toString());
		writer.close();
	}

	private static String mow(int index, int[][] oneCase) {
		int m = oneCase.length;
		int n = oneCase[0].length;
		int[] colHi = new int[n];
		int[] rowHi = new int[m];

		for (int[] row : oneCase) {
			// for each row, first find the max value.
			// then, any value < max would be marked as "lawned vertically"
			int rowMax = 0;
			for (int num : row) {
				rowMax = Math.max(rowMax, num);
			}
			for (int v = 0; v < row.length; v++) {
				if (row[v] < rowMax) {
					// this value row[v] is lawned vertically
					if (colHi[v] == 0)
						colHi[v] = row[v];
				}
			}
		}
		// now for each col, do the same as aboveint rowMax = 0;
		for (int y = 0; y < n; y++) {
			int colMax = 0;
			for (int num[] : oneCase) {
				colMax = Math.max(colMax, num[y]);
			}
			for (int v = 0; v < m; v++) {
				if (oneCase[v][y] < colMax) {
					if (rowHi[v] == 0)
						rowHi[v] = oneCase[v][y];
				}
			}
		}
		// after rowHi[] and colHi[] is got, validate oneCase
		for (int x = 0; x < rowHi.length; x++) {
			for (int y = 0; y < colHi.length; y++) {
				if (rowHi[x] == 0 && colHi[y] == 0)
					continue;
				int expectedHeight;
				if (rowHi[x] == 0 || colHi[y] == 0)
					expectedHeight = Math.max(rowHi[x], colHi[y]);
				else
					expectedHeight = Math.min(rowHi[x], colHi[y]);
				if (expectedHeight != oneCase[x][y]) {
					return "Case #" + ++index + ": NO";
				}
			}
		}
		// at this point, if still the row and col height does not violate,
		// return YES
		return "Case #" + ++index + ": YES";
	}
}
