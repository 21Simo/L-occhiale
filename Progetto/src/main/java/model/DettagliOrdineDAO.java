package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DettagliOrdineDAO 
{
	public synchronized void inserisciDettagliOrdine(DettagliOrdine dettagliOrdine) throws ClassNotFoundException, SQLException
	{
		Connection connessione=null;
		
		PreparedStatement preparedStatement=null;
		
		String query="insert into dettagliOrdine (importo, iva, data, quantità, idUtente, idPagamento, stato) values (?, ?, ?, ?, ?, ?, ?)";
		
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
	
	public synchronized ArrayList<DettagliOrdine> dettagliOrdiniPerUtente (int idUtente) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<DettagliOrdine> listaDettagliOrdini= new ArrayList<DettagliOrdine>();
		
		String query= "select * from occhiale.dettagliordine where idUtente=? order by data desc, id desc limit 2";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setInt(1, idUtente);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				DettagliOrdine dettagliOrdine= new DettagliOrdine();
				dettagliOrdine.setId(risultati.getInt("id"));
				dettagliOrdine.setImporto(risultati.getDouble("importo"));
				dettagliOrdine.setIva(risultati.getDouble("iva"));
				dettagliOrdine.setData(risultati.getDate("data"));
				dettagliOrdine.setQuantità(risultati.getInt("quantità"));
				dettagliOrdine.setIdUtente(risultati.getInt("idUtente"));
				dettagliOrdine.setIdPagamento(risultati.getInt("idPagamento"));
				dettagliOrdine.setStato(risultati.getString("stato"));
				listaDettagliOrdini.add(dettagliOrdine);
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
		return listaDettagliOrdini;
	}
	
	public synchronized ArrayList<DettagliOrdine> ordiniPerUtente (int idUtente) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<DettagliOrdine> listaOrdini= new ArrayList<DettagliOrdine>();
		
		String query= "select * from occhiale.dettagliordine where idUtente=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setInt(1, idUtente);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				DettagliOrdine dettagliOrdine= new DettagliOrdine();
				dettagliOrdine.setId(risultati.getInt("id"));
				dettagliOrdine.setImporto(risultati.getDouble("importo"));
				dettagliOrdine.setIva(risultati.getDouble("iva"));
				dettagliOrdine.setData(risultati.getDate("data"));
				dettagliOrdine.setQuantità(risultati.getInt("quantità"));
				dettagliOrdine.setIdUtente(risultati.getInt("idUtente"));
				dettagliOrdine.setIdPagamento(risultati.getInt("idPagamento"));
				dettagliOrdine.setStato(risultati.getString("stato"));
				listaOrdini.add(dettagliOrdine);
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
	
	public synchronized ArrayList<DettagliOrdine> ordiniTraDueDate (Date data1, Date data2) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<DettagliOrdine> listaOrdini= new ArrayList<DettagliOrdine>();
		
		String query= "select * from occhiale.dettagliordine where data between ? and ?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setDate(1, data1);
			preparedStatement.setDate(2, data2);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				DettagliOrdine dettagliOrdine= new DettagliOrdine();
				dettagliOrdine.setId(risultati.getInt("id"));
				dettagliOrdine.setImporto(risultati.getDouble("importo"));
				dettagliOrdine.setIva(risultati.getDouble("iva"));
				dettagliOrdine.setData(risultati.getDate("data"));
				dettagliOrdine.setQuantità(risultati.getInt("quantità"));
				dettagliOrdine.setIdUtente(risultati.getInt("idUtente"));
				dettagliOrdine.setIdPagamento(risultati.getInt("idPagamento"));
				dettagliOrdine.setStato(risultati.getString("stato"));
				listaOrdini.add(dettagliOrdine);
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
