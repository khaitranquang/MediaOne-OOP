package mediaone.model;

public class FilmCD extends Product {
	private String director;
	private String type;

	public FilmCD(String idProduct, String nameProduct, int quantity, double outPrice, double inPrice, String director,
			String type) {
		super(idProduct, nameProduct, quantity, outPrice, inPrice);
		this.director = director;
		this.type = type;
	}

	public String getDirector() {
		return director;
	}

	public String getType() {
		return type;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setType(String type) {
		this.type = type;
	}
}
