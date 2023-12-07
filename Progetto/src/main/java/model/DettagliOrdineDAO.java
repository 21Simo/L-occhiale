package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DettagliOrdineDAO 
{
	public synchronized void inserisciDettagliOrdine(DettagliOrdine dettagliOrdine) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		String query="insert into dettagliOrdine (importo, iva, data, quantità, idUtente, idPagamento, stato, file) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			preparedStatement.setDouble(1, dettagliOrdine.getImporto());
			preparedStatement.setDouble(2, dettagliOrdine.getIva());
			preparedStatement.setDate(3, dettagliOrdine.getData());
			preparedStatement.setInt(4, dettagliOrdine.getQuantità());
			preparedStatement.setInt(5, dettagliOrdine.getIdUtente());
			preparedStatement.setInt(6, dettagliOrdine.getIdPagamento());
			preparedStatement.setString(7, dettagliOrdine.getStato());
			preparedStatement.setString(8, dettagliOrdine.getFile());
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
		
		String query= "select id from occhiale.dettagliOrdine order by id desc limit 1";
		
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
}
