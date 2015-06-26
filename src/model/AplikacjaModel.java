package model;

import java.util.*;
import java.sql.*;

public class AplikacjaModel {

	public static Vector<Book> findAll() {
		try {
			Vector<Book> listBooks = new Vector<Book>();

			PreparedStatement ps = DB.getConnection().prepareStatement(
					"select * from BOOKSTORE");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book b = new Book(rs.getInt("id"), rs.getString("title"),
						rs.getString("author"), rs.getString("publisher"),
						rs.getInt("publicationYear"), rs.getString("owner"));

				listBooks.add(b);
			}

			return listBooks;
		} catch (Exception e) {
			return null;
		}
	}
}