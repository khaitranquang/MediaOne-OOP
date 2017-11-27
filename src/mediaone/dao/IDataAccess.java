package mediaone.dao;

import java.util.List;

import mediaone.model.Product;

public interface IDataAccess <T> {
	public List<Product> listProduct();
	public List<T> loadProduct();
	public String addProduct(T product);
	public String delProductID(String idProduct);
	public boolean hasProductID(String idProduct);
	public String updateProduct(T product);
}
