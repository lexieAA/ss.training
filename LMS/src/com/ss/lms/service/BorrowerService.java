package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoanDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.LibraryBranchDAO;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;

public class BorrowerService {

	public ConnectionUtil connUtil = new ConnectionUtil();

	public void saveBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			if (borrower.getCardNo() != null && borrower.getBorrowerName() != null) {
				adao.updateBorrower(borrower);
			} else if (borrower.getCardNo() != null) {
				adao.deleteBorrower(borrower);
			} else {
				adao.addBorrower(borrower);
			}
			conn.commit(); // transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Borrower> readBorrowers(Integer pk, String borrowerName) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			if (pk != null) {
				Borrower borrower = new Borrower();
				borrower.setCardNo(pk);
				return adao.readNameBorrower(borrower);
			} else {
				List<Borrower> borrowers = adao.readAllBorrowers();
				return borrowers;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<LibraryBranch> readLibraryBranch(Integer branchId, Boolean byCardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibraryBranchDAO bdao = new LibraryBranchDAO(conn);
			if (branchId != null & byCardNo == true) {// return branches
				List<LibraryBranch> branches = bdao.readBranchesByCardNo(branchId);
				int counter = 1;
				for (LibraryBranch a : branches) {
					System.out.println("\t" + counter + ") " + a.getBranchName());
					counter++;
				}
				return branches;
			} else {
				List<LibraryBranch> branches = bdao.readAllLibraryBranches();
				int counter = 1;
				for (LibraryBranch a : branches) {
					System.out.println("\t" + counter + ") " + a.getBranchName());
					counter++;
				}
				return branches;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Book> readBook(Integer branchId, Integer cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			if (cardNo != null && branchId != null) {
				List<Book> books = bdao.readAllBooksByCardAndBranch(cardNo, branchId);// where not turned in
				return books;
			} else if (branchId != null) {
				List<Book> books = bdao.readAllBooksByCardAndBranchWithCopy(branchId);
				return books;
			} else {
				List<Book> books = bdao.readAllBooks();
				return books;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void saveBookLoan(BookLoan loan, Integer numCopies) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDAO adao = new BookLoanDAO(conn);
			if (numCopies != null) {
				BookCopiesDAO cdao = new BookCopiesDAO(conn);
				BookCopies copy = new BookCopies();
				copy.setBookId(loan.getBookId());
				copy.setBranchId(loan.getBranchId());
				List<BookCopies> copies = cdao.readBookCopy(copy);
				if (copies.size() == 0) { // no copies of book find in branch for book
					cdao.addBookCopies(copy);
				} else {
					copy.setNoOfCopies((copies.get(0).getNoOfCopies() + numCopies)); // update copies count
				}

				if (numCopies > 0) { // returned book
					adao.updateBookLoan(loan);
				} else {// new book loan
					adao.addBookLoan(loan);
				}
			}
			conn.commit(); // transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<BookLoan> readBookloans(BookLoan loan) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDAO adao = new BookLoanDAO(conn);
			List<BookLoan> loans = adao.readBookLoanByBookBranchCardNo(loan);
			return loans;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
}
