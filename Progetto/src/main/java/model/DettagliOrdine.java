package model;

import java.sql.Date;

public class DettagliOrdine 
{	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public double getImporto() 
	{
		return importo;
	}
	
	public void setImporto(double importo) 
	{
		this.importo = importo;
	}
	
	public double getIva() 
	{
		return iva;
	}
	
	public void setIva(double iva) 
	{
		this.iva = iva;
	}
	
	public Date getData() 
	{
		return data;
	}
	
	public void setData(Date data) 
	{
		this.data = data;
	}
	
	public int getQuantità() 
	{
		return quantità;
	}
	
	public void setQuantità(int quantità) 
	{
		this.quantità = quantità;
	}
	
	public int getIdUtente() 
	{
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) 
	{
		this.idUtente = idUtente;
	}
	
	public int getIdPagamento() 
	{
		return idPagamento;
	}
	
	public void setIdPagamento(int idPagamento) 
	{
		this.idPagamento = idPagamento;
	}
	
	public String getStato() 
	{
		return stato;
	}
	
	public void setStato(String stato) 
	{
		this.stato = stato;
	}
	
	public String getFile() 
	{
		return file;
	}

	public void setFile(String file) 
	{
		this.file = file;
	}
	

	private int id;
	private double importo;
	private double iva;
	private Date data;
	private int quantità;
	private int idUtente;
	private int idPagamento;
	private String stato;
	private String file;
}
