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
				colore.setPrezzo(risultati.getDouble("prezzo"));
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
}
