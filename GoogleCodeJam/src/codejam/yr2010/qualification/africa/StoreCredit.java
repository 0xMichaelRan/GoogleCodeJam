package codejam.yr2010.qualification.africa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class StoreCredit {
	static BufferedWriter writer;
	static BufferedReader reader;

	public static void main(String[] args) throws IOException {

		File inFile = new File("A-large-practice.in");
		File outFile = new File("my-output.out");
		StringBuilder solution = new StringBuilder();

		FileWriter fwriter = new FileWriter(outFile);
		writer = new BufferedWriter(fwriter);
		FileReader freader = new FileReader(inFile);
		reader = new BufferedReader(freader);

		int numCases = 0;
		numCases = Integer.parseInt(reader.readLine());
		System.out.println("Read the following " + numCases + " input: ");
		for (int c = 0; c < numCases; c++) {
			// for each test case, there are 3 lines to be read
			int credit = Integer.parseInt(reader.readLine());
			int numItems = Integer.parseInt(reader.readLine());
			int[] prices = new int[numItems];
			String[] priceList = reader.readLine().split(" ");
			for (int d = 0; d < prices.length; d++) {
				prices[d] = Integer.parseInt(priceList[d]);
			}

			System.out.println(credit + " " + numItems);
			for (int e : prices) {
				System.out.print(e + " ");
			}
			System.out.println();
			String oneSolution = matchCredit(c, credit, prices);
			solution.append(oneSolution + "\n");
			System.out.println("Print to output file " + oneSolution);
			System.out.println();
		}
		System.out.println(solution);
		writer.write(solution.toString());
		writer.close();
	}

	private static String matchCredit(int index, int credit, int[] prices) {
		int[] sortedP = new int[prices.length];
		for (int f = 0; f < prices.length; f++) {
			sortedP[f] = prices[f];
		}
		Arrays.sort(sortedP);
		int left = 0, right = sortedP.length - 1;
		while (sortedP[left] + sortedP[right] != credit) {
			if (sortedP[left] + sortedP[right] > credit)
				right--;
			else if (sortedP[left] + sortedP[right] < credit)
				left++;
		}
		System.out.println("Solution found: " + sortedP[left] + " and "
				+ sortedP[right]);
		// find sortedP[left] and sortedP[right] from prices[]
		int first = 0, next = 0;
		for (int g = 0; g < prices.length; g++) {
			if (prices[g] == sortedP[left]) {
				first = g;
				next = sortedP[right];
				break;
				// if found sortedP[left] first
			}
			if (prices[g] == sortedP[right]) {
				first = g;
				next = sortedP[left];
				break;
			}
			// next round i'm gonna search for 'next'
			// instead of sortedP[left] and sortedP[right]
		}
		int second = 0;
		for (int h = first + 1; h < prices.length; h++) {
			if (prices[h] == next) {
				second = h;
				break;
			}
		}
		System.out.println("Validate: " + prices[first] + " + "
				+ prices[second] + " = " + credit);
		return "Case #" + ++index + ": " + ++first + " " + ++second;
	}
}
