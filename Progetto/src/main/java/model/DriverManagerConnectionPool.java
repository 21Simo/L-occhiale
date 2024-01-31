package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ComuniServlet;

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
	
	static Logger logger= Logger.getLogger(DriverManagerConnectionPool.class.getName());
	
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
		
		//Leggiamo il file delle properties e ci prendiamo l'username e la password. 
		try(InputStream input= new FileInputStream("D:\\Università\\2 anno\\2 semestre\\TSW\\RepoGithub\\Progetto\\src\\main\\webapp\\WEB-INF\\database.properties"))
		{
			Properties properties= new Properties(); 
			properties.load(input);
			String username= properties.getProperty("db.user");
			String password= properties.getProperty("db.password");
		
			//Prende la connessione al database e restituisce un oggetto di tipo connessione.
			nuovaConnessione= DriverManager.getConnection("jdbc:mysql://"+ ip + ":" + porta + "/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			nuovaConnessione.setAutoCommit(false);
		}
		catch (IOException e) 
		{
			logger.log(Level.INFO, "Exception", e);
		}
		
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