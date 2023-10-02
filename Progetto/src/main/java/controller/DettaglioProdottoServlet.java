package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Colore;
import model.ColoreDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class DettaglioProdottoServlet
 */
@WebServlet("/DettaglioProdottoServlet")
public class DettaglioProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioProdottoServlet() 
    {
        super();
        this.prodottoDAO= new ProdottoDAO();
        this.coloreDAO= new ColoreDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try
		{
			String id=request.getParameter("bottone");
			//int id=Integer.parseInt(request.getParameter("bottone"));//problema
			//System.out.println("DettaglioServlet: "+request.getParameter("prova"));
			System.out.println("DettaglioServlet: "+id);
			int indice=id.indexOf("/");
			//System.out.println("DettaglioServlet: "+indice);
			int idProdotto=Integer.parseInt(id.substring(0, indice));
			System.out.println("Id prodotto: "+idProdotto);
			//int idImmagine=Integer.parseInt(id.substring(indice+1));
			//System.out.println("Id immagine: "+idImmagine);
			String indiceColore=id.substring(indice+1);
			System.out.println("Colore: "+indiceColore);
			ArrayList<Prodotto> prodotti= prodottoDAO.dettagliProdottiPerId(idProdotto);
			//JSONObject jsonProdotti= new JSONObject();
			JSONObject prodotto= new JSONObject();
			for(int i=0; i<prodotti.size(); i++)
			{
				//JSONObject prodotto= new JSONObject();
				prodotto.put("id", prodotti.get(i).getId());
				prodotto.put("nome", prodotti.get(i).getNome());
				prodotto.put("descrizione", prodotti.get(i).getDescrizione());
				prodotto.put("marca", prodotti.get(i).getMarca());
				prodotto.put("sesso", prodotti.get(i).getSesso());
				//System.out.println("DettaglioServlet: "+prodotto);
				ArrayList<Colore> colore= coloreDAO.dettaglioColorePerId(idProdotto);
				for(int j=0; j<colore.size(); j++)
				{
					JSONObject coloreJson= new JSONObject();
					coloreJson.put("id", colore.get(j).getId());
					coloreJson.put("idProdotto", colore.get(j).getIdProdotto());
					coloreJson.put("colore", colore.get(j).getColore());
					coloreJson.put("immagine", colore.get(j).getImmagine());
					coloreJson.put("prezzo", colore.get(j).getPrezzo());
					coloreJson.put("quantità", colore.get(j).getQuantità());
					coloreJson.put("codiceProdotto", colore.get(j).getCodiceProdotto());
					prodotto.put("colore"+j, coloreJson);
				}
				//System.out.println("DettaglioServlet: "+prodotto);
			}
			System.out.println("DettaglioServlet: "+prodotto);
			request.setAttribute("prodotto", prodotto);
			request.setAttribute("coloreSelezionato", indiceColore);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		//questo è null.
		//request.getAttribute("prodotto");
		//restituisce tutto il json.
		//System.out.println("DettaglioServlet: "+request.getSession().getAttribute("prodotto"));
		//Otteniamo il parametro del form e ci restituisce quello che clicchiamo.
		//System.out.println("DettaglioServlet: "+request.getParameter("bottone"));
		//String prodotto="prodotto"+request.getParameter("bottone");
		//System.out.println("DettaglioServlet: "+prodotto);
		//Otteniamo il JSON.
		//JSONObject jsonProdotto=(JSONObject) request.getSession().getAttribute("prodotto");
		//System.out.println("DettaglioServlet: "+jsonProdotto.get(prodotto));
		//Otteniamo l'id.
		//System.out.println("DettaglioServlet: "+request.getSession().getAttribute("idProdotto"));
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