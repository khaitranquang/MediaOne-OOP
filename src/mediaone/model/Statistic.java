package mediaone.model;

public class Statistic {
	private String name;
	private int sum;
	
	public Statistic() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Statistic(String name, int sum) {
		super();
		this.name = name;
		this.sum = sum;
	}
}
