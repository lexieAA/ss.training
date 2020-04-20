package com.ss.training.object.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Scanner;

public class Book extends CRUD implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2971029745244273398L;
	private String bookId;
	private String bookName;
	private String authorId;
	private String publisherId;
	
	/**
	 * @param bookId
	 * @param bookName
	 * @param authorId
	 * @param publisherId
	 */
	public Book(String bookId, String bookName, String authorId, String publisherId) {
		super(3);
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorId = authorId;
		this.publisherId = publisherId;
	}
	
	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the authorId
	 */
	public String getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the publisherId
	 */
	public String getPublisherId() {
		return publisherId;
	}

	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		return true;
	}

	@Override
	void createfileEntry(Scanner userInput) {
		String[] info = askForInfo(userInput).split(":");
		if("Error" == info[0]) {
			return;
			}else {
				String bookId = info[0];
				String bookName = info[1];
				String authorId = info[2];
				String publisherId = info[3];
				
				if(idFound(bookId,3) == false) {
					if(idFound(authorId,1) == true) {
						if(idFound(publisherId,2) == true) {
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
		
	}


	@Override
	String askForInfo(Scanner userInput) {
		System.out.println("Enter Book ID:");
		StringBuffer info = new StringBuffer("");
		try {
		  info.append(Integer.toString(userInput.nextInt()) +":");
		  
		  System.out.println("Enter Book Name:");
		  userInput.nextLine(); 
		  info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}","")+":");
		  
		  System.out.println("Enter Book's Author's ID:");
		  int authorId = userInput.nextInt();
		  info.append(Integer.toString(authorId) +":");
		  
		  System.out.println("Enter Book's Publisher's ID:");
		  int publisherId = userInput.nextInt();
		  info.append(Integer.toString(publisherId));
		} catch (Exception e) {
			System.out.println("\nInvalid Input Error.");
			return "Error:";
		}
		return info.toString();
	}
	


}
