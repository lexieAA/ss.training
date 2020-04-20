package com.ss.training.object.library;


import java.io.Serializable;

public class Author implements Serializable{
	private static final long serialVersionUID = -7774947880299190056L;
	private String authorId;
	private String authorName;
	
	/**
	 * @param authorId
	 * @param authorName
	 */
	public Author(String authorId, String authorName) {
		this.authorId = authorId;
		this.authorName = authorName;
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
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
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
		Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}

//	@Override
//	void createfileEntry(Scanner userInput) {
//		String[] info = askForInfo(userInput).split(":");
//		if("Error" == info[0]) {return;}else {
//		String authorId = info[0];
//		String authorName = info[1];
//		
//		if(idFound(authorId,1) == false) {
//			try(FileWriter fileWriter = new FileWriter("resources/author.txt", true)) {
//	
//				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
//				
//				bufferWriter.newLine();
//				bufferWriter.write(authorId + ":" + authorName);
//				System.out.println("Success, author was added.");
//				bufferWriter.close();
//				fileWriter.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return;
//		}else {
//			System.out.println("Author with id " + authorId + " already exist.");
//			return;
//		}
//		}
//	}
//
//	@Override
//	String askForInfo(Scanner userInput) {
//		StringBuffer info = new StringBuffer("");
//		try {
//		  System.out.println("Enter Author ID:");
//		  info.append(Integer.toString(userInput.nextInt()) +":");
//		} catch (Exception e) {
//			System.out.println("\nInvalid Input Error.");
//			return "Error:";
//		}
//		System.out.println("Enter Author Name:");
//		userInput.nextLine(); 
//		info.append(userInput.nextLine().replaceAll("\\p{P}\\p{S}",""));
//		return info.toString();
//	}
}
