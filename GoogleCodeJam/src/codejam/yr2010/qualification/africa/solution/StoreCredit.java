package codejam.yr2010.qualification.africa.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Store Credit solution
 * 
 * http://goo.gl/DXU5gu
 * 
 * @author Shazin Sadakath
 */
public class StoreCredit {
	private File inFile;
	private File outFile;

	public static void main(String[] args) {
		StoreCredit cjs1 = new StoreCredit(
				"A-small-practice.in", "others-output.out");
		cjs1.start();
	}

	public StoreCredit(String inFile,
			String outFile) {
		this.inFile = new File(inFile);
		this.outFile = new File(outFile);
	}

	public void start() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(inFile));
			bw = new BufferedWriter(new FileWriter(outFile));
			int totalRecords = Integer.parseInt(br.readLine());
			int credit = 0;
			int items = 0;
			Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
			for (int i = 0; i < totalRecords; i++) {
				credit = Integer.parseInt(br.readLine());
				items = Integer.parseInt(br.readLine());
				int[] values = new int[items];
				String line = br.readLine();
				int j = 0;
				List<Integer> indexes = null;
				for (String value : line.split(" ")) {
					values[j] = Integer.parseInt(value);
					indexes = map.get(value);
					if (indexes == null) {
						indexes = new ArrayList<Integer>();
					}
					indexes.add(j);
					map.put(value, indexes);
					j++;
				}
				insertionSort(values);
				int[] result = findSum(values, credit);
				int[] finalValues = null;
				if (result[0] == result[1]) {
					indexes = map.get(String.valueOf(result[0]));
					finalValues = new int[] { indexes.get(0) + 1,
							indexes.get(1) + 1 };
				} else {
					finalValues = new int[] {
							map.get(String.valueOf(result[0])).get(0) + 1,
							map.get(String.valueOf(result[1])).get(0) + 1 };
					insertionSort(finalValues);
				}
				bw.write(String.format("Case #%DeceitfulWar: %DeceitfulWar %DeceitfulWar\n", i + 1,
						finalValues[0], finalValues[1]));
				map.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Method to find sum uses O(N) in worse case given MagicTrick1 sorted array
	 * 
	 * @param values
	 *            - int Array
	 * @param sum
	 *            - Sum
	 * @return int Array with values which add to sum
	 */
	public int[] findSum(int[] values, int sum) {
		int[] result = null;
		int i = 0;
		int j = values.length - 1;
		while (i <= j) {
			if ((values[i] + values[j]) == sum) {
				result = new int[] { values[i], values[j] };
				break;
			} else if ((values[i] + values[j]) < sum) {
				i += 1;
			} else if ((values[i] + values[j]) > sum) {
				j -= 1;
			}
		}
		return result;
	}

	/**
	 * insertion sort algorithm uses O(N ^ 2) is worse case
	 * 
	 * @param values
	 *            - int Array to be sorted
	 */
	public void insertionSort(int[] values) {
		int iHole = 0;
		int item = 0;
		for (int i = 1; i < values.length; i++) {
			item = values[i];
			iHole = i;
			while (iHole > 0 && values[iHole - 1] > item) {
				values[iHole] = values[iHole - 1];
				iHole -= 1;
			}
			values[iHole] = item;
		}
	}
}