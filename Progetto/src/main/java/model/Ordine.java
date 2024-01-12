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
	
	public int getIdColore() 
	{
		return idColore;
	}

	public void setIdColore(int idColore) 
	{
		this.idColore = idColore;
	}

	public String getFile() 
	{
		return file;
	}

	public void setFile(String file) 
	{
		this.file = file;
	}

	public int getQuantitàProdotto() 
	{
		return quantitàProdotto;
	}

	public void setQuantitàProdotto(int quantitàProdotto) 
	{
		this.quantitàProdotto = quantitàProdotto;
	}


	private int id;
	private int idOrdine;
	private int idProdotto;
	private int idColore;
	private String file;
	private int quantitàProdotto;
}
