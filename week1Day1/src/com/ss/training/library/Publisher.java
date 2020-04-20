package com.ss.training.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Publisher extends CRUD {
	private static Publisher publisher = new Publisher(); // making it singleton

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private Publisher() {
		super(2);
	}

	/* Static 'instance' method */
	public static Publisher getInstance() {
		return publisher;
	}

	/**
	 * Writes new entry to publisher.txt Checks if id is already in file
	 */
	@Override
	void createfileEntry(Scanner userInput) {
		String[] info = askForInfo(userInput).split(":");
		if ("Error" == info[0]) {
			return;
		} else {
			String publisherId = info[0];
			String publisherName = info[1];
			String address = info[2];
			if (idFound(publisherId, 2) == false) {
				try (FileWriter fileWriter = new FileWriter("resources/publisher.txt", true)) {

					BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

					bufferWriter.newLine();
					bufferWriter.write(publisherId + ":" + publisherName + ":" + address);
					System.out.println("Success, publisher was added.");
					bufferWriter.close();
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				System.out.println("Publisher with id " + publisherId + " already exist.");
				return;
			}
		}
	}

	/**
	 * Asks user question and returns them as a string
	 */
	@Override
	String askForInfo(Scanner userInput) {
		System.out.println("Enter Publisher's ID:");
		StringBuffer info = new StringBuffer("");
		try {
			info = info.append(Integer.toString(userInput.nextInt()) + ":");

			System.out.println("Enter Publisher's Name:");
			userInput.nextLine();
			info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}", "") + ":");

			System.out.println("Enter Publisher's Address:");
			info.append(userInput.nextLine().replaceAll(":", ""));

			return info.toString();

		} catch (Exception e) {
			System.out.println("\nInvalid Input Error.");
			return "Error:";
		}
	}

	/**
	 * Reads file and formats text for display
	 */
	@Override
	public boolean fileReader() {
		try (Stream<String> readerStream = Files.lines(Paths.get(getFileMap().get(getType())))) {
			readerStream.forEach(line -> {
				String[] lineItems = line.split(":");
				System.out.println("Publisher's ID: " + lineItems[0] + "\tPublisher's Name: " +
				lineItems[1] + "  &  Address: " + lineItems[2]);
			});
		} catch (IOException e) {
			System.out.println("Error, was not able to read file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
