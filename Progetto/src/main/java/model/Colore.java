package model;

public class Colore 
{
	public int getId()
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getIdProdotto() 
	{
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) 
	{
		this.idProdotto = idProdotto;
	}
	
	public String getColore()
	{
		return colore;
	}
	
	public void setColore(String colore) 
	{
		this.colore = colore;
	}
	
	public String getImmagine() 
	{
		return immagine;
	}
	
	public void setImmagine(String immagine)
	{
		this.immagine = immagine;
	}
	
	/*
	public double getPrezzo()
	{
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) 
	{
		this.prezzo = prezzo;
	}
	*/
	public String getPrezzo()
	{
		return prezzo;
	}
	
	public void setPrezzo(String prezzo) 
	{
		this.prezzo = prezzo;
	}
	
	public int getQuantità()
	{
		return quantità;
	}
	
	public void setQuantità(int quantità)
	{
		this.quantità = quantità;
	}
	
	public String getCodiceProdotto() 
	{
		return codiceProdotto;
	}
	
	public void setCodiceProdotto(String codiceProdotto)
	{
		this.codiceProdotto = codiceProdotto;
	}
	
	private int id;
	private int idProdotto;
	private String colore;
	private String immagine;
	//private double prezzo;
	private String prezzo;
	private int quantità;
	private String codiceProdotto;
}
