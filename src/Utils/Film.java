package Utils;


public class Film 
{
	
	private int id;
	private String nome;
	private String data;
	private String genere;
	private String descrizione;
	private String locandina;
	
	public Film(int id, String nome, String calendar, String genere, String descrizione, String locandina){
		this.id = id;
		this.nome = nome;
		this.data = calendar;
		this.genere = genere;
		this.descrizione = descrizione;
		this.locandina = locandina;
	}

	public Film()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLocandina() {
		return locandina;
	}

	public void setLocandina(String locandina) {
		this.locandina = locandina;
	}

	
}
