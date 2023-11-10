package model;

public class Regione 
{	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
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
	private String nome;
	private double latitudine;
	private double longitudine;
}
