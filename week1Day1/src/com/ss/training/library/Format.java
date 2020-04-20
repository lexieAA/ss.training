package com.ss.training.library;

public class Format {
	private int selectedType;
	private int selectedAction;

	/**
	 * @return the selectedType
	 */
	public int getSelectedType() {
		return selectedType;
	}

	/**
	 * @param selectedType the selectedType to set
	 */
	public void setSelectedType(int selectedType) {
		this.selectedType = selectedType;
	}

	/**
	 * @return the selectedAction
	 */
	public int getSelectedAction() {
		return selectedAction;
	}

	/**
	 * @param selectedAction the selectedAction to set
	 */
	public void setSelectedAction(int selectedAction) {
		this.selectedAction = selectedAction;
	}
	
	/**
	 * converts home main choice to words
	 */
	public String typeToString() {
		switch (getSelectedType()) {
		case 1:
			return "Author";

		case 2:
			return "Publisher";

		case 3:
			return "Book";
		case 0:
			return "Bye! Closing Library.";
		default:
			System.out.println("Please enter a vaild number as shown in menu.");
			System.out.println("-------------------------\n");
			mainMenu();
			return "Error";
		}

	}
	

	/**
	 * prints the main menu
	 */
	public void mainMenu() {
		System.out.println("\n 	Main Menu");
		System.out.println("-------------------------");
		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Author");
		System.out.println("2 - Publisher");
		System.out.println("3 - Book");
		System.out.println("0 - Quit");
//		System.out.println("\nSelection: ");
		return;
	}

	/**
	 * prints the CRUD menu
	 */
	public void crudMenu() {
		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Create new entry");
		System.out.println("2 - Read file");
		System.out.println("3 - Update file");
		System.out.println("4 - Delete entry\n");
		System.out.println("5 - Main Menu");
		System.out.println("0 - Quit");
		System.out.println("\nSelection: ");
		return;
	}

}
