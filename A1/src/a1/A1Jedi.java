package a1;

import java.util.HashMap;
import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int itemsInStore = scan.nextInt();
		String[] itemArray = new String[itemsInStore];
		HashMap<String,Integer> itemToCountMap = new HashMap<String,Integer>();
		HashMap<String,Integer> itemToPersonMap = new HashMap<String,Integer>();
		HashMap<String,Boolean> itemCountedBefore = new HashMap<String,Boolean>();

		// Places items in store in itemList and itemMap
		for (int i=0; i<itemsInStore; i++) {
			itemArray[i] = scan.next();
			itemToCountMap.put(itemArray[i],0);
			itemToPersonMap.put(itemArray[i],0);
			itemCountedBefore.put(itemArray[i],false);
			scan.nextDouble();
		}

		int numOfPeople = scan.nextInt();

		// Scans through each person
		for (int i=0; i<numOfPeople; i++) {
			scan.next(); scan.next(); // Skips over name

			int itemPerPerson = scan.nextInt();

			// Scans through each item per person
			for (int j=0; j<itemPerPerson; j++) {
				int tmpItemCount = scan.nextInt();
				String tmpItem = scan.next();
				itemToCountMap.put(tmpItem, itemToCountMap.get(tmpItem) + tmpItemCount);
				// Adds person tally for item bought which also checks if it's not counting more than once per person
				if (itemCountedBefore.get(tmpItem) == false) {
					itemToPersonMap.put(tmpItem, itemToPersonMap.get(tmpItem)+1);
				}
				itemCountedBefore.put(tmpItem,true);
			}

			// Reset itemCountedBefore map to all false for next person
			for (int j=0; j<itemsInStore; j++) {
				itemCountedBefore.put(itemArray[j], false);
			}
		}
		scan.close();
		// Prints out statement for each item
		for (int i=0; i<itemsInStore; i++) {
			if (itemToCountMap.get(itemArray[i]) == 0) {
				System.out.println("No customers bought " + itemArray[i]);
			} else {
				System.out.println(itemToPersonMap.get(itemArray[i]) + " customers bought " + itemToCountMap.get(itemArray[i]) + " " + itemArray[i]);
			}
		}
	}
}