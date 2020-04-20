package com.ss.training.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Book extends CRUD {
	private static Book book = new Book(); // making it singleton

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private Book() {
		super(3);
	}

	/* Static 'instance' method */
	public static Book getInstance() {
		return book;
	}

	/**
	 * Writes new entry to book.txt Checks if id is already in file
	 */
	@Override
	void createfileEntry(Scanner userInput) {
		String[] info = askForInfo(userInput).split(":");
		if ("Error" == info[0]) {
			return;
		} else {
			String bookId = info[0];
			String bookName = info[1];
			String authorId = info[2];
			String publisherId = info[3];

			if (idFound(bookId, 3) == false) {
				if (idFound(authorId, 1) == true) {
					if (idFound(publisherId, 2) == true) {
						try (FileWriter fileWriter = new FileWriter("resources/book.txt", true)) {

							BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

							bufferWriter.newLine();
							bufferWriter.write(bookId + ":" + bookName + ":" + authorId + ":" + publisherId);
							System.out.println("Success, book was added.");
							bufferWriter.close();
							fileWriter.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					} else {
						System.out.println("Publisher not found. Please add publisher before adding book entry.");
						return;
					}

				} else {
					System.out.println("Author not found. Please add author before adding book entry.");
					return;
				}

			} else {
				System.out.println("Book with id " + bookId + " already exist.");
				return;
			}
		}

	}

	/**
	 * Asks user question and returns them as a string
	 */
	@Override
	String askForInfo(Scanner userInput) {
		System.out.println("Enter Book ID:");
		StringBuffer info = new StringBuffer("");
		try {
			info.append(Integer.toString(userInput.nextInt()) + ":");

			System.out.println("Enter Book Name:");
			userInput.nextLine();
			info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}", "") + ":");

			System.out.println("Enter Book's Author's ID:");
			int authorId = userInput.nextInt();
			info.append(Integer.toString(authorId) + ":");

			System.out.println("Enter Book's Publisher's ID:");
			int publisherId = userInput.nextInt();
			info.append(Integer.toString(publisherId));
		} catch (Exception e) {
			System.out.println("\nInvalid Input Error.");
			return "Error:";
		}
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
				System.out.println("Book's ID: " + lineItems[0] + "\t\tBook's Name: " + lineItems[1] +
						 "  &  Author's ID: " + lineItems[2] + "  &  Publisher's ID: " + lineItems[3]);
			});
		} catch (IOException e) {
			System.out.println("Error, was not able to read file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
