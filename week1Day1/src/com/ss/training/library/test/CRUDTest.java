package com.ss.training.library.test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.ss.training.library.Author;
import com.ss.training.library.Book;
import com.ss.training.library.Publisher;

public class CRUDTest {

	/**
	 * Get type tests
	 */
	@Test
	public void getAuthorType() {
		Author reader = Author.getInstance( );
		assertEquals(reader.getType(), 1);
	}
	
	@Test
	public void getPublisherType() {
		Publisher reader = Publisher.getInstance( );
		assertEquals(reader.getType(), 2);
	}
	
	@Test
	public void getBookType() {
		Book reader = Book.getInstance( );
		assertEquals(reader.getType(), 3);
	}
	

	/**
	 * Test getFileMap should return same HashMap
	 */
	@Test
	public void sameFileMap() {
		Book reader = Book.getInstance( );
		Author reader2 = Author.getInstance( );
		assertEquals(reader.getFileMap(), reader2.getFileMap());
	}
	/**
	 * Tests for idFound(String id, Integer type)
	 * must have id number 1 in each file 
	 */	
	
	@Test
	public void trueFoundAuthorID(){
		Author reader = Author.getInstance( );
		Integer type = new Integer(1);
		assertEquals(reader.idFound("1", type), true);
	}
	
	@Test
	public void trueFoundPublisherID(){
		Author reader = Author.getInstance( );
		Integer type = new Integer(2);
		assertEquals(reader.idFound("1", type), true);
	}
	
	@Test
	public void trueFoundBookID(){
		Book reader = Book.getInstance( );
		Integer type = new Integer(3);
		assertEquals(reader.idFound("1", type), true);
	}
	
	@Test
	public void falseFoundAuthorID(){
		Author reader = Author.getInstance( );
		Integer type = new Integer(1);
		assertEquals(reader.idFound("1", type), false);
	}
	
	@Test
	public void falseFoundPublisherID(){
		Publisher reader = Publisher.getInstance( );
		Integer type = new Integer(2);
		assertEquals(reader.idFound("1", type), false);
	}
	
	@Test
	public void falseFoundBookID(){
		Book reader = Book.getInstance( );
		Integer type = new Integer(3);
		assertEquals(reader.idFound("1", type), false);
	}

	/**
	 * Tests for deleting
	 * deleteFile(Integer type, String toDelete)
	 */	
	
	@Test
	public void trueDeleteAuthor(){
		Author reader = Author.getInstance( );
		Integer type = new Integer(1);
		assertEquals(reader.deleteFile(type, "1"), true);
	}
	
	@Test
	public void trueDeletePublisher(){
		Publisher reader = Publisher.getInstance( );
		Integer type = new Integer(2);
		assertEquals(reader.deleteFile(type, "1"), true);
	}
	
	@Test
	public void trueDeleteBook(){
		Book reader = Book.getInstance( );
		Integer type = new Integer(3);
		assertEquals(reader.deleteFile(type, "1"), true);
	}
	
	@Test
	public void falseDeleteAuthor(){
		Author reader = Author.getInstance( );
		Integer type = new Integer(1);
		assertEquals(reader.deleteFile(type, "1"), false);
	}
	
	@Test
	public void falseDeletePublisher(){
		Publisher reader = Publisher.getInstance( );
		Integer type = new Integer(2);
		assertEquals(reader.deleteFile(type, "1"), false);
	}
	
	@Test
	public void falseDeleteBook(){
		Book reader = Book.getInstance( );
		Integer type = new Integer(3);
		assertEquals(reader.deleteFile(type, "1"), false);
	}
	
	/**
	 * Tests for read
	 * fileReader()
	 */	
	@Test
	public void trueReadAuthor(){
		Author reader = Author.getInstance( );
		assertEquals(reader.fileReader(), true);
	}
	
	@Test
	public void trueReadPublisher(){
		Publisher reader = Publisher.getInstance( );
		assertEquals(reader.fileReader(), true);
	}
	
	@Test
	public void trueReadBook(){
		Book reader = Book.getInstance( );
		assertEquals(reader.fileReader(), true);
	}
	
}
