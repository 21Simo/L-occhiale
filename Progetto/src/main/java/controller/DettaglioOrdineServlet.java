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
import model.DettagliOrdine;
import model.Ordine;
import model.OrdineDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.UtenteDAO;

/**
 * Servlet implementation class DettaglioOrdine
 */
@WebServlet("/DettaglioOrdineServlet")
public class DettaglioOrdineServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO;
	
	private OrdineDAO ordineDAO;
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioOrdineServlet() 
    {
        super();
        this.utenteDAO= new UtenteDAO();
        this.ordineDAO= new OrdineDAO();
        this.prodottoDAO= new ProdottoDAO();
        this.coloreDAO= new ColoreDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<DettagliOrdine> listaDettagliOrdini=(ArrayList<DettagliOrdine>) request.getSession().getAttribute("listaDettagliOrdini");
		//request.getAttribute("listaDettagliOrdini");
		System.out.println("DettaglioOrdineServlet: "+listaDettagliOrdini);
		
		int indiceDettaglioProdotto= Integer.parseInt(request.getParameter("dettaglioOrdine"));
		
		System.out.println("DettaglioOrdineServlet: "+indiceDettaglioProdotto);
		
		try 
		{
			String indirizzo= utenteDAO.indirizzoPerUtente(listaDettagliOrdini.get(indiceDettaglioProdotto).getIdUtente());
			System.out.println("DettaglioOrdineServlet: "+indirizzo);
			System.out.println("DettaglioOrdineServlet: "+listaDettagliOrdini.get(indiceDettaglioProdotto).getId());
			ArrayList<Ordine> listaOrdini= ordineDAO.ordinePerId(listaDettagliOrdini.get(indiceDettaglioProdotto).getId());
			System.out.println("DettagliOrdineServlet: "+listaOrdini.get(0).getId());
			ArrayList<Prodotto> prodotti= new ArrayList<Prodotto>();
			ArrayList<Colore> listaColore= new ArrayList<Colore>();
			for(int i=0; i<listaOrdini.size(); i++)
			{
				Prodotto prodotto= prodottoDAO.dettagliProdottiPerId(listaOrdini.get(i).getIdProdotto());
				System.out.println("DettaglioOrdineServlet file: "+listaOrdini.get(i).getFile());
				Colore colore= coloreDAO.colorePerIdColore(listaOrdini.get(i).getIdColore());
				String prezzo= coloreDAO.prezzo(colore.getPrezzo());
				System.out.println("DettaglioOrdineServlet prezzo: "+prezzo);
				colore.setPrezzo(prezzo);
				prodotto.setColore(colore);
				prodotti.add(prodotto);
				System.out.println("DettaglioOrdineServlet: "+prodotti.get(0).getNome());
				//listaColore.add(colore);
				//System.out.println("DettaglioOrdineServlet: "+listaColore);
			}
			JSONObject dettaglioOrdineJson= new JSONObject();
			System.out.println("DettaglioOrdine Servlet: "+prodotti.get(0));
			dettaglioOrdineJson.put("dettagliOrdine", listaDettagliOrdini.get(indiceDettaglioProdotto));
			dettaglioOrdineJson.put("indirizzo", indirizzo);
			dettaglioOrdineJson.put("prodotti", prodotti);
			dettaglioOrdineJson.put("ordine", listaOrdini);
			//dettaglioOrdineJson.put("coloriProdotti", listaColore);
			request.setAttribute("dettaglioOrdineJson", dettaglioOrdineJson);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/DettaglioOrdine.jsp");
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
