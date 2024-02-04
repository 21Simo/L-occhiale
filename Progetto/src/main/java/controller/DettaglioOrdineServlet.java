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
import model.Ordine;
import model.OrdineDAO;
import model.Prodotto;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet che serve per ottenere i dettagli dell'ordine. 
 */
@WebServlet("/DettaglioOrdineServlet")
public class DettaglioOrdineServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(DettaglioOrdineServlet.class.getName());
	
	private UtenteDAO utenteDAO;
	
	private OrdineDAO ordineDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioOrdineServlet() 
    {
        super();
        this.utenteDAO= new UtenteDAO();
        this.ordineDAO= new OrdineDAO();
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
		
			ArrayList<DettagliOrdine> listaDettagliOrdini=(ArrayList<DettagliOrdine>) request.getSession().getAttribute("listaDettagliOrdini");
			
			int indiceDettaglioProdotto= Integer.parseInt(request.getParameter("dettaglioOrdine"));
			
			try 
			{
				String indirizzo= utenteDAO.indirizzoPerUtente(listaDettagliOrdini.get(indiceDettaglioProdotto).getIdUtente());
				ArrayList<Ordine> listaOrdini= ordineDAO.ordinePerId(listaDettagliOrdini.get(indiceDettaglioProdotto).getId());
				ArrayList<Prodotto> prodotti= new ArrayList<Prodotto>();
				JSONObject dettaglioOrdineJson= new JSONObject();
				dettaglioOrdineJson.put("dettagliOrdine", listaDettagliOrdini.get(indiceDettaglioProdotto));
				dettaglioOrdineJson.put("indirizzo", indirizzo);
				dettaglioOrdineJson.put("prodotti", prodotti);
				dettaglioOrdineJson.put("ordine", listaOrdini);
				request.setAttribute("dettaglioOrdineJson", dettaglioOrdineJson);
				RequestDispatcher dispatcher= request.getRequestDispatcher("/DettaglioOrdine.jsp");
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
