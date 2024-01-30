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
		
		String query="insert into ordine (idOrdine, file, quantitàProdotto, prezzo, immagineProdotto, nomeProdotto, coloreProdotto) values (?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, ordine.getIdOrdine());
			preparedStatement.setString(2, ordine.getFile());
			preparedStatement.setInt(3, ordine.getQuantitàProdotto());
			preparedStatement.setString(4, ordine.getPrezzo());
			preparedStatement.setString(5, ordine.getImmagineProdotto());
			preparedStatement.setString(6, ordine.getNomeProdotto());
			preparedStatement.setString(7, ordine.getColoreProdotto());
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
				ordine.setFile(risultati.getString("file"));
				ordine.setQuantitàProdotto(risultati.getInt("quantitàProdotto"));
				ordine.setPrezzo(risultati.getString("prezzo"));
				ordine.setImmagineProdotto(risultati.getString("immagineProdotto"));
				ordine.setNomeProdotto(risultati.getString("nomeProdotto"));
				ordine.setColoreProdotto(risultati.getString("coloreProdotto"));
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
