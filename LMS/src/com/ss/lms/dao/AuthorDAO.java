package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { author.getAuthorName() });
	}
	
	public void addAuthorBooK(Integer authorId, Integer bookId) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_authors (authorId, bookId) VALUES (?)", new Object[] { authorId, bookId });
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] { author.getAuthorId() });
	}

	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_author", null);
	}
	
//	public List<Author> readAuthorByBook() throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_author WHERE authorId IN (SELECT bookId FROM tbl_book_authors WHERE authorId = ?", null);
//	}
	
	public List<Author> readAuthorByBookID(Book book) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)",
				new Object[] { book.getBookId() });
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}

}
