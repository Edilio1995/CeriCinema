package Utils;

import java.util.ArrayList;

public class Carrello 
{
	private ArrayList<Prodotto> carrello = new ArrayList<Prodotto>();
	
	public Carrello()
	{
		
	}
	
	

	public ArrayList<Prodotto> getCarrello() 
	{
		return carrello;
	}

	public double getTotale()
	{
		int i=0;
		double tot=0.0;
		Prodotto tmp=null;
		for(i = 0; i < carrello.size(); i++)
		{
			tmp=carrello.get(i);
			tot=tot+tmp.getTotale();
		}
		return tot;
	}
	
	public void setCarrello(ArrayList<Prodotto> carrello) 
	{
		this.carrello = carrello;
	}
	
	public Prodotto getProdotto(int i)
	{
		return carrello.get(i);
	}

	public String showCarrello()
	{
		int i=0;
		Prodotto tmp;
		String result=null;
		for(i=0;i<carrello.size();i++)
		{
			tmp=carrello.get(i);
			result=result+tmp.toString()+"\n";
		}
		return result;
	}

	
	public void addProdotto(Prodotto prodotto)
	{
		carrello.add(prodotto);
	}
	
	public void removeProdotto(String id)
	{
		for(int i = 0; i < carrello.size(); i++)
		{
			if(id.equals(carrello.get(i).getId()))
			{
				carrello.remove(i);
			}
		}
	}
	
	public int getSize()
	{
		int somma = 0;
		for(int i = 0; i < carrello.size(); i++)
		{
			somma = somma + carrello.get(i).getQuantità();
		}
		return somma;
	}
	
}

