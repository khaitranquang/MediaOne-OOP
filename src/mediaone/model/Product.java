package mediaone.model;

public class Product {
	private String idProduct;
	private String nameProduct;
	private int quantity;
	private double outPrice;
	private double inPrice;

	public Product() {

	}

	public Product(String idProduct, String nameProduct, int quantity, double outPrice, double inPrice) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.outPrice = outPrice;
		this.inPrice = inPrice;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public double getInPrice() {
		return inPrice;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public double getOutPrice() {
		return outPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public void setOutPrice(double outPrice) {
		this.outPrice = outPrice;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product \n[idProduct=" + idProduct + ",\n nameProduct=" + nameProduct + ",\n quantity=" + quantity
				+ ",\n outPrice=" + outPrice + ",\n inPrice=" + inPrice + "]";
	}
}
