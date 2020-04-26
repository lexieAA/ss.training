package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public Integer addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		return saveWithPK("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?)", 
				new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}

	public void updatePublisher(Publisher publisher)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_publisher SET publisherName = ? , publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?", 
				new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
	}

	public void deletePublisher(Publisher publisher)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisher.getPublisherId()});
	}
	
	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_publisher", null);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}

}
