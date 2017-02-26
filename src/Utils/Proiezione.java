package Utils;

public class Proiezione extends Film
{
	private String sala;
	private String prezzo;
	private String dataProiezione;
	
	public Proiezione()
	{
		
	}
	public Proiezione(String nome, String calendar, String genere, String descrizione, String locandina, String sala, String prezzo, String dataProiezione)
	{
		super();
		this.sala = sala;
		this.prezzo = prezzo;
		this.dataProiezione =dataProiezione;
		this.setNome(nome);
		this.setData(calendar);
		this.setDescrizione(descrizione);
		this.setGenere(genere);
		this.setLocandina(locandina);
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getDataProiezione() {
		return dataProiezione;
	}

	public void setDataProiezione(String dataProiezione) {
		this.dataProiezione = dataProiezione;
	}
	
	
}
