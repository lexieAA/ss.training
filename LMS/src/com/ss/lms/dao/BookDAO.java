package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;

public class BookDAO extends BaseDAO<Book> {
	public BookDAO(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book (title, pubId) VALUES (?,?)",
				new Object[] { book.getTitle(), book.getPublisherId() });
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET title = ?, pubId =? WHERE bookId = ?",
				new Object[] { book.getTitle(), book.getPublisherId(), book.getBookId() });
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> readBookID(Book book) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book Where pubId = ? AND title = ?", new Object[] { book.getPublisherId(), book.getTitle() });
	}

	public List<Book> readAllBooksByAuthor(Integer authorId) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book where bookId IN (select bookId from tbl_book_authors where authorId= ?)",
				new Object[] { authorId });
	}

	public List<Book> readAllBooksByBranchFromCopy(Integer branchId) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ?)",
				new Object[] { branchId });
	}

	public List<Book> readAllBooksByBranchFromLoan(Integer branchId) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE branchId = ?)",
				new Object[] { branchId });
	}

	public List<Book> readAllBooksByCardAndBranch(Integer cardNo, Integer branchId)
			throws ClassNotFoundException, SQLException {
		return read(
				"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE branchId = ? AND cardNo = ?)",
				new Object[] { branchId, cardNo });
	}

	public List<Book> try2(BookLoan loan) throws ClassNotFoundException, SQLException {
		return read(
				"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE branchId = ? AND cardNo = ? AND dateIn = ?)",
				new Object[] { loan.getBranchId(), loan.getCardNo(), loan.getDateIn() });
	}

	public List<Book> readAllBooksByCardAndBranchWithCopy(Integer branchId)
			throws ClassNotFoundException, SQLException {
		return read(
				"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies >= 1)",
				new Object[] { branchId });
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPublisherId(rs.getInt("pubId"));
			books.add(book);
		}
		return books;
	}

}
