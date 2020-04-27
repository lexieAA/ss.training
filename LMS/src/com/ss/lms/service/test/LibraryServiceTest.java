package com.ss.lms.service.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.service.LibraryService;

public class LibraryServiceTest {
	/**
	 * Library Servic tests
	 * @throws SQLException 
	 */
	
	
	@Test
	public void readWrongBranchID() throws SQLException {
		LibraryService librarySer = new LibraryService();
		List<LibraryBranch> branches = librarySer.readLibraryBranches(Integer.valueOf(100));
		assertEquals(branches, null);
	}
	
	@Test
	public void readBranchID() throws SQLException {
		LibraryService librarySer = new LibraryService();
		List<LibraryBranch> branches = librarySer.readLibraryBranches(null);
		assertEquals(branches.isEmpty(), false);
	}
	
	@Test
	public void readWrongBookID() throws SQLException {
		LibraryService librarySer = new LibraryService();
		List<Book> books = librarySer.readBook(Integer.valueOf(100), Integer.valueOf(100));
		assertEquals(books.isEmpty(), true);
	}
	
	@Test
	public void readBookID() throws SQLException {
		LibraryService librarySer = new LibraryService();
		List<Book> books = librarySer.readBook(Integer.valueOf(1), null);
		assertNotNull(books);
	}
	
	@Test
	public void readWrongCopyID() throws SQLException {
		LibraryService librarySer = new LibraryService();
		List<BookCopies> copy = librarySer.readBookCopies(Integer.valueOf(100), Integer.valueOf(100));
		assertEquals(copy.isEmpty(), true);
	}
	
	@Test
	public void readCopyID() throws SQLException {
		LibraryService librarySer = new LibraryService();
		List<BookCopies> copy = librarySer.readBookCopies(Integer.valueOf(1), null);
		assertNotNull(copy);
	}

}


