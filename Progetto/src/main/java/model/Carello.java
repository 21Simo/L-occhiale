package model;

import java.util.ArrayList;

public class Carello 
{
	public Carello()
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
	
	public int getQuantitaCarello() 
	{
		return quantitaCarello;
	}
	
	public void setQuantitaCarello(int quantitaCarello) 
	{
		this.quantitaCarello = quantitaCarello;
	}
	
	public void aggiungiProdotto(ProdottoCarello prodotto)
	{
		this.listaProdotti.add(prodotto);
	}
	
	public boolean duplicato(ArrayList<ProdottoCarello> lista)
	{
		for(int i=0; i<this.listaProdotti.size()-1; i++)
		{
			for(int k=i+1; k<this.listaProdotti.size(); k++)
			{
				if(this.listaProdotti.get(i).getColore().getCodiceProdotto().equals(this.listaProdotti.get(k).getColore().getCodiceProdotto()))
				{
					if(this.listaProdotti.get(i).getGradazione().equals(this.listaProdotti.get(k).getGradazione()))
					{
						if(this.listaProdotti.get(i).getFile().equals(this.listaProdotti.get(k).getFile()))
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
	
	public ArrayList<ProdottoCarello> getListaProdotti() 
	{
		return listaProdotti;
	}


	private String immagine;
	private String nome;
	private String colore;
	private String prezzo;
	private String totaleCosto;
	private int quantitaCarello;
	private ArrayList<ProdottoCarello> listaProdotti=new ArrayList<ProdottoCarello>();
}
