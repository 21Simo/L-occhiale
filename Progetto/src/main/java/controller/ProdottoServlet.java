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

import model.Colore;
import model.ColoreDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet che serve per la visualizzazione dei prodotti.
 */
@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(ProdottoServlet.class.getName());
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    public ProdottoServlet() 
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
			ArrayList<Prodotto> prodotti= prodottoDAO.prodotti();
			JSONObject jsonProdotti= new JSONObject();
			for(int i=0; i<prodotti.size(); i++)
			{
				JSONObject prodotto=new JSONObject();
				prodotto.put("id", prodotti.get(i).getId());
				prodotto.put("nome", prodotti.get(i).getNome());
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
					prodotto.put("colore"+j, coloreJson);
				}
			}
			request.setAttribute("prodotti", jsonProdotti);
			
			String header=request.getParameter("prodotti");
			
			if(header.equals("header"))
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("/Prodotti.jsp");
				dispatcher.forward(request, response);
			}
			else if(header.equals("dashboard"))
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("/ProdottiAdmin.jsp");
				dispatcher.forward(request, response);
			}			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			logger.log(Level.INFO, "Exception", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}