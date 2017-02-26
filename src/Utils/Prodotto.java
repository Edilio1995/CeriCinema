package Utils;

public class Prodotto 
{
	private String nome;
	private	String id;
	private String foto;
	private Double prezzo;
	private int quantit�;
	private Double totale;

	public Prodotto()
	{
		
	}
	
	public Prodotto(String nome, String id, String foto, Double prezzo, int quantit�)
	{
		this.nome = nome;
		this.id = id;
		this.foto = foto;
		this. prezzo = prezzo;
		this.quantit� = quantit�;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public Double getTotale(){
		return totale;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	public void calcolaPrezzo()
	{
		totale=prezzo*quantit�;
	}
	
	public void diminuisci()
	{
		if(quantit� > 1)
		{
			totale=totale-prezzo;
			quantit�--;
		}
	}

	@Override
	public String toString() {
		return "Prodotto [nome=" + nome + ", id=" + id + ", foto=" + foto + ", prezzo=" + prezzo + "]";
	}
	
	
	
	
	
	
	
}

