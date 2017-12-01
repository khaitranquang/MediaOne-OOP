package mediaone.dao;

import java.util.List;

import mediaone.model.Product;

public interface ProductRepository<T extends Product>{

	List<T> findAll();

	T add(T product);

	boolean removeByID(String id);

	T update(T product);

	T findOne(String id);

}