package a1;

import java.util.HashMap;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		// Fields of items in store
		int numOfItems = scan.nextInt();
		HashMap<String,Double> itemList = new HashMap<String,Double>();

		// Places items and price in map
		for (int i=0; i<numOfItems; i++) {
			itemList.put(scan.next(), scan.nextDouble());
		}

		// Fields for customers
		int numOfPeople = scan.nextInt();
		String[] firstName = new String[numOfPeople];
		String[] lastName = new String[numOfPeople];
		double[] totalPerPerson = new double[numOfPeople];

		// Updates total price per person
		for (int i=0; i<numOfPeople; i++) {
			firstName[i] = scan.next();
			lastName[i] = scan.next();

			int itemPerPerson = scan.nextInt();
			double runningCount = 0.0;

			for (int j=0; j<itemPerPerson; j++) {
				int tempAmount = scan.nextInt();
				String tempItem = scan.next();
				runningCount += itemList.get(tempItem) * tempAmount;
			}
			totalPerPerson[i] = runningCount;
		}

		// Finds biggest, smallest, and average amount
		double biggest = 0.0;
		int biggestIndex = 0;
		double smallest = totalPerPerson[0];
		int smallestIndex = 0;
		double runningTotal = 0.0;

		for (int i=0; i<numOfPeople; i++) {
			if (totalPerPerson[i] >= biggest) {
				biggest = totalPerPerson[i];
				biggestIndex = i;
			}
			if (totalPerPerson[i] <= smallest) {
				smallest = totalPerPerson[i];
				smallestIndex = i;
			}
			runningTotal += totalPerPerson[i];
		}

		double avg = runningTotal / numOfPeople;

		System.out.println("Biggest: " + firstName[biggestIndex] + " " + lastName[biggestIndex] + " (" + String.format("%.2f",biggest) + ")");
		System.out.println("Smallest: " + firstName[smallestIndex] + " " + lastName[smallestIndex] + " (" + String.format("%.2f",smallest) + ")");
		System.out.println("Average: " + String.format("%.2f", avg));

		scan.close();
	}
}
