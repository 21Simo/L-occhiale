package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.DettagliOrdine;
import model.DettagliOrdineDAO;
import model.Utente;

/**
 * Servlet che serve per ottenere gli ordini. 
 */
@WebServlet("/OrdiniServlet")
public class OrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(OrdiniServlet.class.getName());
	
	private DettagliOrdineDAO dettagliOrdineDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdiniServlet() 
    {
        super();
        this.dettagliOrdineDAO= new DettagliOrdineDAO();
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
			try 
			{
				ArrayList<DettagliOrdine> listaOrdini= dettagliOrdineDAO.ordiniPerUtente(utente.getId());
				JSONObject ordini= new JSONObject();
				ordini.put("listaOrdini", listaOrdini);
				request.setAttribute("ordini", ordini);
				RequestDispatcher dispatcher= request.getRequestDispatcher("Ordini.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ClassNotFoundException | SQLException e) 
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
