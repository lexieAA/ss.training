package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower>{

	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public Integer addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		return saveWithPK("INSERT INTO tbl_borrower (name, address, phone) VALUES (?)", 
				new Object[] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}

	public void updateBorrower(Borrower borrower)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_borrower SET name = ? , address = ?, phone = ? WHERE cardNo = ?", 
				new Object[] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone(), borrower.getCardNo()});
	}

	public void deleteBorrower(Borrower borrower)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_borrower", null);
	}
	
	public List<Borrower> readNameBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			borrower.setBorrowerAddress(rs.getString("address"));
			borrower.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

}
