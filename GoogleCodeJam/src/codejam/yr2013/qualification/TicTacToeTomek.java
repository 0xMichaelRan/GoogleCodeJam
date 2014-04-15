package codejam.yr2013.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Tic-Tac-Toe-Tomek
// https://code.google.com/codejam/contest/2270488/dashboard#s=p0

public class TicTacToeTomek {
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
		System.out.println("Read the following " + numCases + " input: ");
		for (int ca = 0; ca < numCases; ca++) {
			String[] oneCase = new String[4];
			for (int bw = 0; bw < 4; bw++) {
				oneCase[bw] = reader.readLine();
			}
			String oneSolution = determine(ca, oneCase);
			solution.append(oneSolution + "\n");
			System.out.println("Print to output file " + oneSolution);
			reader.readLine();
		}
		writer.write(solution.toString());
		writer.close();
	}

	private static String determine(int index, String[] oneCase) {
		char[][] in = new char[4][4];
		for (int m = 0; m < 4; m++) {
			in[m] = oneCase[m].toCharArray();
		}
		boolean full = true;
		// check row
		for (int r = 0; r < 4; r++) {
			// for row#r
			int x = 0;
			int o = 0;
			int t = 0;
			for (char foo : in[r]) {
				if (foo == 'X')
					x++;
				if (foo == 'O')
					o++;
				if (foo == 'T')
					t++;
				if (foo == '.')
					full = false;
			}
			if (x + t == 4)
				return "Case #" + ++index + ": " + "X won";
			if (o + t == 4)
				return "Case #" + ++index + ": " + "O won";
		}
		// check column
		for (int co = 0; co < 4; co++) {
			// for row#r
			int x = 0;
			int o = 0;
			int t = 0;
			for (char[] foo : in) {
				if (foo[co] == 'X')
					x++;
				if (foo[co] == 'O')
					o++;
				if (foo[co] == 'T')
					t++;
			}
			if (x + t == 4)
				return "Case #" + ++index + ": " + "X won";
			if (o + t == 4)
				return "Case #" + ++index + ": " + "O won";
		}
		// check top-left to bottom-right diagonal
		int x = 0;
		int o = 0;
		int t = 0;
		for (int i = 0; i < 4; i++) {
			int val = in[i][i];
			if (val == 'X')
				x++;
			if (val == 'O')
				o++;
			if (val == 'T')
				t++;
		}
		if (x + t == 4)
			return "Case #" + ++index + ": " + "X won";
		if (o + t == 4)
			return "Case #" + ++index + ": " + "O won";
		// check the other diagonal
		x = 0;
		o = 0;
		t = 0;
		for (int i = 0; i < 4; i++) {
			int val = in[i][3 - i];
			if (val == 'X')
				x++;
			if (val == 'O')
				o++;
			if (val == 'T')
				t++;
		}
		if (x + t == 4)
			return "Case #" + ++index + ": " + "X won";
		if (o + t == 4)
			return "Case #" + ++index + ": " + "O won";

		// at this point, if neither X or O won, there can be 2 results.
		// 1 is MagicTrick1 draw, and another is game not complete.
		if (full)
			return "Case #" + ++index + ": Draw";
		else
			return "Case #" + ++index + ": Game has not completed";
	}
}