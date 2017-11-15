package mediaone.model;

public class DetailBill {
	private String idBill;
	private String idProduct;
	private String quantityBill;
	
	public String getIdBill() {
		return idBill;
	}
	public String getIdProduct() {
		return idProduct;
	}
	public String getQuantityBill() {
		return quantityBill;
	}
	
	public DetailBill(String idBill, String idProduct, String quantityBill) {
		super();
		this.idBill = idBill;
		this.idProduct = idProduct;
		this.quantityBill = quantityBill;
	}
}
