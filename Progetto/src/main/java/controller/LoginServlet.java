package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet che serve per la login. 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(LoginServlet.class.getName());
	
	private UtenteDAO utenteDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() 
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
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		Utente utente= new Utente();
		utente.setEmail("errore");
		try
		{
			utente=utenteDAO.login(email, password);
			if(utente.getEmail().equals("errore"))
			{
				request.setAttribute("login", utente.getEmail());
				utente.setTipo("errore");
				request.getSession().setAttribute("utente", utente);
				RequestDispatcher dispatcher= request.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				request.getSession().setAttribute("utente", utente);
				Carrello carrello=(Carrello) request.getSession().getAttribute("carrello");
				if(carrello!=null)
				{
					Carrello carrelloUtente= carrello;
					request.getSession().setAttribute("carrelloUtente", carrelloUtente);
					carrello= null;
				}
				
				String tipo= utente.getTipo();
				if(tipo.equals("Utente"))
				{
					response.sendRedirect("DashboardUtenteServlet");
				}
				else if (tipo.equals("Amministratore")) 
				{
					response.sendRedirect("DashboardAdminServlet");
				}
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.log(Level.INFO, "Exception", e);
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