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
		
		//String query= "Select * From prodotto";
		
		String query= "select id, nome from occhiale.prodotto";
		
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
				//prodotto.setDescrizione(risultati.getString("descrizione"));
				//prodotto.setMarca(risultati.getString("marca"));
				//prodotto.setSesso(risultati.getString("sesso"));
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
	
	public synchronized ArrayList<Prodotto> dettagliProdottiPerId(int id) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();
		
		String query= "Select * From occhiale.prodotto where id=?";
		
		try 
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();	
			while(risultati.next())
			{
				Prodotto prodotto= new Prodotto();
				prodotto.setId(risultati.getInt("id"));
				prodotto.setNome(risultati.getString("nome"));
				prodotto.setDescrizione(risultati.getString("descrizione"));
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
	
	/*
	public Collection<Prodotto> prodottiPerUomo() throws SQLException 
	{
		String query= "select * from occhiale.prodotto where sesso='M'";
		
		Connection connessione= DriverManagerConnectionPool.getConnessione();
		
		PreparedStatement preparedStatement= connessione.prepareStatement(query);
		
		System.out.println(preparedStatement);
		
		ResultSet risultati= preparedStatement.executeQuery();
		
		Prodotto prodotto= new Prodotto();
		
		Collection<Prodotto> listaProdotti= new LinkedList<Prodotto>();
		
		while(risultati.next())
		{
			prodotto.setId(risultati.getInt("id"));
			prodotto.setNome(risultati.getString("nome"));
			prodotto.setDescrizione(risultati.getString("descrizione"));
			prodotto.setPrezzo(risultati.getDouble("prezzo"));
			prodotto.setMarca(risultati.getString("marca"));
			prodotto.setSesso(risultati.getString("sesso"));
			prodotto.setImmagine(risultati.getString("immagine"));
			prodotto.setQuantità(risultati.getInt("quantità"));
			prodotto.setCodice(risultati.getString("codice"));
			prodotto.setColore(risultati.getString("colore"));
			listaProdotti.add(prodotto);
		}
		preparedStatement.close();
		DriverManagerConnectionPool.rilasciaConnessione(connessione);
		return listaProdotti;
	}
	*/
}
