package mediaone.model;

public class Book extends Product {
	private String publisher;
	private String author;

	public Book() {

	}

	public Book(String idProduct, String nameProduct, int quantity, double outPrice, double inPrice, String publisher,
			String author) {
		super(idProduct, nameProduct, quantity, outPrice, inPrice);
		this.publisher = publisher;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		String book = null;
		book = super.toString();
		book += "Book \n[publisher=" + publisher + ",\n author=" + author + "]";
		return book;
	}

}
