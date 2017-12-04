package mediaone.service;

import java.util.List;

import mediaone.dao.ProductRepository;
import mediaone.dao.ProductRepositoryImpl;
import mediaone.model.DetailBill;
import mediaone.model.Product;

public class TransactionServiceImpl implements TransactionService {
	ProductRepository<Product> productRepository = new ProductRepositoryImpl();

	@Override
	public Product addProductToBill(String id, int number) {
		Product product = productRepository.findOne(id);
		
		if (number < 0 || product == null || product.getQuantity() < number) {
			return null;
		}
		product.setQuantity(number);
		return product;		
	}

	@Override
	public Double pay(DetailBill detailBill) {
		List<Product> products = detailBill.getProducts();
		for (Product product : products) {
			Product repoProduct = productRepository.findOne(product.getIdProduct());
			repoProduct.setQuantity(repoProduct.getQuantity() - product.getQuantity());
			productRepository.update(repoProduct);
		}
		return detailBill.getPrice();
	}
}
