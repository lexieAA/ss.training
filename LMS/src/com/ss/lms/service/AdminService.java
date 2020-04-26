package com.ss.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoanDAO;
import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;

public class AdminService {
	
	public ConnectionUtil connUtil = new ConnectionUtil();
	
	public void saveAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(author.getAuthorId()!=null && author.getAuthorName()!=null){
				adao.updateAuthor(author);
			}else if(author.getAuthorId()!=null){
				adao.deleteAuthor(author);
			}else{
				adao.addAuthor(author);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Author> readAuthors(Integer pk, String authorName) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			BookDAO bdao = new BookDAO(conn);
			if(pk!=null){
				//get author by primary key
			}else if(authorName!=null){
				//searchAuthors
			}else{
				List<Author> authors = adao.readAllAuthors();
				for(Author a: authors){
					a.setBooks(bdao.readAllBooksByAuthor(a.getAuthorId()));
				}
				return authors;
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
	
	public List<BookLoan> readBookLoan(BookLoan loan, Date dateOut) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDAO adao = new BookLoanDAO(conn);
			if(dateOut != null) {
				List<BookLoan> loans = adao.readBookLoanByBookBranchCardNo(loan);
				return loans;
			}else {
				loan.setDateOut(dateOut);
				List<BookLoan> loans = adao.readAllBookLoansByKeys(loan);
				return loans;
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
	
	public void saveBookLoan(BookLoan loan) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDAO adao = new BookLoanDAO(conn);
			adao.updateBookLoanDueDate(loan);
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
	}

//-------------------------------------------------------------------------------
	public void savePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			if(publisher.getPublisherId()!=null && publisher.getPublisherName()!=null){
				adao.updatePublisher(publisher);
			}else if(publisher.getPublisherId()!=null){
				adao.deletePublisher(publisher);
			}else{
				adao.addPublisher(publisher);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void readPublishers() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			List <Publisher> publisher = adao.readAllPublishers();
			int counter = 1;
			for (Publisher a : publisher) {
				System.out.println("\t" + counter + ") " + a.getPublisherName());
				counter++;
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
	}

}