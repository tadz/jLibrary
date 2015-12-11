package model;

public class Book {
	private String title, author, publisher,isbn, owner;
	private int id, publicationYear;

	public Book(int id, String title, String author, String publisher,
			int publicationYear, String isbn, String owner) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.owner = owner;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
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
