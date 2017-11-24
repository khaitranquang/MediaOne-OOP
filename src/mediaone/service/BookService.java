package mediaone.service;

import java.util.ArrayList;

import mediaone.model.Book;
import mediaone.model.Statistic;

public interface BookService {
	
	// Get one Book
	public Book getBook();
	
	// Get all Book from database
	public ArrayList<Book> getAllBook();
	
	// Update one Book
	public boolean updateBook ();
	
	// Insert one book
	public boolean insertBook ();
	
	// Delete one Book
	public boolean deleteBook ();
	
	// Statistic Book
	public ArrayList<Statistic> statisticBook(String colName);
}
