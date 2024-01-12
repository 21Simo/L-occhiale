package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO 
{
	public synchronized void inserisciOrdine(Ordine ordine) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		String query="insert into ordine (idOrdine, idProdotto, idColore, file, quantitàProdotto) values (?, ?, ?, ?, ?)";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, ordine.getIdOrdine());
			preparedStatement.setInt(2, ordine.getIdProdotto());
			preparedStatement.setInt(3, ordine.getIdColore());
			preparedStatement.setString(4, ordine.getFile());
			preparedStatement.setInt(5, ordine.getQuantitàProdotto());
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
	
	public synchronized ArrayList<Ordine> ordinePerId(int idOrdine) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Ordine> listaOrdini= new ArrayList<Ordine>();
		
		String query= "select * from occhiale.ordine where idOrdine= ?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, idOrdine);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				Ordine ordine= new Ordine();
				ordine.setId(risultati.getInt("id"));
				ordine.setIdOrdine(risultati.getInt("idOrdine"));
				ordine.setIdProdotto(risultati.getInt("idProdotto"));
				ordine.setIdColore(risultati.getInt("idColore"));
				ordine.setFile(risultati.getString("file"));
				ordine.setQuantitàProdotto(risultati.getInt("quantitàProdotto"));
				listaOrdini.add(ordine);
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
		return listaOrdini;
	}
}
