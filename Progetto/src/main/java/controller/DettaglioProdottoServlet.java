package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class DettaglioProdottoServlet
 */
@WebServlet("/DettaglioProdottoServlet")
public class DettaglioProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioProdottoServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getAttribute("prodotto");
		System.out.println("DettaglioServlet: "+request.getSession().getAttribute("prodotto"));
		System.out.println("DettaglioServlet: "+request.getParameter("bottone"));
		String prodotto="prodotto"+request.getParameter("bottone");
		System.out.println("DettaglioServlet: "+prodotto);
		JSONObject jsonProdotto=(JSONObject) request.getSession().getAttribute("prodotto");
		System.out.println("DettaglioServlet: "+jsonProdotto.get(prodotto));
		RequestDispatcher dispatcher= request.getRequestDispatcher("/DettaglioProdotto.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}