package mediaone.service;

import java.util.List;
import java.util.stream.Collectors;

import mediaone.dao.FilmCDRepository;
import mediaone.dao.ProductRepository;
import mediaone.model.FilmCD;

public class FilmCDService implements ProductService<FilmCD>{
	ProductRepository<FilmCD> productRepository = new FilmCDRepository();
	
	@Override
	public List<FilmCD> findAll() {
		return productRepository.findAll();
	}

	@Override
	public FilmCD add(FilmCD product) {
		if (product.getIdProduct().trim().equals("") || product.getNameProduct().trim().equals("") ||
			product.getDirector().trim().equals("") || product.getType().trim().equals("") ||
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
	public List<FilmCD> findBySpecialProps(FilmCD product) {
		List<FilmCD> filmCDs = productRepository.findAll();
		
		return filmCDs.stream()
					.filter(e->checkFilter(e, product))
					.collect(Collectors.toList());	
	}
	
	private boolean checkFilter(FilmCD filmCD, FilmCD filterfilmCD) {
		return filmCD.getIdProduct().contains(filterfilmCD.getIdProduct()) 
			   && filmCD.getDirector().contains(filterfilmCD.getDirector())
			   && filmCD.getType().contains(filterfilmCD.getType());
	}	
	
	@Override
	public FilmCD update(FilmCD product) {
		if (product.getIdProduct().trim().equals("") || product.getNameProduct().trim().equals("") ||
			product.getDirector().trim().equals("") || product.getType().trim().equals("") ||
			Integer.toString(product.getQuantity()).trim().equals("") ||
			Double.toString(product.getInPrice()).trim().equals("") ||
			Double.toString(product.getOutPrice()).trim().equals("") ||
			product.getQuantity() < 0 || product.getInPrice() < 0 || product.getOutPrice() < 0) {
			return null;
		}
		return productRepository.update(product);
	}

	@Override
	public boolean remove(String id) {
		return productRepository.removeByID(id);
	}

	@Override
	public FilmCD findOne(String id) {
		return productRepository.findOne(id);
	}
}