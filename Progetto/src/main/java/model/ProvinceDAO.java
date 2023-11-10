package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceDAO 
{
	public synchronized ArrayList<Province> province(int regione) throws ClassNotFoundException, SQLException
	{
		Connection connessione= null;
		
		PreparedStatement preparedStatement= null;
		
		ArrayList<Province> listaProvince= new ArrayList<Province>();
		
		String query="select id, nome from italia.province where id_regione=?";
		
		try
		{
			connessione= DriverManagerConnectionPool.getConnessione();
			preparedStatement= connessione.prepareStatement(query);
			preparedStatement.setInt(1, regione);
			System.out.println(preparedStatement);
			ResultSet risultati= preparedStatement.executeQuery();
			while(risultati.next())
			{
				Province province=new Province();
				province.setId(risultati.getInt("id"));
				province.setNome(risultati.getString("nome"));
				listaProvince.add(province);
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
		
		return listaProvince;
	}
}
