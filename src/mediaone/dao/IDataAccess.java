package mediaone.dao;

import java.util.List;

import mediaone.model.Product;

public interface IDataAccess {
	public List<Product> listProduct();
	public <T> List<T> loadProduct();
	public <T> String addProduct(T product);
	public String delProductID(String idProduct);
	public boolean hasProductID(String idProduct);
}
