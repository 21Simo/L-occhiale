package model;

public class Pagamento 
{
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getNomeCliente() 
	{
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) 
	{
		this.nomeCliente = nomeCliente;
	}
	
	public String getNumeroCarta() 
	{
		return numeroCarta;
	}
	
	public void setNumeroCarta(String numeroCarta) 
	{
		this.numeroCarta = numeroCarta;
	}
	
	public String getScadenza() 
	{
		return scadenza;
	}
	
	public void setScadenza(String scadenza) 
	{
		this.scadenza = scadenza;
	}
	
	public String getCvv() 
	{
		return cvv;
	}
	
	public void setCvv(String cvv) 
	{
		this.cvv = cvv;
	}
	
	
	private int id;
	private String nomeCliente;
	private String numeroCarta;
	private String scadenza;
	private String cvv;
}
