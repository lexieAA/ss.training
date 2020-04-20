package com.ss.training.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Author extends CRUD {
	private static Author author = new Author(); // making class a singleton

	/**
	 * A private Constructor prevents any other class from instantiating.
	 */
	private Author() {
		super(1);
	}

	/** Static 'instance' method */
	public static Author getInstance() {
		return author;
	}

	/**
	 * Writes new entry to author.txt Checks if id is already in file
	 */
	@Override
	void createfileEntry(Scanner userInput) {
		String[] info = askForInfo(userInput).split(":"); // storing information return from askForIno method
		if ("Error" == info[0]) { // check if info was invalid
			return;
		} else { // if information was correct, store it
			String authorId = info[0];
			String authorName = info[1];

			if (idFound(authorId, 1) == false) { // checking if id is already in file
				try (FileWriter fileWriter = new FileWriter("resources/author.txt", true)) {

					BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

					bufferWriter.newLine();
					bufferWriter.write(authorId + ":" + authorName); // add to end of file
					System.out.println("Success, author was added.");
					bufferWriter.close();
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				System.out.println("Author with id " + authorId + " already exist."); // if id was in file don't add it
				return;
			}
		}
	}

	/**
	 * Asks user question and returns them as a string
	 */
	@Override
	String askForInfo(Scanner userInput) {
		StringBuffer info = new StringBuffer("");
		try {
			System.out.println("Enter Author ID:");
			info.append(Integer.toString(userInput.nextInt()) + ":");
		} catch (Exception e) {
			System.out.println("\nInvalid Input Error.");
			return "Error:";
		}
		System.out.println("Enter Author Name:");
		userInput.nextLine();
		info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}", ""));
		return info.toString();
	}

	/**
	 * Reads file and formats text for display
	 */
	@Override
	public boolean fileReader() {
		try (Stream<String> readerStream = Files.lines(Paths.get(getFileMap().get(getType())))) {
			readerStream.forEach(line -> {
				String[] lineItems = line.split(":");
				System.out.println("Author's ID: " + lineItems[0] + "\t\t" + "Authors Name: " + lineItems[1]);
			});
		} catch (IOException e) {
			System.out.println("Error, was not able to read file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
