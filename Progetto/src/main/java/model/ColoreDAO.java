package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ColoreDAO 
{
	public synchronized ArrayList<Colore> colorePerId(int id) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		ArrayList<Colore> listaColore=new ArrayList<Colore>();
		
		String query="Select id, idProdotto, colore, immagine, prezzo from occhiale.colore where idProdotto=?";
		
		try
		{
			connessione=DriverManagerConnectionPool.getConnessione();
			preparedStatement=connessione.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet risultati=preparedStatement.executeQuery();
			while(risultati.next())
			{
				Colore colore= new Colore();
				colore.setId(risultati.getInt("id"));
				colore.setIdProdotto(risultati.getInt("idProdotto"));
				colore.setColore(risultati.getString("colore"));
				colore.setImmagine(risultati.getString("immagine"));
				String prezzo=prezzo(risultati.getString("prezzo"));
				colore.setPrezzo(prezzo);
				listaColore.add(colore); 
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
		
		return listaColore;		
	}
	
	public synchronized ArrayList<Colore> dettaglioColorePerId(int id) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		ArrayList<Colore> listaColore=new ArrayList<Colore>();
		
		String query="Select * from occhiale.colore where idProdotto=?";
		
		try
		{
			connessione=DriverManagerConnectionPool.getConnessione();
			preparedStatement=connessione.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet risultati=preparedStatement.executeQuery();
			while(risultati.next())
			{
				Colore colore= new Colore();
				colore.setId(risultati.getInt("id"));
				colore.setIdProdotto(risultati.getInt("idProdotto"));
				colore.setColore(risultati.getString("colore"));
				colore.setImmagine(risultati.getString("immagine"));
				String prezzo=prezzo(risultati.getString("prezzo"));
				colore.setPrezzo(prezzo);
				colore.setQuantità(risultati.getInt("quantità"));
				colore.setCodiceProdotto(risultati.getString("codiceProdotto"));
				listaColore.add(colore);
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
		
		return listaColore;		
	}
	
	public String prezzo(String prezzo)
	{
		if(prezzo.contains(".")==true)
		{
			int punto=prezzo.indexOf(".");
			String prezzoIntero=prezzo.substring(0, punto);
			String prezzoDopoPunto=prezzo.substring(punto+1);
			String prezzoModificato="";
			if(prezzoDopoPunto.equals("0"))
			{
				prezzoModificato=prezzoIntero;
			}
			else
			{
				prezzoModificato=prezzo;
			}
			return prezzoModificato;
		}
		else
		{
			return prezzo;
		}
	}
	
	public synchronized void aggiornaQuantità(int idProdotto, int idColore, int quantità) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		PreparedStatement preparedStatementQuantità=null;
		
		String queryQuantità="select quantità from occhiale.colore where id=?";
		
		String query="update occhiale.colore set quantità=? where id=? and idProdotto=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			
			preparedStatementQuantità= connessione.prepareStatement(queryQuantità);
			preparedStatementQuantità.setInt(1, idColore);
			ResultSet risultati=preparedStatementQuantità.executeQuery();
			int quantitàMagazzino=-1;
			while(risultati.next())
			{
				quantitàMagazzino=risultati.getInt("quantità");
			}
			
			System.out.println("ColoreDAO: "+quantitàMagazzino);
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			int quantitàAggiornata=quantitàMagazzino-quantità;
			preparedStatement.setInt(1, quantitàAggiornata);
			preparedStatement.setInt(2, idColore);
			preparedStatement.setInt(3, idProdotto);
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
					preparedStatementQuantità.close();
				}
			}
			finally
			{
				DriverManagerConnectionPool.rilasciaConnessione(connessione);
			}
		}
	}
	
	public synchronized Colore colorePerIdColore(int idColore) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "select * from occhiale.colore where id= ?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, idColore);
			ResultSet risultati= preparedStatement.executeQuery();
			Colore colore= new Colore();
			while(risultati.next())
			{
				colore.setId(risultati.getInt("id"));
				colore.setIdProdotto(risultati.getInt("idProdotto"));
				colore.setColore(risultati.getString("colore"));
				colore.setImmagine(risultati.getString("immagine"));
				colore.setPrezzo(risultati.getString("prezzo"));
				colore.setQuantità(risultati.getInt("quantità"));
				colore.setCodiceProdotto(risultati.getString("codiceProdotto"));
			}
			return colore;
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
	
	public synchronized void aggiornaColore(Colore colore) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "update occhiale.colore set colore=?, immagine=?, prezzo=?, quantità=?, codiceProdotto=? where id=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			//preparedStatement.setInt(1, colore.getIdProdotto());
			preparedStatement.setString(1, colore.getColore());
			preparedStatement.setString(2, colore.getImmagine());
			preparedStatement.setString(3, colore.getPrezzo());
			preparedStatement.setInt(4, colore.getQuantità());
			preparedStatement.setString(5, colore.getCodiceProdotto());
			preparedStatement.setInt(6, colore.getId());
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
	
	public synchronized void inserisciColore(Colore colore) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "INSERT INTO occhiale.colore (idProdotto, colore, immagine, prezzo, quantità, codiceProdotto) VALUES (?, ?, ?, ?, ?, ?)";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, colore.getIdProdotto());
			preparedStatement.setString(2, colore.getColore());
			preparedStatement.setString(3, colore.getImmagine());
			preparedStatement.setString(4, colore.getPrezzo());
			preparedStatement.setInt(5, colore.getQuantità());
			preparedStatement.setString(6, colore.getCodiceProdotto());
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
	
	public synchronized void eliminaColore(Colore colore) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "DELETE FROM occhiale.colore WHERE id=? and idProdotto=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, colore.getId());
			preparedStatement.setInt(2, colore.getIdProdotto());
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
}
