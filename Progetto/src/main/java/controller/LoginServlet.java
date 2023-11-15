package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carello;
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
		System.out.println(email);
		String password= request.getParameter("password");
		System.out.println(password);
		String login= null;
		try
		{
			login=utenteDAO.login(email, password);
			System.out.println("Servlet: "+login);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Servlet: "+login);
		if(login.equals("errore"))
		{
			request.setAttribute("login", login);
			Utente utente= null;
			request.getSession().setAttribute("utente", utente);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			Utente utente= new Utente();
			utente.setEmail(login);
			request.getSession().setAttribute("utente", utente);
			Carello carello=(Carello) request.getSession().getAttribute("carello");
			if(carello!=null)
			{
				Carello carelloUtente= carello;
				System.out.println("Carello utente: "+carelloUtente);
				request.getSession().setAttribute("carelloUtente", carelloUtente);
				carello= null;
				System.out.println("Carello fuori login: "+carello);
			}
			System.out.println("Carello fuori if: "+carello);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Index.jsp");
			dispatcher.forward(request, response);
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