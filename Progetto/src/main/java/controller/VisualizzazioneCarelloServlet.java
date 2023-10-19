package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carello;
import model.ProdottoCarello;

/**
 * Servlet che serve per la visualizzazione dei prodotti nel carello.
 */
@WebServlet("/VisualizzazioneCarelloServlet")
public class VisualizzazioneCarelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public VisualizzazioneCarelloServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Carello carello=(Carello) request.getSession().getAttribute("carello");
		if(carello!=null)
		{
			String bottoneQuantità=(String) request.getParameter("quantitàBottone");
			if(bottoneQuantità!=null)
			{
				int indiceTrattino=bottoneQuantità.indexOf("-");
				String bottonePremuto=bottoneQuantità.substring(0, indiceTrattino);
				int indice=Integer.parseInt(bottoneQuantità.substring(indiceTrattino+1));
				ArrayList<ProdottoCarello> listaProdotti=carello.getListaProdotti();
				int quantitàProdotto=listaProdotti.get(indice).getColore().getQuantità();
				switch (bottonePremuto)
				{
					case "meno":
						if(quantitàProdotto>1)
						{
							listaProdotti.get(indice).getColore().setQuantità(quantitàProdotto-1);
						}
						break;
					case "più":
						listaProdotti.get(indice).getColore().setQuantità(quantitàProdotto+1);
						break;
					default:
						break;
				}
			}
			else
			{
				int indice=Integer.parseInt(request.getParameter("prodotto"));
				ArrayList<ProdottoCarello> listaProdotti=carello.getListaProdotti();
				listaProdotti.remove(indice);
				if(listaProdotti.size()==0)
				{
					carello=null;
					request.getSession().setAttribute("carello", carello);
				}
				else
				{
					int quantitàCarello=carello.getQuantitaCarello();
					carello.setQuantitaCarello(quantitàCarello-1);
					request.getSession().setAttribute("carello", carello);
				}
			}
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/Carello.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}