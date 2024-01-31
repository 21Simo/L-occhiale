package controller;

import java.io.IOException;
import java.sql.Date;
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

import model.ColoreDAO;
import model.DettagliOrdine;
import model.DettagliOrdineDAO;
import model.Utente;

/**
 * Servlet che serve per la Dashboard dell'utente.
 */
@WebServlet("/DashboardUtenteServlet")
public class DashboardUtenteServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(DashboardUtenteServlet.class.getName());
	
	private DettagliOrdineDAO dettagliOrdineDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardUtenteServlet() 
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
		try 
		{
			if(utente==null)
			{
				response.sendRedirect("Login.jsp");
			}
			else
			{
			ArrayList<DettagliOrdine> listaDettagliOrdini= dettagliOrdineDAO.dettagliOrdiniPerUtente(utente.getId());
			JSONObject dettagliOrdiniJson= new JSONObject();
			dettagliOrdiniJson.put("listaDettagliOrdini", listaDettagliOrdini);
			request.setAttribute("dettagliOrdini", dettagliOrdiniJson);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Dashboard.jsp");
			dispatcher.forward(request, response);
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
