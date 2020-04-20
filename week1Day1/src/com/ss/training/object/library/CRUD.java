package com.ss.training.object.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CRUD {
	private int type;
	private HashMap<Integer, String> fileMap = new HashMap<Integer, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = -455907038695056528L;

		{
			this.put(1, "./resources/author.txt");
			this.put(2, "./resources/publisher.txt");
			this.put(3, "./resources/book.txt");
		}
	};

	/**
	 * @param type
	 */
	public CRUD(int type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the fileMap
	 */
	public HashMap<Integer, String> getFileMap() {
		return fileMap;
	}

	public void doCRUD(int action, Scanner userInput) {
		switch (action) {
		case 1:// create entry
			createfileEntry(userInput);
			break;
		case 2:// read file
			fileReader();
			break;
		case 3:// update file
			updateFileEntry(userInput);
			break;
		case 4: // delete entry
			System.out.println("Enter entry ID to delete: ");
			try {
				int selection = userInput.nextInt();
				if (deleteFile(getType(), Integer.toString(selection)) == true) {
					System.out.println("Success, entry was deleted.");
				} else {
					System.out.println("Error, was not able to delete.");
				}
			} catch (Exception e) {
				System.out.println("\nInvalid Input Error.");
			}
			break;

		}
	}

	/**
	 * looks in file for id
	 * 
	 * @return boolean
	 */
	public boolean idFound(String id, Integer type) {
		HashMap<Integer, String> fileMap = new HashMap<Integer, String>();
		fileMap.put(1, "./resources/author.txt");
		fileMap.put(2, "./resources/publisher.txt");
		fileMap.put(3, "./resources/book.txt");

		try (BufferedReader bufStream = new BufferedReader(new FileReader(fileMap.get(type)))) {
			String line = bufStream.readLine();
			while (line != null) {
				String[] splits = line.split(":");
				line = bufStream.readLine();
				if (splits[0].equals(id)) { // looks if id is a match
					bufStream.close(); // if so close buffer and return true
					return true;
				}

			}
			bufStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from file");

		}
		return false;

	}

	abstract void createfileEntry(Scanner userInput);

	abstract String askForInfo(Scanner userInput);

	public void fileReader() {
		try (Stream<String> readerStream = Files.lines(Paths.get(getFileMap().get(getType())))) {
			readerStream.forEach(line -> {
				String[] lineItems = line.split(":");
				System.out.println(Arrays.toString(lineItems));
			});
		} catch (IOException e) {
			System.out.println("Error, was not able to update.");
			e.printStackTrace();
		}
	}

	public void updateFileEntry(Scanner userInput) {
		String info = askForInfo(userInput);
		if ("Error" == info) {
			return;
		} else {
			String toUpdateId = info.substring(0, info.indexOf(":"));
			String toUpdateInfo = info.substring(info.indexOf(":"), info.length());
			String[] tempUpdate = toUpdateInfo.split(":");

			if (tempUpdate.length == 4) {
				Integer author = new Integer(1);
				Integer publisher = new Integer(2);
				if (idFound(tempUpdate[2], author) == false && idFound(tempUpdate[3], publisher) == false) {
					System.out.println(
							"Sorry, not able to update entry. Author ID or Publisher ID was not found on file.");
					return;
				}
			} 

				File file = new File(getFileMap().get(getType()));
				File tempFile = new File("resources/temp.txt");
				try (PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));) {
					if (idFound(toUpdateId, getType()) == false) {
						System.out.println("Error, ID was not found in file");
						return;
					} else {
						Files.lines(file.toPath())
								.filter(line -> !line.substring(0, line.indexOf(":")).equals(toUpdateId))
								.forEach(printWriter::println);

						printWriter.append(toUpdateId + toUpdateInfo);
						printWriter.flush();
						printWriter.close();
						file.delete();
						tempFile.renameTo(file);
						System.out.println("Success, entry was updated.");
					}
				} catch (IOException e) {
					System.out.println("Error, was not able to update.");
					e.printStackTrace();
				}
		}
	}

	public boolean deleteFile(Integer type, String toDelete) {

		File file = new File(getFileMap().get(type)); // current file
		List<String> toRemove = null;
		File tempFile = new File("./resources/temp.txt");// temp file
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));) { // write to temp file
			// if(type == 3) { //if book
			toRemove = Files.lines(file.toPath()) // save book to be removed
					.filter(line -> line.substring(0, line.indexOf(":")).equals(toDelete)).collect(Collectors.toList());
			;
			// }
			if (toRemove.isEmpty() == true) {
				System.out.println("ID was not found in file");
				return false;
			}
			Files.lines(file.toPath()).filter(line -> !line.substring(0, line.indexOf(":")).equals(toDelete))
					.forEach(line -> {
						printWriter.println(line);
					});
			printWriter.flush();
			printWriter.close();
			file.delete();
			tempFile.renameTo(file); // renames temp file to old file name
//			System.out.println("The following Book was removed" + toRemove);
			if (type == 3) {
				String[] splits = toRemove.get(0).split(":");
				deleteFile(1, splits[2]); // removes author
				deleteFile(2, splits[3]); // removes publisher
			}
			return true; // Success, entry was deleted.
		} catch (IOException e) {
			e.printStackTrace();
			return false; // Error, was not able to delete.
		}
	}

	public static void main(String[] args) {
//		CRUD reader = new CRUD(1);
//		reader.fileReader();
//		System.out.println(reader.getType());
	}
}
