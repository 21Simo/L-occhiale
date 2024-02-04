package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet che serve per ottenere gli ordini per l'amministratore. 
 */
@WebServlet("/OrdiniAdminServlet")
public class OrdiniAdminServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(OrdiniAdminServlet.class.getName());
	
	private DettagliOrdineDAO dettagliOrdineDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdiniAdminServlet() 
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
			String opzione= request.getParameter("opzione");
			if(opzione.equals("Data"))
			{
				String data1String= request.getParameter("data1");
				String data2String= request.getParameter("data2");
				SimpleDateFormat converti= new SimpleDateFormat("yyyy-MM-dd");
				try 
				{
					java.util.Date data1= converti.parse(data1String);
					Date data1Date= new Date(data1.getTime());
					java.util.Date data2= converti.parse(data2String);
					Date data2Date= new Date(data2.getTime());
					try 
					{
						ArrayList<DettagliOrdine> listaOrdini= dettagliOrdineDAO.ordiniTraDueDate(data1Date, data2Date);
						JSONObject ordiniJson= new JSONObject();
						ordiniJson.put("ordini", listaOrdini);
						request.setAttribute("ordiniJson", ordiniJson);
						RequestDispatcher dispatcher= request.getRequestDispatcher("VisualizzazioneOrdiniAdmin.jsp");
						dispatcher.forward(request, response);
					} 
					catch (ClassNotFoundException | SQLException e) 
					{
						logger.log(Level.INFO, "Exception", e);
					}				
				} 
				catch (ParseException e) 
				{
					logger.log(Level.INFO, "Exception", e);
				}
			}
			else if(opzione.equals("Utente"))
			{
				int utenteId= Integer.parseInt(request.getParameter("utenteOption"));
				try 
				{
					ArrayList<DettagliOrdine> listaOrdini= dettagliOrdineDAO.ordiniPerUtente(utenteId);
					JSONObject ordiniJson= new JSONObject();
					ordiniJson.put("ordini", listaOrdini);
					request.setAttribute("ordiniJson", ordiniJson);
					RequestDispatcher dispatcher= request.getRequestDispatcher("VisualizzazioneOrdiniAdmin.jsp");
					dispatcher.forward(request, response);
				} 
				catch (ClassNotFoundException | SQLException e) 
				{
					logger.log(Level.INFO, "Exception", e);
				}
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
