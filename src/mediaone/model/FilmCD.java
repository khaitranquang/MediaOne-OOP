package mediaone.model;

public class FilmCD extends Product{
	private String director;
	private String actor;
	
	public String getDirector() {
		return director;
	}
	public String getActor() {
		return actor;
	}
	
	public FilmCD(String idProduct, String nameProduct, int quantity, double outPrice, double inPrice, String director, String actor) {
		super(idProduct, nameProduct, quantity, outPrice, inPrice);
		this.director = director;
		this.actor = actor;
	}

}
