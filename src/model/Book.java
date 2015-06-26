package model;

public class Book {
	private String title, author, publisher;
	private int id, publicationYear;
	
	// usunęłam argumenty z Product, zeby productModel nie wywalał błędu.
	public Book(int id, String title, String author, String publisher, int publicationYear) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}
	
	public String gettitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
}
