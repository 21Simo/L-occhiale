package model;

public class Comune
{
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getIdRegione() 
	{
		return idRegione;
	}
	
	public void setIdRegione(int idRegione) 
	{
		this.idRegione = idRegione;
	}
	
	public int getIdProvincia() 
	{
		return idProvincia;
	}
	
	public void setIdProvincia(int idProvincia) 
	{
		this.idProvincia = idProvincia;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getCodiceCatastale() 
	{
		return codiceCatastale;
	}
	
	public void setCodiceCatastale(String codiceCatastale) 
	{
		this.codiceCatastale = codiceCatastale;
	}
	
	public double getLatitudine() 
	{
		return latitudine;
	}
	
	public void setLatitudine(double latitudine) 
	{
		this.latitudine = latitudine;
	}
	
	public double getLongitudine() 
	{
		return longitudine;
	}
	
	public void setLongitudine(double longitudine) 
	{
		this.longitudine = longitudine;
	}
	
	
	
	private int id;
	private int idRegione;
	private int idProvincia;
	private String nome;
	private String codiceCatastale;
	private double latitudine;
	private double longitudine;
}
