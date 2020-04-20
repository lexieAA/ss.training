package com.ss.training.object.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ReadFile {
	private int type;
	

		/**
	 * @param type
	 */
	public ReadFile(int type) {
		super();
		this.type = type;
	}
	


		public int getType() {
			return type;
		}



		public void setType(int type) {
			this.type = type;
		}
		
		public void fileReader() {
			HashMap<Integer, String> fileMap = new HashMap<Integer, String>();
			fileMap.put(1, "./resources/author.txt");
			fileMap.put(2, "./resources/publisher.txt");
			fileMap.put(3, "./resources/book.txt");
			
			try(BufferedReader bufStream = new BufferedReader(new FileReader(fileMap.get(this.getType())))){
				String line = bufStream.readLine();
				switch(this.getType()) {
				case 1: //read author file
					while(line!=null){
						Author author = new Author("1", "Test");
						String[] splits = line.split(":");
						author.setAuthorId(splits[0]);
						author.setAuthorName(splits[1]);
						System.out.println("Author ID: "+author.getAuthorId()+" & Author Name: " +author.getAuthorName());
						line = bufStream.readLine();
					}
					break;
				case 2: //read publisher file
					while(line!=null){
						Publisher publisher = new Publisher("1", "Test", "Test");
						String[] splits = line.split(":");
						publisher.setPublisherId(splits[0]);
						publisher.setPublisherName(splits[1]);
						publisher.setAddress(splits[2]);
						System.out.println("Publisher ID: "+ publisher.getPublisherId()+" & Publisher Name: "
						+ publisher.getPublisherName() + " & Address: " + publisher.getAddress());
						line = bufStream.readLine();
					}
					break;
				case 3: //read book file
					while(line!=null){
						Book book = new Book("1", "Test", "1", "1");
						String[] splits = line.split(":");
						book.setBookId(splits[0]);
						book.setBookName(splits[1]);
						book.setAuthorId(splits[2]);
						book.setPublisherId(splits[3]);
						System.out.println("Book ID:  "+ book.getBookId() +
								" & Book Name: " + book.getBookName() + 
								" & Author: " + book.getAuthorId() + 
								" & Publisher: " + book.getPublisherId());
						line = bufStream.readLine();
					}
					break;
				}
				bufStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to read from File");
			
			}
			
			
		}
		
		public static void main(String[] args) {
			ReadFile reader = new ReadFile(1);
			reader.fileReader();
		}

	}
