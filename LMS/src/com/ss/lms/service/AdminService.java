package com.ss.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoanDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.GenreDAO;
import com.ss.lms.dao.LibraryBranchDAO;
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

//------------------------------Author---------------------------------------------------
	public void saveAuthor(Author author, int type) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if (type == 2) {
				adao.updateAuthor(author);
			} else if (type == 3) {
				adao.deleteAuthor(author);
			} else {
				adao.addAuthor(author);
			}
			System.out.println("Success");
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

	public List<Author> readAuthors(Integer pk, String authorName) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			BookDAO bdao = new BookDAO(conn);
				List<Author> authors = adao.readAllAuthors();
				for (Author a : authors) {
					List<String> bookTitles = new ArrayList<>();
					List<Book> books = bdao.readAllBooksByAuthor(a.getAuthorId());
					for (Book b : books) {
						bookTitles.add(b.getTitle());
					}
					a.setBooks(books);
					System.out.println("Author Name: " + a.getAuthorName());
					System.out.println("Author Id: " + a.getAuthorId());
					System.out.println("Author Book's: " + bookTitles.toString());
					System.out.println("-------------------------------------------");
				}
				return authors;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

//------------------------------Genre---------------------------------------------------
	public void saveGenre(Genre genre, int type) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			if (type == 2) {
				adao.updateGenre(genre);
			} else if (type == 3) {
				adao.deleteGenre(genre);
			} else {
				adao.addGenre(genre);
			}
			System.out.println("Success");
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

	public List<Genre> readGenres() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			List<Genre> genres = adao.readAllGenres();
			for (Genre a : genres) {
				System.out.println("Genre Name: " + a.getGenreName());
				System.out.println("Genre Id: " + a.getGenreId());
				System.out.println("-------------------------------------------");
			}
			return genres;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

//------------------------------LibraryBranch---------------------------------------------------
	public void saveLibraryBranch(LibraryBranch branch, int type) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			if (type == 2) {
				adao.updateLibraryBranch(branch);
			} else if (type == 3) {
				adao.deleteLibraryBranch(branch);
			} else {
				adao.addLibraryBranch(branch);
			}
			System.out.println("Success");
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

	public List<LibraryBranch> readLibraryBranchs() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			List<LibraryBranch> branchs = adao.readAllLibraryBranches();
			for (LibraryBranch a : branchs) {
				System.out.println("LibraryBranch Name: " + a.getBranchName());
				System.out.println("LibraryBranch Id: " + a.getBranchId());
				System.out.println("LibraryBranch Address: " + a.getBranchAddress());
				System.out.println("-------------------------------------------");
			}
			return branchs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

//------------------------------Publisher---------------------------------------------------
	public void savePublisher(Publisher publisher, int type) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			if (type == 2) {
				adao.updatePublisher(publisher);
			} else if (type == 3) {
				adao.deletePublisher(publisher);
			} else {
				adao.addPublisher(publisher);
			}
			System.out.println("Success");
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

	public List<Publisher> readPublishers() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			
				List<Publisher> publishers = adao.readAllPublishers();
				for (Publisher a : publishers) {
					System.out.println("Publisher Name: " + a.getPublisherName());
					System.out.println("Publisher Id: " + a.getPublisherId());
					System.out.println("Publisher Address: " + a.getPublisherAddress());
					System.out.println("Publisher Phone: " + a.getPublisherPhone());
					System.out.println("-------------------------------------------");
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

//------------------------------Borrower---------------------------------------------------
	public void saveBorrower(Borrower borrower, int type) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			if (type == 2) {
				adao.updateBorrower(borrower);
			} else if (type == 3) {
				adao.deleteBorrower(borrower);
			} else {
				adao.addBorrower(borrower);
			}
			System.out.println("Success");
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

	public List<Borrower> readBorrowers() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			List<Borrower> borrowers = adao.readAllBorrowers();
			for (Borrower a : borrowers) {
				System.out.println("Borrower Name: " + a.getBorrowerName());
				System.out.println("Borrower Card Number: " + a.getCardNo());
				System.out.println("Borrower Address: " + a.getBorrowerAddress());
				System.out.println("Borrower Phone: " + a.getBorrowerPhone());
				System.out.println("-------------------------------------------");
			}
			return borrowers;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

//-----------------------------BookLoan----------------------------------------------
	public List<BookLoan> readBookLoan(BookLoan loan, Date dateOut) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDAO adao = new BookLoanDAO(conn);
			if (dateOut != null) {
				loan.setDateOut(dateOut);
				List<BookLoan> loans = adao.readAllBookLoansByKeys(loan);
				return loans;
			} else {
				List<BookLoan> loans = adao.readBookLoanByBookBranchCardNo(loan);
				return loans;
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

	public void saveBookLoan(BookLoan loan) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDAO adao = new BookLoanDAO(conn);
			adao.updateBookLoanDueDate(loan);
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

//-------------------------------------------------------------------------------
	//------------------------------Book---------------------------------------------------
		public void saveBook(Book book, int type) throws SQLException {
			Connection conn = null;
			System.out.println("here");
			try {
				conn = connUtil.getConnection();
				BookDAO adao = new BookDAO(conn);
				if (type == 2) {
					adao.updateBook(book);
				} else if (type == 3) {
					adao.deleteBook(book);
				} else {
					adao.addBook(book);
				}
				System.out.println("Success");
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
		
		public List<Book> getBook(Book book) throws SQLException {
			Connection conn = null;
			System.out.println("here");
			try {
				conn = connUtil.getConnection();
				BookDAO adao = new BookDAO(conn);
				List<Book> books = adao.readBookID(book);
				conn.commit(); // transaction
				return books;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				if (conn != null) {
					conn.close();
				}
			}
			return null;
		}
		
		public void saveNewBookRelationships(Integer bookId, String[] newAuthorRelationships, 
				String[] newGenreRelationships, String[] newLibBranchRelationships) throws SQLException {
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				//update author book table
				AuthorDAO adao = new AuthorDAO(conn);
				for (String author: newAuthorRelationships) {
					adao.addAuthorBooK(Integer.valueOf(author), bookId);
				}
				//update genre book table
				GenreDAO gdao = new GenreDAO(conn);
				for (String genre: newGenreRelationships) {
					gdao.addGenreBooK(Integer.valueOf(genre), bookId);
				}
				//update library branch book table
				LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
				for (String branch: newLibBranchRelationships) {
					ldao.addLibraryBranchBooK(Integer.valueOf(branch), bookId);
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

		public List<Book> readBooks() throws SQLException {//readAuthorByBookID(Book book) 
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BookDAO bdao = new BookDAO(conn);
				AuthorDAO adao = new AuthorDAO(conn);
				GenreDAO gdao = new GenreDAO(conn);
				LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
				List<Book> books = bdao.readAllBooks();
				for (Book book : books) {
					System.out.println("Book Name: " + book.getTitle());
					System.out.println("Book Id: " + book.getBookId());
					System.out.println("Book Publisher: " + book.getPublisherId());
					
					//get all authors for book
					List<String> authorName = new ArrayList<>();
					List<Author> authors = adao.readAuthorByBookID(book);
					for (Author a : authors) {
						authorName.add(a.getAuthorName());
					}
					
					//get all genres for book
					List<String> genreName = new ArrayList<>();
					List<Genre> genres = gdao.readGenresByBookID(book);
					for (Genre a : genres) {
						genreName.add(a.getGenreName());
					}
					
					//get all library branches that have the book
					List<String> branchName = new ArrayList<>();
					List<LibraryBranch> branches = ldao.readLibraryBranchesByBookID(book);
					for (LibraryBranch a : branches) {
						branchName.add(a.getBranchName());
					}
					
					System.out.println("Book's Author(s): " + authorName.toString());
					System.out.println("Book's Genre(s): " + genreName.toString());
					System.out.println("Library Branches with Copies: " + branchName.toString());
					System.out.println("-------------------------------------------");
				}
				return books;
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