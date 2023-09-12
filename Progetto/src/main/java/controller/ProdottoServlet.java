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
 * Servlet implementation class ProdottoServlet
 */
@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottoServlet() 
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
			ArrayList<Prodotto> prodotti= prodottoDAO.prodotti();
			JSONObject jsonProdotti= new JSONObject();
			for(int i=0; i<prodotti.size(); i++)
			{
				JSONObject prodotto=new JSONObject();
				prodotto.put("id", prodotti.get(i).getId());
				prodotto.put("nome", prodotti.get(i).getNome());
				prodotto.put("descrizione", prodotti.get(i).getDescrizione());
				prodotto.put("marca", prodotti.get(i).getMarca());
				prodotto.put("sesso", prodotti.get(i).getSesso());
				jsonProdotti.put("prodotto"+i, prodotto);
				ArrayList<Colore> colore= coloreDAO.colorePerId(prodotti.get(i).getId());
				for(int j=0; j<colore.size(); j++)
				{
					JSONObject coloreJson=new JSONObject();
					coloreJson.put("id", colore.get(j).getId());
					coloreJson.put("colore", colore.get(j).getColore());
					coloreJson.put("immagine", colore.get(j).getImmagine());
					coloreJson.put("prezzo", colore.get(j).getPrezzo());
					coloreJson.put("quantità", colore.get(j).getQuantità());
					coloreJson.put("codiceProdotto", colore.get(j).getCodiceProdotto());
					prodotto.put("colore"+j, coloreJson);
				}
			}
			request.setAttribute("prodotti", jsonProdotti);
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("/Prodotti.jsp");
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