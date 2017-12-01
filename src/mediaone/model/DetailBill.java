package mediaone.model;

import java.util.List;

public class DetailBill {
	private int idBill;
	private List<Product> products;

	public DetailBill() {

	}

	public DetailBill(int idBill, List<Product> products) {
		super();
		this.idBill = idBill;
		this.products = products;
	}

	public int getIdBill() {
		return idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Double getProfit() {
		double sum = 0;
		for (Product product : products) {
			sum+= (product.getOutPrice() - product.getInPrice())*product.getQuantity();
		}
		return sum;
	}
	
	public Double getPrice() {
		double sum = 0;
		for (Product product : products) {
			sum+= product.getOutPrice() * product.getQuantity();
		}
		return sum;
	}
}
