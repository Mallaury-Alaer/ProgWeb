package metier;

public class Product{
	private int ref;
	private String lib;
	private String des;
	private Sting url;
	private int prix;

	public Product(int ref, String lib, String des, String url, int prix){
		this.ref = ref; 
		this.lib = lib;
		this.des = des;
		this.url = url;
		this.prix = prix;
	}

	public int getRef(){
		return this.ref;
	}

	public String getLib(){
		return this.lib;
	}

	public String getDes(){
		return this.des;
	}

	public String getUrl(){
		return this.url;
	}

	public int getPrix(){
		return this.prix;
	}
}