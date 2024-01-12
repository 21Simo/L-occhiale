package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carello;
import model.DettagliOrdine;
import model.DettagliOrdineDAO;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
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
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		if(utente.getEmail().equals("errore"))
		{
			request.setAttribute("login", utente.getEmail());
			request.getSession().setAttribute("utente", utente);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("utente", utente);
			Carello carello=(Carello) request.getSession().getAttribute("carello");
			if(carello!=null)
			{
				Carello carelloUtente= carello;
				request.getSession().setAttribute("carelloUtente", carelloUtente);
				carello= null;
			}
			
			String tipo= utente.getTipo();
			if(tipo.equals("Utente"))
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("/Dashboard.jsp"); 
				dispatcher.forward(request, response);
			}
			else if (tipo.equals("Amministratore")) 
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("/DashboardAdmin.jsp");
				dispatcher.forward(request, response);
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