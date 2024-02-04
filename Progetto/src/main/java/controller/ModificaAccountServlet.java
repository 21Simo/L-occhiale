package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

/**
 * Servlet che serve per la modifica dell'account. 
 */
@WebServlet("/ModificaAccountServlet")
public class ModificaAccountServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(ModificaAccountServlet.class.getName());
	
	private UtenteDAO utenteDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaAccountServlet() 
    {
        super();
        this.utenteDAO= new UtenteDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Utente utente=(Utente) request.getSession().getAttribute("utente");
		if(utente==null)
		{
			response.sendRedirect("Login.jsp");
		}
		else
		{		
			String nome= request.getParameter("nome");
			String cognome= request.getParameter("cognome");
			String codiceFiscale= request.getParameter("codiceFiscale");
			String dataNascitaString= request.getParameter("dataNascita");
			SimpleDateFormat converti= new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				java.util.Date data= converti.parse(dataNascitaString);
				Date dataNascita= new Date(data.getTime());
				String email= request.getParameter("email");
				String password= request.getParameter("password");
				String telefono= request.getParameter("telefono");
				String sesso= request.getParameter("sesso");
				String regione= request.getParameter("regione");
				int indiceRegione=regione.indexOf("/");
				String nomeRegione=regione.substring(indiceRegione+1);
				String provincia= request.getParameter("province");
				int indiceProvincia=provincia.indexOf("/");
				String nomeProvincia=provincia.substring(indiceProvincia+1);
				String comune= request.getParameter("comune");
				String indirizzo= request.getParameter("indirizzo");
				if(!utente.getNome().equals(nome) || !utente.getCognome().equals(cognome) || !utente.getCodiceFiscale().equals(codiceFiscale) || !utente.getDataNascita().equals(dataNascita) || !utente.getEmail().equals(email) || !utente.getPassword().equals(password) || !utente.getTelefono().equals(telefono) || !utente.getSesso().equals(sesso) || !utente.getRegione().equals(nomeRegione) || !utente.getProvincia().equals(nomeProvincia) || !utente.getComune().equals(comune) || !utente.getIndirizzo().equals(indirizzo))
				{
					System.out.println("Prova");
					Utente utenteAggiornato= new Utente();
					utenteAggiornato.setId(utente.getId());
					utenteAggiornato.setNome(nome);
					utenteAggiornato.setCognome(cognome);
					utenteAggiornato.setCodiceFiscale(codiceFiscale);
					utenteAggiornato.setDataNascita(dataNascita);
					utenteAggiornato.setEmail(email);
					utenteAggiornato.setPassword(password);
					utenteAggiornato.setTelefono(telefono);
					utenteAggiornato.setSesso(sesso);
					utenteAggiornato.setRegione(nomeRegione);
					utenteAggiornato.setProvincia(nomeProvincia);
					utenteAggiornato.setComune(comune);
					utenteAggiornato.setIndirizzo(indirizzo);
					utenteAggiornato.setTipo("Utente");
					try
					{
						utenteDAO.aggiornaProfilo(utenteAggiornato);
						request.getSession().setAttribute("utente", utenteAggiornato);
						response.sendRedirect("Login.jsp");
					}
					catch (ClassNotFoundException | SQLException e)
					{
						logger.log(Level.INFO, "Exception", e);
					}
				}
			}
			catch (ParseException e)
			{
				logger.log(Level.INFO, "Exception", e);
			}
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
