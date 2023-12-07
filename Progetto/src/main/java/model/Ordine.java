package model;


public class Ordine 
{		
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getIdOrdine() 
	{
		return idOrdine;
	}
	
	public void setIdOrdine(int idOrdine) 
	{
		this.idOrdine = idOrdine;
	}
	
	public int getIdProdotto() 
	{
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) 
	{
		this.idProdotto = idProdotto;
	}
	
	
	private int id;
	private int idOrdine;
	private int idProdotto;
}
