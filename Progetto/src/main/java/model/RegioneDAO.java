package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegioneDAO 
{
	public synchronized ArrayList<Regione> regioni() throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Regione> listaRegione= new ArrayList<Regione>();
		
		String query= "select id, nome from italia.regioni";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				Regione regione= new Regione();
				regione.setId(risultati.getInt("id"));
				regione.setNome(risultati.getString("nome"));
				listaRegione.add(regione);
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
		
		return listaRegione;
	}
}