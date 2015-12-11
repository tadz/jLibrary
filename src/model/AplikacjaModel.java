package model;

import java.util.*;
import java.sql.*;

public class AplikacjaModel {

	public static Vector<Book> findAll() {
		Vector<Book> listBooks = new Vector<Book>();
		
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(
					"select * from BOOKSTORE");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book b = new Book(rs.getInt("id"), rs.getString("title"),
						rs.getString("author"), rs.getString("publisher"),
						rs.getInt("publicationYear"),rs.getString("isbn"), rs.getString("owner"));
				
				listBooks.add(b);
			}

			return listBooks;
		} catch (Exception e) {
			return listBooks;
		}
	}
}