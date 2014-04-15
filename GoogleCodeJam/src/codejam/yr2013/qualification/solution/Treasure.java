package codejam.yr2013.qualification.solution;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Author: Ivan Voroshilin
 * @Date: April 13 2013 Problem A Small inputs Comment: Poor time complexity -
 *        O(n!) permutations
 */
public class Treasure {

	private int numOfKeysIHave;
	private int numOfChests;
	private ArrayList<Integer> keyTypesIHave = new ArrayList<Integer>();
	private List<Chest> chests = new ArrayList<Chest>();
	private Set<String> paths = new HashSet<String>();

	private static class Chest {
		Integer keyTypeToOpen;
		List<Integer> keyTypesInside;

		public Chest(int keyTypeToOpen) {
			keyTypesInside = new ArrayList<Integer>();
			this.keyTypeToOpen = keyTypeToOpen;
		}

		public void addKeyType(int keyTypeInside) {
			keyTypesInside.add(keyTypeInside);
		}

		public boolean isEmpty() {
			return keyTypesInside.isEmpty();
		}
	}

	private void solve(Scanner sc, PrintWriter pw) {

		// 1. Create data structures:
		numOfKeysIHave = sc.nextInt();
		System.out.println("numOfKeysIHave =  " + numOfKeysIHave);
		numOfChests = sc.nextInt();

		System.out.println("numOfChests =  " + numOfChests);

		keyTypesIHave = new ArrayList<Integer>();

		for (int i = 0; i < numOfKeysIHave; i++) {
			keyTypesIHave.add(sc.nextInt());
		}

		System.out.println("keyTypesIHave =  " + keyTypesIHave.toString());

		for (int i = 0; i < numOfChests; i++) {

			Chest chest = new Chest(sc.nextInt());
			System.out.println("Creating new chest " + (i + 1));

			int numOfkeysInside = sc.nextInt();
			System.out.println("numOfkeysInside =  " + numOfkeysInside);

			if (numOfkeysInside > 0)
				for (int j = 0; j < numOfkeysInside; j++) {
					chest.addKeyType(sc.nextInt());
				}

			System.out.println("keysInside =  "
					+ chest.keyTypesInside.toString());
			System.out.println("Chest's key =  " + chest.keyTypeToOpen);
			System.out.println();

			chests.add(chest);
		}

		System.out.println();
		System.out.println("-----------------SOLVING---------------------");
		System.out.println();

		// 2. Solve:
		traverse();

		List<String> listOfPaths = new ArrayList<String>(paths);
		Collections.sort(listOfPaths);
		System.out.println("The answer is: " + listOfPaths.get(0).toString());

		if (listOfPaths.get(0).isEmpty()) {
			pw.println("IMPOSSIBLE");
			return;
		}

		pw.println(listOfPaths.get(0).replace("", " ").trim());
	}

	private void traverse() {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < numOfChests; i++) {
			list.add(i);
		}
		permute(list, 0);
	}

	private void permute(List<Integer> list, int k) {

		for (int i = k; i < list.size(); i++) {
			java.util.Collections.swap(list, i, k);
			permute(list, k + 1);
			java.util.Collections.swap(list, k, i);
		}

		if (k == list.size() - 1) {
			System.out.println("--------------------------------------: "
					+ list.toString());
			paths.add(traverse(list));
		}
	}

	private String traverse(List<Integer> listOfChests) {

		List<Integer> keyTypesIHaveCopy = new ArrayList<Integer>(keyTypesIHave);

		int chestCounter = 0;
		int openChests = 0;

		int loopCounter = 0;

		String sequence = "";

		while (keyTypesIHaveCopy.size() > 0 && chestCounter < numOfChests) {

			loopCounter++;

			if (loopCounter > 100) {
				break;
			}

			Integer chestNumber = listOfChests.get(chestCounter);
			Chest chest = chests.get(chestNumber);

			if (keyTypesIHaveCopy.size() == 1 && chest.isEmpty()
					&& openChests == 0) {
				chestCounter = incrementChestNumber(chestCounter, numOfChests);
				continue;
			}

			if (keyTypesIHaveCopy.contains(chest.keyTypeToOpen)) {
				System.out.println("keyTypesIHave = "
						+ keyTypesIHaveCopy.toString()
						+ " chest.keyTypeToOpen = " + chest.keyTypeToOpen);
				keyTypesIHaveCopy.remove(chest.keyTypeToOpen);
				keyTypesIHaveCopy
						.addAll(chests.get(chestNumber).keyTypesInside);
				System.out.println("And now keyTypesIHave = "
						+ keyTypesIHaveCopy.toString());
				System.out.println("Chest " + (chestNumber + 1) + " is open");
				openChests++;
				sequence += (chestNumber + 1);
			}

			chestCounter = incrementChestNumber(chestCounter, numOfChests);
		}

		return sequence;
	}

	private static int incrementChestNumber(int currentChestNum, int limit) {
		if (currentChestNum == (limit - 1)) {
			return 0;
		}
		return ++currentChestNum;
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(new FileReader("test.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

		int caseCnt = sc.nextInt();
		sc.nextLine();
		for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
			System.out.println("Processing test case " + (caseNum + 1));
			pw.print("Case #" + (caseNum + 1) + ": ");
			new Treasure().solve(sc, pw);
		}

		pw.flush();
		pw.close();
		sc.close();
	}

}