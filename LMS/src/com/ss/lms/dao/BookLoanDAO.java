package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan> {

	public BookLoanDAO(Connection conn) {
		super(conn);
	}

	public void addBookLoan(BookLoan loan) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,CURRENT_TIMESTAMP, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 7 DAY),'0000-00-00 00:00:00')",
				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo() });
	}

	public void updateBookLoan(BookLoan loan) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dateIn = DATE_ADD(?, INTERVAL 1 DAY) WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
				new Object[] { loan.getDateIn(), loan.getBookId(), loan.getBranchId(), loan.getCardNo(),
						loan.getDateOut() });
	}

	public void updateBookLoanDate(BookLoan loan) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dateOut = CURRENT_TIMESTAMP, dueDate = DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 7 DAY) WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
	}

	public void updateBookLoanDueDate(BookLoan loan) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dueDate = ? WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
				new Object[] { loan.getDueDate(), loan.getBookId(), loan.getBranchId(), loan.getCardNo(),
						loan.getDateOut() });
	}

	public void deleteBookLoan(BookLoan loan) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
	}

	public List<BookLoan> readAllBookLoans() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_loans", null);
	}

	public List<BookLoan> readAllBookLoansByKeys(BookLoan loan) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
	}

	public List<BookLoan> readBookLoanByBookBranchCardNo(BookLoan loan) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?",
				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo() });
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> loans = new ArrayList<>();
		while (rs.next()) {
			BookLoan loan = new BookLoan();
			loan.setBookId(rs.getInt("bookId"));
			loan.setBranchId(rs.getInt("branchId"));
			loan.setCardNo(rs.getInt("cardNo"));
			loan.setDateOut(rs.getDate("dateOut"));
			loan.setDueDate(rs.getDate("dueDate"));
			loan.setDateIn(rs.getDate("dateIn"));
			loans.add(loan);
		}
		return loans;
	}

}
