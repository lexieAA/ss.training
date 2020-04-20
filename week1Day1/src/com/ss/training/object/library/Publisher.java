package com.ss.training.object.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Scanner;

public class Publisher extends CRUD implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8047669205159597446L;
	private String publisherId;
	private String publisherName;
	private String address;
	
	public Publisher(String publisherId, String publisherName, String address) {
		super(2);
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.address = address;
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

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		result = prime * result + ((publisherName == null) ? 0 : publisherName.hashCode());
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
		Publisher other = (Publisher) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		if (publisherName == null) {
			if (other.publisherName != null)
				return false;
		} else if (!publisherName.equals(other.publisherName))
			return false;
		return true;
	}

	@Override
	void createfileEntry(Scanner userInput) {
		String[] info = askForInfo(userInput).split(":");
		if("Error" == info[0]) {
			return;
			}else {
				String publisherId = info[0];
				String publisherName = info[1];
				String address = info[2];
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
	}


	@Override
	String askForInfo(Scanner userInput) {
		System.out.println("Enter Publisher's ID:");
		StringBuffer info = new StringBuffer("");
		try {
		  info = info.append(Integer.toString(userInput.nextInt()) +":");
		  
		  System.out.println("Enter Publisher's Name:");
		  userInput.nextLine(); 
		  info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}","")+":");
		  
		  System.out.println("Enter Publisher's Address:");
		  info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}",""));
		  
		  return info.toString();
		  
		} catch (Exception e) {
			System.out.println("\nInvalid Input Error.");
			return "Error:";
		}
	}
	
	
	
	

}
