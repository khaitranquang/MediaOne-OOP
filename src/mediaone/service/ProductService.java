package mediaone.service;

import java.util.List;

import mediaone.model.Product;
/**
 * All service in manager product
 * @author Chu lun Kute
 *
 * @param <T>
 */
public interface ProductService<T extends Product> {
	/**
	 * Load all product from database
	 * @return products
	 */
	List<T> findAll();
	/**
	 * Add product, update if product is exist
	 * @param product
	 * @return product
	 */
	T add(T product);
	/**
	 * Query product by special properties 's product
	 * @param product
	 * @return products
	 */
	List<T> findBySpecialProps(T product);
	
	/**
	 * Update product
	 * @param product
	 * @return product updated
	 */
	T update(T product);
	/**
	 * Remove product by id
	 * @param id
	 * @return true if removed
	 */
	boolean remove(String id);
	
	/**
	 * Get a product
	 * @param id
	 * @return Product has this id
	 */
	T findOne(String id);
	
	
	
}
