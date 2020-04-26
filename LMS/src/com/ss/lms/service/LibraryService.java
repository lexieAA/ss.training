package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.LibraryBranchDAO;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.LibraryBranch;

public class LibraryService {
	public ConnectionUtil connUtil = new ConnectionUtil();

	public void saveLibraryBranch(LibraryBranch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			if (branch.getBranchId() != null && branch.getBranchName() != null) {
				adao.updateLibraryBranch(branch);
			} else if (branch.getBranchId() != null) {
				adao.deleteLibraryBranch(branch);
			} else {
				adao.addLibraryBranch(branch);
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

	public List<LibraryBranch> readLibraryBranches(Integer pk) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			if (pk != null) {
				LibraryBranch branch = new LibraryBranch();
				branch.setBranchId(pk);
				// return adao.readNameLibraryBranches(branch);
			} else {
				List<LibraryBranch> branches = adao.readAllLibraryBranches();
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

	public List<Book> readBook(Integer branchId, Integer cardNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			if(cardNo != null & branchId == null) {
				List<Book> books = bdao.readAllBooksByCardAndBranch(cardNo, branchId);
				return books;
			}if(branchId != null) {
				List<Book> books = bdao.readAllBooksByCardAndBranchWithCopy(branchId);
				return books;
			}else {
				List<Book> books = bdao.readAllBooks();
				int counter = 1;
				for (Book a : books) {
					System.out.println("\t" + counter + ") " + a.getTitle());
					counter++;
				}
				return books;
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

	public List<BookCopies> readBookCopies(Integer branchId, Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO adao = new BookCopiesDAO(conn);
			BookCopies copy = new BookCopies();
			copy.setBookId(bookId);
			copy.setBranchId(branchId);
			List<BookCopies> bookCopy = adao.readBookCopy(copy);
				return bookCopy;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	public void saveBookCopies(BookCopies copy, Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO adao = new BookCopiesDAO(conn);
			if(bookId != null) {
				copy.setBookId(bookId);
				copy.setNoOfCopies(0);
				adao.addBookCopies(copy);
				
			}else {
				adao.updateBookCopies(copy);
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
}
