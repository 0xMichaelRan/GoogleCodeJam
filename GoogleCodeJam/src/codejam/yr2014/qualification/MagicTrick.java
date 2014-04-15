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
public class MagicTrick {

	public static void main(String[] args) throws Exception {
		MagicTrick main = new MagicTrick();
		String problemIndex = "A";
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
			out.write("Case #" + t + ": ");
			int guess1 = Integer.parseInt(in.readLine());
			int[][] cards1 = new int[4][4];
			for (int k = 0; k < 4; k++) {
				String[] lines = in.readLine().split("\\s");
				for (int l = 0; l < 4; l++) {
					cards1[k][l] = Integer.parseInt(lines[l]);
				}
			}
			int guess2 = Integer.parseInt(in.readLine());
			int[][] cards2 = new int[4][4];
			for (int k = 0; k < 4; k++) {
				String[] lines = in.readLine().split("\\s");
				for (int l = 0; l < 4; l++) {
					cards2[k][l] = Integer.parseInt(lines[l]);
				}
			}
			String ret = solve(guess1, cards1, guess2, cards2);
			System.out.println(ret);
			out.write("" + ret + "\n");
		}
		in.close();
		out.close();
	}

	private String solve(int guess1, int[][] cards1, int guess2, int[][] cards2) {
		int[] first = cards1[guess1 - 1];
		int[] second = cards2[guess2 - 1];
		int chosen = 0, numChoose = 0;
		for (int a : first) {
			for (int b : second) {
				if (a == b) {
					chosen = a;
					numChoose++;
				}
			}
		}
		if (numChoose == 0)
			return "Volunteer cheated!";
		else if (numChoose == 1)
			return Integer.toString(chosen);
		else
			return "Bad magician!";
	}

}