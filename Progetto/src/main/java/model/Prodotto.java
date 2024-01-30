package model;

public class Prodotto 
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
	
	public String getDescrizione() 
	{
		return descrizione;
	}
	
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	
	public String getMarca() 
	{
		return marca;
	}
	
	public void setMarca(String marca)
	{
		this.marca = marca;
	}
	
	public String getSesso()
	{
		return sesso;
	}
	
	public void setSesso(String sesso) 
	{
		this.sesso = sesso;
	}
	
	public Colore getColore() 
	{
		return colore;
	}

	public void setColore(Colore colore) 
	{
		this.colore = colore;
	}
	


	//Variabili di istanza.
	//Variabile che indica l'id del prodotto.
	private int id;
	//Variabile che indica il nome del prodotto.
	private String nome;
	//Variabile che indica la descrizione del prodotto.
	private String descrizione;
	//Variabile che indica la marca del prodotto.
	private String marca;
	//Variabile che indica il sesso del prodotto.
	private String sesso;
	//Variabile che rappresenta l'oggetto Colore del prodotto con relativo prezzo, immagine, ecc.
	private Colore colore;
}
