package model;

public class Province 
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
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getSigla() 
	{
		return sigla;
	}
	
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
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
	private String nome;
	private String sigla;
	private double latitudine;
	private double longitudine;
}
