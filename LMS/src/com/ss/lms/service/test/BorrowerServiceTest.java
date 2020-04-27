package com.ss.lms.service.test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.service.BorrowerService;

public class BorrowerServiceTest {

	@Test
	public void readBorrowersOnlyPk() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		List<Borrower> borrower = borrowerSer.readBorrowers(Integer.valueOf(1), null);
		assertEquals(borrower.isEmpty(), false);
	}
	
	@Test
	public void readBorrowersAllNull() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		List<Borrower> borrower = borrowerSer.readBorrowers(null, null);
		assertEquals(borrower.isEmpty(), false);
	}
	
	@Test
	public void readLibraryBranchAllNull() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		List<LibraryBranch> borrower = borrowerSer.readLibraryBranch(null, false);
		assertEquals(borrower.isEmpty(), false);
	}
	
	@Test
	public void readLibraryBranchPK() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		List<LibraryBranch> borrower = borrowerSer.readLibraryBranch(1, true);
		assertEquals(borrower.isEmpty(), false);
	}
	
	@Test
	public void readBookAllNull() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		List<Book> book = borrowerSer.readBook(null, null);
		assertEquals(book.isEmpty(), false);
	}
	
	@Test
	public void readBookNoCardNo() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		List<Book> book = borrowerSer.readBook(1, null);
		assertEquals(book.isEmpty(), false);
	}
	
	@Test
	public void readBookLoan() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		BookLoan loan = new BookLoan();
		loan.setBranchId(1);
		loan.setCardNo(2);
		loan.setBookId(17);
		List<BookLoan> loans = borrowerSer.readBookloans(loan);
		assertEquals(loans.isEmpty(), true);
	}
	
	@Test
	public void readNullBookLoan() throws SQLException {
		BorrowerService borrowerSer = new BorrowerService();
		BookLoan loan = new BookLoan();
		List<BookLoan> loans = borrowerSer.readBookloans(loan);
		assertEquals(loans.isEmpty(), true);
	}
}
