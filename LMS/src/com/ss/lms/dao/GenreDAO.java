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

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] {genre.getGenreName()});
	}

	public void updateGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}

	public void deleteGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[]{genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_genre", null);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
