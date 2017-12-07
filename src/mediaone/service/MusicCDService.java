package mediaone.service;

import java.util.List;
import java.util.stream.Collectors;

import mediaone.dao.MusicCDRepository;
import mediaone.dao.ProductRepository;
import mediaone.model.MusicCD;

public class MusicCDService implements ProductService<MusicCD>{
	ProductRepository<MusicCD> productRepository = new MusicCDRepository();
	
	@Override
	public List<MusicCD> findAll() {
		return productRepository.findAll();
	}

	@Override
	public MusicCD add(MusicCD product) {
		if (product.getQuantity() < 0 || product.getInPrice() < 0 || product.getOutPrice() < 0) {
			return null;
		}
		if (productRepository.findOne(product.getIdProduct()) != null) {
			return productRepository.update(product);
		}
		return productRepository.add(product);
	}

	@Override
	public List<MusicCD> findBySpecialProps(MusicCD product) {
		List<MusicCD> musicCDs = productRepository.findAll();
		
		return musicCDs.stream()
					.filter(e->checkFilter(e, product))
					.collect(Collectors.toList());	
	}
	
	private boolean checkFilter(MusicCD musicCD, MusicCD filterMusicCD) {
		return musicCD.getIdProduct().indexOf(filterMusicCD.getIdProduct()) >= 0 
			   && musicCD.getSingerName().indexOf(filterMusicCD.getSingerName()) >= 0
			   && musicCD.getType().indexOf(filterMusicCD.getType()) >= 0;
	}	
	
	@Override
	public MusicCD update(MusicCD product) {
		if (product.getQuantity() < 0 || product.getInPrice() < 0 || product.getOutPrice() < 0) {
			return null;
		}
		return productRepository.update(product);
	}

	@Override
	public boolean remove(String id) {
		return productRepository.removeByID(id);
	}

	@Override
	public MusicCD findOne(String id) {
		return productRepository.findOne(id);
	}
}
