package com.ss.training.day1;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class GuessNumber extends Format{
	static int randomInt;
	static int tries = 4;
	
	public static void makeRandomInt() {
		randomInt = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		return;
	}
	
	public static boolean checkGuess(int guess) {
		if(guess <= randomInt + 10 && guess >= randomInt - 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Format form = new Format();
		form.setBunnyText("Hello! I am thinking of a number between 0 and 100.", 1);
		makeRandomInt();
		
		System.out.println(randomInt);//testing
		form.newLine();
		form.setBunnyText("Hmm. Please, enter your guess.",3);
		form.newLine();
		Scanner userInputScanner = new Scanner(System.in);
		int userGuess;
		while(tries!= 0) {
			userGuess = userInputScanner.nextInt();
			if(checkGuess(userGuess) == true) {
				form.setBunnyText("Winner! " +userGuess+ " is correct!", 4 );
				form.newLine();
				userInputScanner.close();
				form.newLine();
				form.setBunnyText("Bye!", 5);
				System.exit(0);
			}
			else {
				form.setBunnyText("Sorry. " +userGuess+ " is not correct. Please try again :/", 2 );
				form.newLine();
				tries -= 1;
			}
		}
		form.newLine();
		form.setBunnyText("GAME OVER!", 2);
		form.newLine();
		form.newLine();
		form.setBunnyText("Bye!", 5);
		userInputScanner.close();
		System.exit(0);
	}

}
