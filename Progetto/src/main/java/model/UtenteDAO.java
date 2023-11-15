package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			System.out.println(preparedStatement);
			//preparedStatement.setInt(1, utente.getId());
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
	
	public synchronized String login(String email, String password) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query="select email, password from occhiale.utente where email=? and password=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet risultati= preparedStatement.executeQuery();
			System.out.println("C'è risultato: "+risultati);
			String login="errore";
			while(risultati.next())
			{
				System.out.println("Risultato: "+risultati.getString("email"));
				login=risultati.getString("email");
			}
			System.out.println("Login: "+login);
			return login;
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
}