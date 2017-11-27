package mediaone.model;

public class Staff {
	private String idStaff;
	private String nameStaff;
	private String pass;
	private Double salary;
	private int days;
	
	public String getIdStaff() {
		return idStaff;
	}
	public String getNameStaff() {
		return nameStaff;
	}
	public String getPass() {
		return pass;
	}
	public Double getSalary() {
		return salary;
	}
	
	public int getDays() {
		return days;
	}
	
	public Staff(String idStaff, String nameStaff, String pass, Double salary, int days) {
		super();
		this.idStaff = idStaff;
		this.nameStaff = nameStaff;
		this.pass = pass;
		this.salary = salary;
		this.days = days;
	}
}
