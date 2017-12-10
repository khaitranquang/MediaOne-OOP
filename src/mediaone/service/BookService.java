package mediaone.service;

import java.util.List;
import java.util.stream.Collectors;

import mediaone.dao.BookRepository;
import mediaone.dao.ProductRepository;
import mediaone.model.Book;

public class BookService implements ProductService<Book> {
	ProductRepository<Book> productRepository = new BookRepository();
	
	@Override
	public List<Book> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	public Book add(Book product) {
		if (product.getIdProduct().trim().equals("") || product.getNameProduct().trim().equals("") ||
			product.getPublisher().trim().equals("") || product.getAuthor().trim().equals("") ||
			Integer.toString(product.getQuantity()).trim().equals("") ||
			Double.toString(product.getInPrice()).trim().equals("") ||
			Double.toString(product.getOutPrice()).trim().equals("") ||
			product.getQuantity() < 0 || product.getInPrice() < 0 || product.getOutPrice() < 0) {
			return null;
		}
		if (productRepository.findOne(product.getIdProduct()) != null) {
			return null;
		}
		return productRepository.add(product);
	}

	@Override
	public Book update(Book product) {
		if (product.getIdProduct().trim().equals("") || product.getNameProduct().trim().equals("") ||
			product.getPublisher().trim().equals("") || product.getAuthor().trim().equals("") ||
			Integer.toString(product.getQuantity()).trim().equals("") ||
			Double.toString(product.getInPrice()).trim().equals("") ||
			Double.toString(product.getOutPrice()).trim().equals("") ||
			product.getQuantity() < 0 || product.getInPrice() < 0 || product.getOutPrice() < 0) {
			return null;
		}
		return productRepository.update(product);
	}

	@Override
	public List<Book> findBySpecialProps(Book product) {
		List<Book> books = productRepository.findAll();
		
		return books.stream()
					.filter(e->checkFilter(e, product))
					.collect(Collectors.toList());
	}
	
	private boolean checkFilter(Book book, Book filterBook) {
		return book.getIdProduct().contains(filterBook.getIdProduct()) 
			   && book.getAuthor().contains(filterBook.getAuthor())
			   && book.getPublisher().contains(filterBook.getPublisher());
	}

	@Override
	public boolean remove(String id) {
		return productRepository.removeByID(id);
	}

	@Override
	public Book findOne(String id) {
		return productRepository.findOne(id);
	}	
}
