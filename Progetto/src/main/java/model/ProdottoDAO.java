package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class ProdottoDAO 
{
	public synchronized ArrayList<Prodotto> prodotti() throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();
		
		String query= "select id, nome, marca, sesso from occhiale.prodotto";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();	
			while(risultati.next())
			{
				Prodotto prodotto= new Prodotto();
				prodotto.setId(risultati.getInt("id"));
				prodotto.setNome(risultati.getString("nome"));
				prodotto.setMarca(risultati.getString("marca"));
				prodotto.setSesso(risultati.getString("sesso"));
				listaProdotti.add(prodotto);
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
		
		return listaProdotti;
	}
	
	public synchronized Prodotto dettagliProdottiPerId(int id) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "Select * From occhiale.prodotto where id=?";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();
			Prodotto prodotto= new Prodotto();
			while(risultati.next())
			{
				prodotto.setId(risultati.getInt("id"));
				prodotto.setNome(risultati.getString("nome"));
				prodotto.setDescrizione(risultati.getString("descrizione"));
				prodotto.setMarca(risultati.getString("marca"));
				prodotto.setSesso(risultati.getString("sesso"));
			}
			return prodotto;
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
	
	public synchronized void aggiornaProdotto(Prodotto prodotto) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "update occhiale.prodotto set nome=?, marca=? where id=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setString(1, prodotto.getNome());
			preparedStatement.setString(2, prodotto.getMarca());
			preparedStatement.setInt(3, prodotto.getId());
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
	
	public synchronized void inserisciProdotto(Prodotto prodotto) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "INSERT INTO occhiale.prodotto (nome, descrizione, marca, sesso) VALUES (?, ?, ?, ?)";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setString(1, prodotto.getNome());
			preparedStatement.setString(2, "Occhiali");
			preparedStatement.setString(3, prodotto.getMarca());
			preparedStatement.setString(4, prodotto.getSesso());
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
	
	public synchronized int ultimoId() throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "select id from occhiale.prodotto order by id desc limit 1";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();
			int id=-1;
			while(risultati.next())
			{
				id= risultati.getInt("id");
			}
			return id;
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
	
	public synchronized void eliminaProdotto(Prodotto prodotto) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		String query= "DELETE FROM occhiale.prodotto WHERE id=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, prodotto.getId());
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
	
	public synchronized ArrayList<Prodotto> ultimiProdotti() throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();
		
		String query= "select * from occhiale.prodotto order by id desc limit 4";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();	
			while(risultati.next())
			{
				Prodotto prodotto= new Prodotto();
				prodotto.setId(risultati.getInt("id"));
				prodotto.setNome(risultati.getString("nome"));
				prodotto.setMarca(risultati.getString("marca"));
				prodotto.setSesso(risultati.getString("sesso"));
				listaProdotti.add(prodotto);
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
		
		return listaProdotti;
	}
	
	public synchronized ArrayList<Prodotto> prodottiPerGenere(String genere) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();
		
		String query= "select * from occhiale.prodotto where sesso=?";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setString(1, genere);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();	
			while(risultati.next())
			{
				Prodotto prodotto= new Prodotto();
				prodotto.setId(risultati.getInt("id"));
				prodotto.setNome(risultati.getString("nome"));
				prodotto.setMarca(risultati.getString("marca"));
				prodotto.setSesso(risultati.getString("sesso"));
				listaProdotti.add(prodotto);
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
		
		return listaProdotti;
	}
}
