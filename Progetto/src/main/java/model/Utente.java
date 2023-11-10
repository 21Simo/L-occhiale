package model;

import java.sql.Date;

public class Utente 
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
	
	public String getCognome() 
	{
		return cognome;
	}
	
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	
	public String getCodiceFiscale() 
	{
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale) 
	{
		this.codiceFiscale = codiceFiscale;
	}
	
	public Date getDataNascita() 
	{
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) 
	{
		this.dataNascita = dataNascita;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getTelefono() 
	{
		return telefono;
	}
	
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	
	public String getSesso()
	{
		return sesso;
	}
	
	public void setSesso(String sesso)
	{
		this.sesso = sesso;
	}
	
	public String getTipo() 
	{
		return tipo;
	}
	
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	
	public String getRegione() 
	{
		return regione;
	}

	public void setRegione(String regione) 
	{
		this.regione = regione;
	}

	public String getProvincia() 
	{
		return provincia;
	}

	public void setProvincia(String provincia) 
	{
		this.provincia = provincia;
	}

	public String getComune() 
	{
		return comune;
	}

	public void setComune(String comune) 
	{
		this.comune = comune;
	}

	public String getIndirizzo() 
	{
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) 
	{
		this.indirizzo = indirizzo;
	}
	
	
	private int id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataNascita;
	private String email;
	private String password;
	private String telefono;
	private String sesso;
	private String tipo;
	private String regione;
	private String provincia;
	private String comune;
	private String indirizzo;
}
