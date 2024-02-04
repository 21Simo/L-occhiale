package model;

import java.util.ArrayList;

public class Carrello 
{
	public Carrello()
	{
		
	}
	
	public String getImmagine() 
	{
		return immagine;
	}
	
	public void setImmagine(String immagine) 
	{
		this.immagine = immagine;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getColore() 
	{
		return colore;
	}
	
	public void setColore(String colore)
	{
		this.colore = colore;
	}
	
	public String getPrezzo()
	{
		return prezzo;
	}
	
	public void setPrezzo(String prezzo)
	{
		this.prezzo = prezzo;
	}
	
	public String getTotaleCosto() 
	{
		return totaleCosto;
	}
	
	public void setTotaleCosto(String totaleCosto)
	{
		this.totaleCosto = totaleCosto;
	}
	
	public int getQuantitaCarrello() 
	{
		return quantitaCarrello;
	}
	
	public void setQuantitaCarrello(int quantitaCarrello) 
	{
		this.quantitaCarrello = quantitaCarrello;
	}
	
	public void aggiungiProdotto(ProdottoCarrello prodotto)
	{
		this.listaProdotti.add(prodotto);
	}
	
	public boolean duplicato(ArrayList<ProdottoCarrello> lista)
	{
		for(int i=0; i<this.listaProdotti.size()-1; i++)
		{
			for(int k=i+1; k<this.listaProdotti.size(); k++)
			{
				if(this.listaProdotti.get(i).getColore().getCodiceProdotto().equals(this.listaProdotti.get(k).getColore().getCodiceProdotto()))
				{
					if(this.listaProdotti.get(i).getGradazione().equals(this.listaProdotti.get(k).getGradazione()))
					{
						if(this.listaProdotti.get(i).getNomeFile().equals(this.listaProdotti.get(k).getNomeFile()))
						{
							this.listaProdotti.remove(k);
							System.out.println(this.listaProdotti.size());
							int quantitàProdotto=this.listaProdotti.get(i).getColore().getQuantità();
							quantitàProdotto++;
							this.listaProdotti.get(i).getColore().setQuantità(quantitàProdotto);
							return true;
						}
					}					
				}
			}
		}
		return false;
	}
	
	public ArrayList<ProdottoCarrello> getListaProdotti() 
	{
		return listaProdotti;
	}


	private String immagine;
	private String nome;
	private String colore;
	private String prezzo;
	private String totaleCosto;
	private int quantitaCarrello;
	private ArrayList<ProdottoCarrello> listaProdotti=new ArrayList<ProdottoCarrello>();
}
