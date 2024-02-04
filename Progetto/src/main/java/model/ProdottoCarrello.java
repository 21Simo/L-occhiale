package model;


public class ProdottoCarrello extends Prodotto
{
	public String getGradazione() 
	{
		return gradazione;
	}
	
	public void setGradazione(String gradazione) 
	{
		this.gradazione = gradazione;
	}
	
	public String getNomeFile()
	{
		return nomeFile;
	}
	
	public void setNomeFile(String nomeFile) 
	{
		this.nomeFile = nomeFile;
	}

	public String getPath() 
	{
		return path;
	}

	public void setPath(String path) 
	{
		this.path = path;
	}


	private String gradazione;
	private String nomeFile;
	private String path;
}
