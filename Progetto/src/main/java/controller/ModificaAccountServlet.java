package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
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
			System.out.println("ModificaAccount serlvlet: "+nome);
			System.out.println("ModificaAccount serlvlet: "+cognome);
			System.out.println("ModificaAccount serlvlet: "+codiceFiscale);
			System.out.println("ModificaAccount serlvlet: "+dataNascita);
			System.out.println("ModificaAccount serlvlet: "+email);
			System.out.println("ModificaAccount serlvlet: "+password);
			System.out.println("ModificaAccount serlvlet: "+telefono);
			System.out.println("ModificaAccount serlvlet: "+sesso);
			System.out.println("ModificaAccount serlvlet: "+nomeRegione);
			System.out.println("ModificaAccount serlvlet: "+nomeProvincia);
			System.out.println("ModificaAccount serlvlet: "+comune);
			System.out.println("ModificaAccount serlvlet: "+indirizzo);
			Utente utente=(Utente) request.getSession().getAttribute("utente");
			System.out.println("ModificaAccount serlvlet utente: "+utente.getId());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getCodiceFiscale());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getDataNascita());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getEmail());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getPassword());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getTelefono());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getSesso());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getRegione());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getProvincia());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getComune());
			System.out.println("ModificaAccount serlvlet utente: "+utente.getIndirizzo());
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
				}
				catch (ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
				}
				RequestDispatcher dispatcher= request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
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
