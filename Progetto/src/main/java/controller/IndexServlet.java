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
 * Servlet che serve per l'homepage. 
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(IndexServlet.class.getName());
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() 
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
			ArrayList<Prodotto> ultimiProdotti= prodottoDAO.ultimiProdotti();
			JSONObject jsonUltimiProdotti= new JSONObject();
			for(int i=0; i<ultimiProdotti.size(); i++)
			{
				JSONObject prodotto= new JSONObject();
				prodotto.put("id", ultimiProdotti.get(i).getId());
				prodotto.put("nome", ultimiProdotti.get(i).getNome());
				prodotto.put("marca", ultimiProdotti.get(i).getMarca());
				prodotto.put("sesso", ultimiProdotti.get(i).getSesso());
				jsonUltimiProdotti.put("prodotto"+i, prodotto);
				ArrayList<Colore> colore= coloreDAO.colorePerId(ultimiProdotti.get(i).getId());
				for(int j=0; j<colore.size(); j++)
				{
					JSONObject coloreJson= new JSONObject();
					coloreJson.put("id", colore.get(j).getId());
					coloreJson.put("colore", colore.get(j).getColore());
					coloreJson.put("immagine", colore.get(j).getImmagine());
					coloreJson.put("prezzo", colore.get(j).getPrezzo());
					prodotto.put("colore"+j, coloreJson);
				}
			}
			request.setAttribute("ultimiProdotti", jsonUltimiProdotti);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Index.jsp");
			dispatcher.forward(request, response);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			logger.log(Level.INFO, "Exception", e);
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
