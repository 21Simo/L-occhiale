package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

/**
 * Servlet che serve per la registrazione dell'utente. 
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(RegistrazioneServlet.class.getName());
	
	private UtenteDAO utenteDAO;
       
	
    public RegistrazioneServlet() 
    {
        super();
        this.utenteDAO= new UtenteDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String codiceFIscale=request.getParameter("codiceFiscale");
		String dataString=request.getParameter("dataNascita");
		SimpleDateFormat converti= new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			java.util.Date data= converti.parse(dataString);
			Date dataNascita= new Date(data.getTime());
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String telefono=request.getParameter("telefono");
			String sesso=request.getParameter("sesso");
			String regione=request.getParameter("regione");
			int indiceRegione=regione.indexOf("/");
			String nomeRegione=regione.substring(indiceRegione+1);
			String provincia=request.getParameter("province");
			int indiceProvincia=provincia.indexOf("/");
			String nomeProvincia=provincia.substring(indiceProvincia+1);
			String comune=request.getParameter("comune");
			String indirizzo=request.getParameter("indirizzo");
			Utente utente=new Utente();
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setCodiceFiscale(codiceFIscale);
			utente.setDataNascita(dataNascita);
			utente.setEmail(email);
			utente.setPassword(password);
			utente.setTelefono(telefono);
			utente.setSesso(sesso);
			utente.setRegione(nomeRegione);
			utente.setProvincia(nomeProvincia);
			utente.setComune(comune);
			utente.setIndirizzo(indirizzo);
			utente.setTipo("Utente");
			try 
			{
				utenteDAO.inserisciUtente(utente);
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				logger.log(Level.INFO, "Exception", e);
			}
			RequestDispatcher dispatcher= request.getRequestDispatcher("Index.jsp");
			dispatcher.forward(request, response);
		} 
		catch (ParseException e) 
		{
			logger.log(Level.INFO, "Exception", e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}