package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Carrello;
import model.ProdottoCarrello;
import model.Utente;

/**
 * Servlet che serve per la visualizzazione dei prodotti nel carello.
 */
@WebServlet("/VisualizzazioneCarrelloServlet")
public class VisualizzazioneCarrelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public VisualizzazioneCarrelloServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Utente utente=(Utente) request.getSession().getAttribute("utente");
		Carrello carrello;
		if(utente!=null)
		{
			carrello=(Carrello) request.getSession().getAttribute("carrelloUtente");
		}
		else
		{
			carrello=(Carrello) request.getSession().getAttribute("carrello");
		}
		if(carrello!=null)
		{
			String bottoneQuantità=(String) request.getParameter("quantitàBottone");
			if(bottoneQuantità!=null)
			{
				int indiceTrattino=bottoneQuantità.indexOf("-");
				String bottonePremuto=bottoneQuantità.substring(0, indiceTrattino);
				int indice=Integer.parseInt(bottoneQuantità.substring(indiceTrattino+1));
				ArrayList<ProdottoCarrello> listaProdotti=carrello.getListaProdotti();
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
				ArrayList<ProdottoCarrello> listaProdotti=carrello.getListaProdotti();
				
				JSONObject prodotto=(JSONObject) request.getSession().getAttribute("dettaglioProdotto");
				String coloreSelezionato=(String) request.getSession().getAttribute("coloreSelezionato");
				JSONObject colore=(JSONObject) prodotto.get(coloreSelezionato);
				if(Integer.parseInt(colore.get("id").toString())==listaProdotti.get(indice).getColore().getId())
				{
					int quantità= Integer.parseInt(colore.get("quantità").toString());
					int quantitàSospesa= listaProdotti.get(indice).getColore().getQuantità();
					colore.remove("quantità");
					colore.put("quantità", quantità+quantitàSospesa);
					prodotto.remove(coloreSelezionato);
					prodotto.put(coloreSelezionato, colore);
				}
				
				listaProdotti.remove(indice);
				if(listaProdotti.size()==0)
				{
					carrello=null;
					if(utente!=null)
					{
						request.getSession().setAttribute("carrelloUtente",carrello);
					}
					else
					{
						request.getSession().setAttribute("carrello",carrello);
					}
				}
				else
				{
					int quantitàCarrello=carrello.getQuantitaCarrello();
					carrello.setQuantitaCarrello(quantitàCarrello-1);
					if(utente!=null)
					{
						request.getSession().setAttribute("carrelloUtente",carrello);
					}
					else
					{
						request.getSession().setAttribute("carrello",carrello);
					}
				}
			}
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/Carrello.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}