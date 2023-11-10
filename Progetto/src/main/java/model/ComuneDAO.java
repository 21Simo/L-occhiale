package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComuneDAO 
{
	public synchronized ArrayList<Comune> comuni(int provincia) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Comune> listaComuni= new ArrayList<Comune>();
		
		String query="select nome from italia.comuni where id_provincia=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setInt(1, provincia);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				Comune comuni=new Comune();
				//province.setId(risultati.getInt("id"));
				comuni.setNome(risultati.getString("nome"));
				listaComuni.add(comuni);
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
		
		return listaComuni;
	}
}
