package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int numCustomers = scan.nextInt();
		String[] firstName = new String[numCustomers];
		String[] lastName = new String[numCustomers];
		double[] totalPrice = new double[numCustomers];

		for (int i=0; i<numCustomers; i++) {
			// 1. Put first and last name into respective arrays
			firstName[i] = scan.next();
			lastName[i] = scan.next();

			int numItems = scan.nextInt();
			double runningCount = 0.0;

			// 2. Updates running count with price
			for (int j=0; j<numItems; j++) {
				int itemCount = scan.nextInt();
				scan.next();
				double itemPrice = scan.nextDouble();
				runningCount += itemCount * itemPrice;
			}
			totalPrice[i] = runningCount;
		}

		// Prints out statement for each customer
		for (int i=0; i<numCustomers; i++) {
			System.out.println(firstName[i].charAt(0) + ". " + lastName[i] + ": " + String.format("%.2f", totalPrice[i]));
		}

		scan.close();
	}
}
