package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static final String ID = null;
	Connection c = null;
	Statement stmt = null;

	 //deklaracja metody polaczenia z baza danych
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			connection = null;
		}
		
		return connection;
	}
	
	public void setConnection() {
		try {
			this.c = DB.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setStatement() {
		try {
			this.stmt = DB.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	// deklaracja metody zamkniacia polaczenia z baza danych
	public void closeConnection() {
		try {
			this.stmt.close();
			this.c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// usuniecie bazy
	public void dropTableBookstore() {
		String sql = "DROP TABLE BOOKSTORE";

		try {
			this.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// stworzenie tabli w bazie danych
	public void createTableBookstore() {
		try {
			String sql = "CREATE TABLE BOOKSTORE "
					+ "(id INT PRIMARY KEY     NOT NULL,"
					+ " title          TEXT    NOT NULL, "
					+ " author          TEXT     NOT NULL, "
					+ " publisher    TEXT     NOT NULL,"
					+ " publicationYear        INT)";
			this.stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	// dodanie rekordu do tabeli w bazie
	public void addRecordToBookstore(String title, String author,
			String publisher, int publicationYear) {
		try {
			String sql = "INSERT INTO BOOKSTORE (id, title, author, publisher, publicationYear)"
					+ "VALUES (1,'"
					+ title
					+ "','"
					+ author
					+ "','"
					+ publisher + "'," + publicationYear + ")";
			this.stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static String getId() {
		return ID;
	}
}