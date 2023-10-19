package model;

public class ProdottoCarello extends Prodotto
{
	public String getGradazione() 
	{
		return gradazione;
	}
	
	public void setGradazione(String gradazione) 
	{
		this.gradazione = gradazione;
	}
	
	public String getFile()
	{
		return file;
	}
	
	public void setFile(String file) 
	{
		this.file = file;
	}
	
	
	private String gradazione;
	private String file;
}
