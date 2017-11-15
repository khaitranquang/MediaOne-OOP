package mediaone.model;

public abstract class Product {
	private String idProduct;
	private String nameProduct;
	private int quantity;
	private double outPrice;
	private double inPrice;
	
	public String getIdProduct() {
		return idProduct;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getOutPrice() {
		return outPrice;
	}
	public double getInPrice() {
		return inPrice;
	}
	
	public Product(String idProduct, String nameProduct, int quantity, double outPrice, double inPrice) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.outPrice = outPrice;
		this.inPrice = inPrice;
	}
	
	
	
}
