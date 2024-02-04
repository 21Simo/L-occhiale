package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO
{
	public synchronized void inserisciUtente(Utente utente) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		String query="insert into utente (nome, cognome, codiceFiscale, dataNascita, email, password, telefono, sesso, tipo, regione, provincia, comune, indirizzo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getCodiceFiscale());
			preparedStatement.setDate(4, utente.getDataNascita());
			preparedStatement.setString(5, utente.getEmail());
			preparedStatement.setString(6, utente.getPassword());
			preparedStatement.setString(7, utente.getTelefono());
			preparedStatement.setString(8, utente.getSesso());
			preparedStatement.setString(9, utente.getTipo());
			preparedStatement.setString(10, utente.getRegione());
			preparedStatement.setString(11, utente.getProvincia());
			preparedStatement.setString(12, utente.getComune());
			preparedStatement.setString(13, utente.getIndirizzo());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
			connessione.commit();
		}
		finally
		{
			try
			{
				if(preparedStatement!=null)
				{
					preparedStatement.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
	}
	
	public synchronized boolean presenzaEmail(String email) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query="select count(1) from occhiale.utente where email=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setString(1, email);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				int conto=Integer.parseInt(risultati.getString(1));
				if(conto==0)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			return false;
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
	}
	
	public synchronized Utente login(String email, String password) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query="select * from occhiale.utente where email=? and password=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet risultati= preparedStatement.executeQuery();
			Utente utente= new Utente();
			utente.setEmail("errore");
			while(risultati.next())
			{
				utente= new Utente();
				utente.setId(risultati.getInt("id"));
				utente.setNome(risultati.getString("nome"));
				utente.setCognome(risultati.getString("cognome"));
				utente.setCodiceFiscale(risultati.getString("codiceFiscale"));
				utente.setDataNascita(risultati.getDate("dataNascita"));
				utente.setEmail(risultati.getString("email"));
				utente.setPassword(risultati.getString("password"));				
				utente.setTelefono(risultati.getString("telefono"));
				utente.setSesso(risultati.getString("sesso"));
				utente.setTipo(risultati.getString("tipo"));
				utente.setRegione(risultati.getString("regione"));
				utente.setProvincia(risultati.getString("provincia"));
				utente.setComune(risultati.getString("comune"));
				utente.setIndirizzo(risultati.getString("indirizzo"));
			}
			return utente;
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
	}
	
	public synchronized String indirizzoPerUtente(int idUtente) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "select indirizzo,comune,provincia,regione from occhiale.utente where id=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, idUtente);
			ResultSet risultati= preparedStatement.executeQuery();
			String indirizzo= null;
			while(risultati.next())
			{
				indirizzo= risultati.getString("indirizzo");
				indirizzo= indirizzo+", "+risultati.getString("comune");
				indirizzo= indirizzo+" ("+risultati.getString("provincia")+")";
				indirizzo= indirizzo+", "+risultati.getString("regione");
			}
			return indirizzo;
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
	}
	
	public synchronized void aggiornaProfilo(Utente utente) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query="update occhiale.utente set nome=?, cognome=?, codiceFiscale=?, dataNascita=?, email=?, password=?, telefono=?, sesso=?, regione=?, provincia=?, comune=?, indirizzo=? where id=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getCodiceFiscale());
			preparedStatement.setDate(4, utente.getDataNascita());
			preparedStatement.setString(5, utente.getEmail());
			preparedStatement.setString(6, utente.getPassword());
			preparedStatement.setString(7, utente.getTelefono());
			preparedStatement.setString(8, utente.getSesso());
			preparedStatement.setString(9, utente.getRegione());
			preparedStatement.setString(10, utente.getProvincia());
			preparedStatement.setString(11, utente.getComune());
			preparedStatement.setString(12, utente.getIndirizzo());
			preparedStatement.setInt(13, utente.getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
			connessione.commit();
		}
		finally
		{
			try
			{
				if(preparedStatement!=null)
				{
					preparedStatement.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
	}
	
	public synchronized ArrayList<Utente> utenti() throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Utente> listaUtenti= new ArrayList<Utente>();
		
		String query= "select id, email from occhiale.utente";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();	
			while(risultati.next())
			{
				Utente utente= new Utente();
				utente.setId(risultati.getInt("id"));
				utente.setEmail(risultati.getString("email"));
				listaUtenti.add(utente);
			}
		}
		finally
		{
			try
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
		
		return listaUtenti;
	}
}