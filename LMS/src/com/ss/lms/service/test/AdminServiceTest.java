package com.ss.lms.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;

public class AdminServiceTest {
	@Test
	public void readAuthorsAllNull() throws SQLException {
		AdminService adminSer = new AdminService();
		List<Author> a = adminSer.readAuthors(null,null);
		assertEquals(a.isEmpty(), false);
	}
	
	@Test
	public void readAuthorsByID() throws SQLException {
		AdminService adminSer = new AdminService();
		List<Author> a = adminSer.readAuthors(1,null);
		assertEquals(a.isEmpty(), false);
	}
	
	@Test
	public void readGenre() throws SQLException {
		AdminService adminSer = new AdminService();
		List<Genre> a = adminSer.readGenres();
		assertEquals(a.isEmpty(), false);
	}
	
	@Test
	public void readLibraryBranch() throws SQLException {
		AdminService adminSer = new AdminService();
		List<LibraryBranch> a = adminSer.readLibraryBranchs();
		assertEquals(a.isEmpty(), false);
	}
	
	@Test
	public void readPublisher() throws SQLException {
		AdminService adminSer = new AdminService();
		List<Publisher> a = adminSer.readPublishers();
		assertEquals(a.isEmpty(), false);
	}
	
	@Test
	public void readBorrower() throws SQLException {
		AdminService adminSer = new AdminService();
		List<Borrower> a = adminSer.readBorrowers();
		assertEquals(a.isEmpty(), false);
	}
	
	@Test
	public void readBookLoanEmptyLoan() throws SQLException {
		AdminService adminSer = new AdminService();
		BookLoan loan = new BookLoan();
		loan.setBookId(17);
		loan.setBranchId(1);
		loan.setCardNo(2);
		List<BookLoan> a = adminSer.readBookLoan(loan, null);
		assertEquals(a.isEmpty(), true);
	}
	
}
