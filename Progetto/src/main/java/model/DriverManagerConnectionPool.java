package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DriverManagerConnectionPool
 * classe per gestire le connessioni al database.
 * @author simon
 *
 */

public class DriverManagerConnectionPool 
{
	//Creazione della connessione libera al database.
	private static List<Connection> connessioneDBLibero;
	
	static
	{
		//Lista delle connessioni.
		connessioneDBLibero=new LinkedList<Connection>();
		try
		{
			//Crea l'istanza del driver.
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			//Errore driver non trovato.
			System.out.println("Driver del DB non trovato:"+ e.getMessage());
		}
	}
	
	/**
	 * Metodo per creare la connessione al database.
	 * @return la connessione.
	 * @throws SQLException l'eccezione SQLException.
	 */
	
	private static synchronized Connection creaConnessioneDB() throws SQLException
	{
		//Crea l'oggetto connessione.
		Connection nuovaConnessione= null;
		//Parametri di configurazione.
		String ip= "localhost";
		String porta= "3306";
		String db= "occhiale";
		String username= "root";
		String password= "root";
		
		//Prende la connessione al database e restituisce un oggetto di tipo connessione.
		nuovaConnessione= DriverManager.getConnection("jdbc:mysql://"+ ip + ":" + porta + "/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
		
		nuovaConnessione.setAutoCommit(false);
		return nuovaConnessione;
	}
	
	/**
	 * Metodo per ottenere la connessione al database.
	 * @return la connessione al database.
	 * @throws SQLException l'eccezione SQLException.
	 */
	
	public static synchronized Connection getConnessione() throws SQLException
	{
		//Utilizza le connessioni esistenti, se non ci sono ne crea altre. 
		Connection connessione;
		
		//Vede se ci sono connessioni. 
		if(!connessioneDBLibero.isEmpty())
		{
			//Ottiene la connessione dalla lista della connessioni libere. 
			connessione= (Connection) connessioneDBLibero.get(0);
			//Rimuove la connessione dalla lista. 
			connessioneDBLibero.remove(0);
			
			try
			{
				//Vede se la connessione è chiusa.
				if(connessione.isClosed())
				{
					//Ottiene la connessione.
					connessione= getConnessione();
				}
			}
			catch (SQLException e)
			{
				//Chiude la connessione.
				connessione.close();
				//Si ottiene una nuova connessione. 
				connessione= getConnessione();
			}
		}
		//Se non ci sono connessioni.
		else
		{
			//Crea una nuova connessione. 
			connessione= creaConnessioneDB();
		}
		
		return connessione;
	}
	
	/**
	 * Metodo per rilasciare la connessione.
	 * @param connessione Una connessione al database.
	 * @throws SQLException l'eccezione SQLException.
	 */
	
	public static synchronized void rilasciaConnessione(Connection connessione) throws SQLException
	{
		//Vede se ci sono connessioni.
		if(connessione!=null)
		{
			//Le aggiunge alla lista di connessioni libere. 
			connessioneDBLibero.add(connessione);
		}
	}
}