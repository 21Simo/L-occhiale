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

import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet che serve per la Dashboard dell'amministratore. 
 */
@WebServlet("/DashboardAdminServlet")
public class DashboardAdminServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(DashboardAdminServlet.class.getName());
	
	private ProdottoDAO prodottoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardAdminServlet()
    {
        super();
        this.prodottoDAO= new ProdottoDAO();
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
				ArrayList<Prodotto> listaUltimiProdotti= prodottoDAO.ultimiProdotti();
				JSONObject ultimiProdottiJson= new JSONObject();
				ultimiProdottiJson.put("listaUltimiProdotti", listaUltimiProdotti);
				request.setAttribute("ultimiProdotti", ultimiProdottiJson);
				RequestDispatcher dispatcher= request.getRequestDispatcher("/DashboardAdmin.jsp");
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
