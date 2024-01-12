package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.DettagliOrdine;
import model.DettagliOrdineDAO;
import model.UtenteDAO;

/**
 * Servlet implementation class OrdiniAdminServlet
 */
@WebServlet("/OrdiniAdminServlet")
public class OrdiniAdminServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
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
		
		String opzione= request.getParameter("opzione");
		System.out.println(opzione);
		if(opzione.equals("Data"))
		{
			String data1String= request.getParameter("data1");
			System.out.println(data1String);
			String data2String= request.getParameter("data2");
			System.out.println(data2String);
			SimpleDateFormat converti= new SimpleDateFormat("yyyy-MM-dd");
			try 
			{
				java.util.Date data1= converti.parse(data1String);
				Date data1Date= new Date(data1.getTime());
				System.out.println(data1Date);
				java.util.Date data2= converti.parse(data2String);
				Date data2Date= new Date(data2.getTime());
				System.out.println(data2Date);
				try 
				{
					ArrayList<DettagliOrdine> listaOrdini= dettagliOrdineDAO.ordiniTraDueDate(data1Date, data2Date);
					System.out.println(listaOrdini);
					JSONObject ordiniJson= new JSONObject();
					ordiniJson.put("ordini", listaOrdini);
					request.setAttribute("ordiniJson", ordiniJson);
					RequestDispatcher dispatcher= request.getRequestDispatcher("VisualizzazioneOrdiniAdmin.jsp");
					dispatcher.forward(request, response);
				} 
				catch (ClassNotFoundException | SQLException e) 
				{
					e.printStackTrace();
				}				
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
		}
		else if(opzione.equals("Utente"))
		{
			int utente= Integer.parseInt(request.getParameter("utenteOption"));
			System.out.println(utente);
			try 
			{
				ArrayList<DettagliOrdine> listaOrdini= dettagliOrdineDAO.ordiniPerUtente(utente);
				System.out.println(listaOrdini);
				JSONObject ordiniJson= new JSONObject();
				ordiniJson.put("ordini", listaOrdini);
				request.setAttribute("ordiniJson", ordiniJson);
				RequestDispatcher dispatcher= request.getRequestDispatcher("VisualizzazioneOrdiniAdmin.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
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
