package com.ss.training.object.library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class CreateNewFileEntry {
	
	
	/**
	 * looks in file for id
	 * @return boolean
	 */
	public boolean idFound(String id, Integer type) {
		HashMap<Integer, String> fileMap = new HashMap<Integer, String>();
		fileMap.put(1, "./resources/author.txt");
		fileMap.put(2, "./resources/publisher.txt");
		fileMap.put(3, "./resources/book.txt");
		
		try(BufferedReader bufStream = new BufferedReader(new FileReader(fileMap.get(type)))){
			String line = bufStream.readLine();
			while(line!=null){
				String[] splits = line.split(":");
				line = bufStream.readLine();
				if(splits[0].equals(id)) { //looks if id is a match
					bufStream.close(); //if so close buffer and return true
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
	
	public void createAuthorEntry(String authorId, String authorName) {
		if(idFound(authorId,1) == false) {
			try(FileWriter fileWriter = new FileWriter("resources/author.txt", true)) {
	
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
				
				bufferWriter.newLine();
				bufferWriter.write(authorId + ":" + authorName);
				System.out.println("Success, author was added.");
				bufferWriter.close();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}else {
			System.out.println("Author with id " + authorId + " already exist.");
			return;
		}
		
	}
	
	public void createPublisherEntry(String publisherId, String publisherName, String address) {
		if(idFound(publisherId,2) == false) {
			try(FileWriter fileWriter = new FileWriter("resources/publisher.txt", true)) {
	
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
		}else {
			System.out.println("Publisher with id " + publisherId + " already exist.");
			return;
		}
		
	}

	public void createBookEntry(String bookId, String bookName, String authorId, String publisherId) {
		if(idFound(bookId,3) == false) {
			if(idFound(authorId,1) == true) {
				if(idFound(publisherId,3) == true) {
					try(FileWriter fileWriter = new FileWriter("resources/book.txt", true)) {
						
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
				}else {
					System.out.println("Publisher not found. Please add publisher before adding book entry.");
					return;
				}
				
			}else {
				System.out.println("Author not found. Please add author before adding book entry.");
				return;
			}
			
		}else {
			System.out.println("Book with id " + bookId + " already exist.");
			return;
		}

	}
	
	public static void main(String[] args) {
		CreateNewFileEntry createNew = new CreateNewFileEntry();
		createNew.createAuthorEntry("4", "Sam");
		createNew.createPublisherEntry("4", "Sam", "100 New York");
		createNew.createBookEntry("3", "Sambook", "1", "8");
		
		createNew.createBookEntry("5", "Sambook", "1", "8");
		createNew.createBookEntry("5", "Sambook", "8", "1");
		createNew.createBookEntry("4", "Sambook", "1", "1");
		
		System.out.println(createNew.idFound("3", 1));
		createNew.createAuthorEntry("4", "Sam");
	}

}
