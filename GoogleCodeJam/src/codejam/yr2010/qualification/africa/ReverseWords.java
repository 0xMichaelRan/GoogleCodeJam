package codejam.yr2010.qualification.africa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReverseWords {
	static BufferedWriter writer;
	static BufferedReader reader;

	public static void main(String[] args) throws IOException {

		File inFile = new File("B-small-practice.in");
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
			String oneLine = reader.readLine();
			String oneSolution = reverseWord(c, oneLine);
			solution.append(oneSolution + "\n");
			System.out.println("Print to output file " + oneSolution);
			System.out.println();
		}
		System.out.println(solution);
		writer.write(solution.toString());
		writer.close();
	}

	private static String reverseWord(int index, String one) {
		String[] wordList = one.split(" ");
		String result = "";
		for (int w = wordList.length - 1; w >= 0; w--) {
			if (w != 0)
				result += wordList[w] + " ";
			else
				result += wordList[w];
		}
		return "Case #" + ++index + ": " + result;
	}
}