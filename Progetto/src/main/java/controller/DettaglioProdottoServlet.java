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

import model.Carrello;
import model.Colore;
import model.ColoreDAO;
import model.Prodotto;
import model.ProdottoCarrello;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet che serve per i dettagli dei prodotti.
 */
@WebServlet("/DettaglioProdottoServlet")
public class DettaglioProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(DettaglioProdottoServlet.class.getName());
	
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
			Prodotto prodottoo= prodottoDAO.dettagliProdottiPerId(idProdotto);
			
			Utente utente=(Utente) request.getSession().getAttribute("utente");
			Carrello carrelloSessione;
			if(utente!=null)
			{
				carrelloSessione=(Carrello) request.getSession().getAttribute("carrelloUtente");
			}
			else
			{
				carrelloSessione=(Carrello) request.getSession().getAttribute("carrello");
			}
			
			JSONObject prodotto= new JSONObject();
			prodotto.put("id", prodottoo.getId());
			prodotto.put("nome", prodottoo.getNome());
			prodotto.put("descrizione", prodottoo.getDescrizione());
			prodotto.put("marca", prodottoo.getMarca());
			prodotto.put("sesso", prodottoo.getSesso());
			ArrayList<Colore> colore= coloreDAO.dettaglioColorePerId(idProdotto);
			for(int j=0; j<colore.size(); j++)
			{
				JSONObject coloreJson= new JSONObject();
				coloreJson.put("id", colore.get(j).getId());
				coloreJson.put("idProdotto", colore.get(j).getIdProdotto());
				coloreJson.put("colore", colore.get(j).getColore());
				coloreJson.put("immagine", colore.get(j).getImmagine());
				coloreJson.put("prezzo", colore.get(j).getPrezzo());
					
				int quantitàSospesa=0;
				if(carrelloSessione!=null)
				{
					ArrayList<ProdottoCarrello> listaCarrello= carrelloSessione.getListaProdotti();
					for(int k=0; k<listaCarrello.size(); k++)
					{
						if(listaCarrello.get(k).getColore().getId()==colore.get(j).getId())
						{
							quantitàSospesa= listaCarrello.get(k).getColore().getQuantità();
						}
					}
				}
					
				coloreJson.put("quantità", colore.get(j).getQuantità()-quantitàSospesa); 
				coloreJson.put("codiceProdotto", colore.get(j).getCodiceProdotto());
				prodotto.put("colore"+j, coloreJson);
			}
			request.setAttribute("prodotto", prodotto);
			request.setAttribute("coloreSelezionato", indiceColore);
			String admin= request.getParameter("admin");
			String azione= request.getParameter("azione");
			if(admin.equals("true"))
			{
				if(azione.equals("modifica"))
				{
					RequestDispatcher dispatcher= request.getRequestDispatcher("/DettaglioProdottoAdmin.jsp");
					dispatcher.forward(request, response);
				}
				else if(azione.equals("elimina"))
				{
					RequestDispatcher dispatcher= request.getRequestDispatcher("/EliminaProdotto.jsp");
					dispatcher.forward(request, response);
				}
			}
			else if(admin.equals("false"))
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("/DettaglioProdotto.jsp");
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