package com.ss.training.object.library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteFileEntry{
	
	public void deleteFile(Integer type, String toDelete) {
		HashMap<Integer, String> fileMap = new HashMap<Integer, String>();
		fileMap.put(1, "./resources/author.txt");
		fileMap.put(2, "./resources/publisher.txt");
		fileMap.put(3, "./resources/book.txt");
		
		File file = new File(fileMap.get(type) );
		List<String>  toRemove = null;
		File tempFile = new File("./resources/temp.txt");
		try(PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));) {
			if(type == 3) {
				toRemove = Files.lines(file.toPath())
						.filter(line -> line.substring(0, line.indexOf(":")).equals(toDelete))
						.collect(Collectors.toList());;
			}
			Files.lines(file.toPath())
				.filter(line -> !line.substring(0, line.indexOf(":")).equals(toDelete))
				.forEach(line ->{
					String[] splits = line.split(":");
					switch(type) {
					case 1:
						Author author = new Author("1", "Test");
						author.setAuthorId(splits[0]);
						author.setAuthorName(splits[1]);
						printWriter.println(author.getAuthorId() + ":" + author.getAuthorName());
						break;
					case 2:
						Publisher publisher = new Publisher("1", "Test", "Test");
						publisher.setPublisherId(splits[0]);
						publisher.setPublisherName(splits[1]);
						publisher.setAddress(splits[2]);
						printWriter.println(publisher.getPublisherId()+":"
						+ publisher.getPublisherName() + ":" + publisher.getAddress());
						break;
					case 3:
						Book book = new Book("1", "Test", "1", "1");
						book.setBookId(splits[0]);
						book.setBookName(splits[1]);
						book.setAuthorId(splits[2]);
						book.setPublisherId(splits[3]);
						printWriter.println(book.getBookId() +
								":" + book.getBookName() + 
								":" + book.getAuthorId() + 
								":" + book.getPublisherId());
						break;
					}
					}
				
				);
			printWriter.flush();
			printWriter.close();
			file.delete();
			tempFile.renameTo(file);
			System.out.println(toRemove);
			if(type == 3) {
				String[] splits = toRemove.get(0).split(":");
				deleteFile(1, splits[2]);
				deleteFile(2, splits[3]);
			}
			System.out.println("Success, entry was deleted.");
		} catch (IOException e) {
			System.out.println("Error, was not able to delete.");
			e.printStackTrace();
		}
}

	public static void main(String[] args) {
		DeleteFileEntry reader = new DeleteFileEntry();
		reader.deleteFile(3,"1");
		//reader.updateFileEntry("1", "alexis");
	}

}
