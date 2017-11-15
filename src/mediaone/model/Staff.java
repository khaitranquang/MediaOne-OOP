package mediaone.model;

public class Staff {
	private String idStaff;
	private String nameStaff;
	private String pass;
	private Double selary;
	
	public String getIdStaff() {
		return idStaff;
	}
	public String getNameStaff() {
		return nameStaff;
	}
	public String getPass() {
		return pass;
	}
	public Double getSelary() {
		return selary;
	}
	
	public Staff(String idStaff, String nameStaff, String pass, Double selary) {
		super();
		this.idStaff = idStaff;
		this.nameStaff = nameStaff;
		this.pass = pass;
		this.selary = selary;
	}
	
}
