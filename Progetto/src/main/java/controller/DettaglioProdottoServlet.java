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
 * Servlet che serve per i dettagli dei prodotti.
 */
@WebServlet("/DettaglioProdottoServlet")
public class DettaglioProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    public DettaglioProdottoServlet() 
    {
        super();
        this.prodottoDAO= new ProdottoDAO();
        this.coloreDAO= new ColoreDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try
		{
			String id=request.getParameter("bottone");
			int indice=id.indexOf("/");
			int idProdotto=Integer.parseInt(id.substring(0, indice));
			String indiceColore=id.substring(indice+1);
			ArrayList<Prodotto> prodotti= prodottoDAO.dettagliProdottiPerId(idProdotto);
			JSONObject prodotto= new JSONObject();
			for(int i=0; i<prodotti.size(); i++)
			{
				prodotto.put("id", prodotti.get(i).getId());
				prodotto.put("nome", prodotti.get(i).getNome());
				prodotto.put("descrizione", prodotti.get(i).getDescrizione());
				prodotto.put("marca", prodotti.get(i).getMarca());
				prodotto.put("sesso", prodotti.get(i).getSesso());
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
			}
			request.setAttribute("prodotto", prodotto);
			request.setAttribute("coloreSelezionato", indiceColore);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/DettaglioProdotto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}