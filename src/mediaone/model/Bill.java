package mediaone.model;

public class Bill {
	private String idBill;
	private String idStaff;
	private String exportDate;
	
	public String getIdBill() {
		return idBill;
	}
	public String getIdStaff() {
		return idStaff;
	}
	public String getExportDate() {
		return exportDate;
	}
	
	public Bill(String idBill, String idStaff, String exportDate) {
		super();
		this.idBill = idBill;
		this.idStaff = idStaff;
		this.exportDate = exportDate;
	}
}
