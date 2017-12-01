package mediaone.model;

import java.time.LocalDate;

public class Bill {
	private int idBill;
	private String idStaff;
	private LocalDate exportDate;
	private DetailBill detailBill;

	public Bill() {

	}

	public Bill(int idBill, String idStaff, LocalDate exportDate) {
		super();
		this.idBill = idBill;
		this.idStaff = idStaff;
		this.exportDate = exportDate;
	}

	public int getIdBill() {
		return idBill;
	}

	public String getIdStaff() {
		return idStaff;
	}

	public LocalDate getExportDate() {
		return exportDate;
	}

	public DetailBill getDetailBill() {
		return detailBill;
	}

	public void setDetailBill(DetailBill detailBill) {
		this.detailBill = detailBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}

	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}

	public void setExportDate(LocalDate exportDate) {
		this.exportDate = exportDate;
	}
}
