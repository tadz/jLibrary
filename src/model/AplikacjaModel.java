package model;

import java.util.*;
import java.sql.*;

public class AplikacjaModel {

	private int id = 0;
	
	public List<Book> findAll() {
		try {
			List<Book> listBooks = new ArrayList<>();
			
			PreparedStatement ps = DB.getConnection().prepareStatement(
					"select * from BOOKSTORE");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book p = new Book(
						++id, 
						rs.getString("title"), 
						rs.getString("author"), 
						rs.getString("publisher"), 
						rs.getInt("publicationYear"),
						rs.getString("owner")
					);
				
				listBooks.add(p);
			}
			
			return listBooks;
		} catch (Exception e) {
			return null;
		}
	}
}