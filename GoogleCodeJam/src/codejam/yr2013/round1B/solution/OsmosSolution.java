package codejam.yr2013.round1B.solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class OsmosSolution {
	private static int moteAfterOperations = 0;

	public static int getMinimumOperations(int mote, ArrayList<Integer> moteList) {
		int operations = 0;
		int totalMotes = moteList.size();
		if (mote == 1) {// Special case: as here we cant add any motes, so
						// remove all the motes
			return totalMotes;
		}
		Collections.sort(moteList);
		int count = 0;
		for (int nextMote : moteList) {
			count++;
			int temOperations = getOperations(mote, nextMote);
			// If required operations are greater than or equal to remaining
			// motes,
			// remove all remaining motes
			if (temOperations >= totalMotes - count + 1) {
				int temp = operations + (totalMotes - count + 1);
				// This check is very important, my submission was InCorrect
				// because of this check.
				if (temp > totalMotes) {
					temp = totalMotes;
				}
				return temp;
			}
			if (temOperations > 0) {
				operations += temOperations;
				mote = moteAfterOperations;
			}
			mote = mote + nextMote;
			// At any point of time if number of operations is greater than
			// total number of motes,
			// return total number of motes, as we can remove them all to begin
			// with
			if (operations >= totalMotes) {
				return totalMotes;
			}
		}
		return operations;
	}

	/*
	 * Returns the number of operations required to become > nextMote
	 */
	private static int getOperations(int mote, int destMote) {
		int ret = 0;
		while (destMote >= mote) {
			ret++;
			mote += (mote - 1);
			moteAfterOperations = mote;
		}
		return ret;
	}

	public static void main(String[] argv) {
		try {
			long startTime = System.currentTimeMillis();
			Reader reader = new FileReader("test.in");
			BufferedReader bufReader = new BufferedReader(reader);
			String x = bufReader.readLine();
			int numOfTestCases = Integer.parseInt(x);
			int count = 0;

			File file = new File("solution.out");
			FileWriter writer = new FileWriter(file);
			for (count = 1; count <= numOfTestCases; count++) {
				ArrayList<Integer> firstLine = getIntegerList(bufReader
						.readLine());
				ArrayList<Integer> secondLine = getIntegerList(bufReader
						.readLine());
				String output = getMinimumOperations(firstLine.get(0),
						secondLine) + "";
				writer.write("Case #" + count + ": " + output + "\n");
				System.out.println("Case #" + count + ": " + output);
			}
			writer.close();
			System.out.println("Total time = "
					+ (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<Integer> getIntegerList(String s) {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		String[] strArr = s.split(" ");
		for (String str : strArr) {
			intList.add(Integer.parseInt(str.trim()));
		}
		return intList;
	}
}