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

	public String getPrezzo() 
	{
		return prezzo;
	}

	public void setPrezzo(String prezzo) 
	{
		this.prezzo = prezzo;
	}

	public String getImmagineProdotto() 
	{
		return immagineProdotto;
	}

	public void setImmagineProdotto(String immagineProdotto) 
	{
		this.immagineProdotto = immagineProdotto;
	}

	public String getNomeProdotto() 
	{
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) 
	{
		this.nomeProdotto = nomeProdotto;
	}

	public String getColoreProdotto() 
	{
		return coloreProdotto;
	}

	public void setColoreProdotto(String coloreProdotto) 
	{
		this.coloreProdotto = coloreProdotto;
	}


	private int id;
	private int idOrdine;
	private int idProdotto;
	private int idColore;
	private String file;
	private int quantitàProdotto;
	private String prezzo;
	private String immagineProdotto;
	private String nomeProdotto;
	private String coloreProdotto;
}
