package com.ss.training.daytwo;

import java.util.Scanner;

public class Adding {

	public static void main(String[] args) {
		int sum = 0;
		int userNum;
		Scanner userInputScanner = new Scanner(System.in);
		for(int i = 1; i <= 3; i++) {
			System.out.print("Please enter a number. ");
			try {
				userNum = userInputScanner.nextInt();
				sum = sum + userNum;
				}
				catch(Exception e) {
					System.out.print("Sorry that is not a number and will not be counted in the sum.\n");
					break;
				}
		}
		userInputScanner.close();
		System.out.print("The sum of the numbers given are " + sum);

	}

}