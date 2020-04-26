package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public Integer addGenre(Genre genre) throws ClassNotFoundException, SQLException{
		return saveWithPK("INSERT INTO tbl_book_genres (genreName) VALUES (?)", new Object[] {genre.getGenreName()});
	}

	public void updateGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_genres SET genreName = ? WHERE genreId = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}

	public void deleteGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_genres WHERE genreId = ?", new Object[]{genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_book_genress", null);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genreId"));
			genre.setGenreName(rs.getString("genreName"));
			genres.add(genre);
		}
		return genres;
	}

}
