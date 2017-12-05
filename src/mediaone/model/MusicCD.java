package mediaone.model;

public class MusicCD extends Product{
	private String singerName;
	private String type;
	
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSingerName() {
		return singerName;
	}
	public String getType() {
		return type;
	}

	public MusicCD(String idProduct, String nameProduct, int quantity, double outPrice, double inPrice, String singerName, String type) {
		super(idProduct, nameProduct, quantity, outPrice, inPrice);
		this.singerName = singerName;
		this.type = type;
	}
	
	public MusicCD() {
		
	}
	
}
