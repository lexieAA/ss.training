package com.ss.training.library;

import java.util.Scanner;

public class UserInterface extends Format {

	public static void main(String[] args) {
		boolean moveOn = false;
		boolean onMainMenu = true;
		boolean onActionMenu = false;
		int selection = 0;
		Scanner userInput = new Scanner(System.in);
		UserInterface userInterface = new UserInterface();

		System.out.println("	Welcome	");
		System.out.println("-------------------------");

		while (moveOn == false) {
			while (onMainMenu == true) { 
//				try {
					userInterface.mainMenu(); // main menu
					System.out.println("\nSelection: ");
					selection = userInput.nextInt();
					onMainMenu = false;
					onActionMenu = true;
//				} catch (Exception e) {
//					System.out.println("\nInvalid Input Error.");
//
//				}
			}
				while (onActionMenu == true) { // CRUD options
					userInterface.setSelectedType(selection);
					if (userInterface.getSelectedType() == 0) { // Quit program
						System.out.println("\n-------------------------");
						System.out.println("Bye! Closing Library.");
						userInterface.setSelectedAction(selection);
						onMainMenu = false;
						onActionMenu = false;
						moveOn = true;
					} else if (userInterface.getSelectedType() <= 3) {//valid number from main menu was given
						System.out.println("	" + userInterface.typeToString());
						userInterface.crudMenu(); // showing action menu
//						try {
							selection = userInput.nextInt();
//						} catch (Exception e) {
//							System.out.println("\nInvalid Input Error.");
//
//						}


						if (selection == 5) { //selected Main Menu
							userInterface.setSelectedAction(selection);
							onMainMenu = true;
							onActionMenu = false;
						} else if (selection == 0) { // Quit program
							System.out.println("\n-------------------------");
							System.out.println("Bye! Closing Library.");
							userInterface.setSelectedAction(selection);
							onMainMenu = false;
							onActionMenu = false;
							moveOn = true;
						} else if (selection <= 4) { // CRUD action was selected
							userInterface.setSelectedAction(selection);
							System.out.println("-------------------------");
							System.out.println("	" + userInterface.typeToString());
							System.out.println("-------------------------");
							switch (userInterface.getSelectedType()) {
							case 1://Do a CRUD method for Author
								Author author = Author.getInstance( );
								author.doCRUD(userInterface.getSelectedAction(),userInput); 
								onMainMenu = true;//go back to main menu
								onActionMenu = false;
								break;
							case 2: //Do a CRUD method for Publisher
								Publisher publisher = Publisher.getInstance( );
								publisher.doCRUD(userInterface.getSelectedAction(),userInput);
								onMainMenu = true; //go back to main menu
								onActionMenu = false;
								break;
							case 3: //Do a CRUD method for Book
								Book book = Book.getInstance( );
								book.doCRUD(userInterface.getSelectedAction(),userInput);
								onMainMenu = true; //go back to main menu
								onActionMenu = false;
								break;
							}
							
							System.out.println("-------------------------");
						} else {
							userInterface.setSelectedType(selection); //close program 
							System.out.println("error");
							onMainMenu = false;
							onActionMenu = false;
							moveOn = true;
						}

					} else { //Invalid number from main menu
						System.out.println("Please try again. Enter a vaild number.");
					}
				}

		}
	}
}
