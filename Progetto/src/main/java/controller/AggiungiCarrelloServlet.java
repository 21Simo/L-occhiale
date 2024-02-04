package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import model.Carrello;
import model.Colore;
import model.ColoreDAO;
import model.ProdottoCarrello;
import model.Utente;

/**
 * Servlet che serve per aggiungere i prodotti al carrello.
 */
@WebServlet("/AggiungiCarrelloServlet")
@MultipartConfig
public class AggiungiCarrelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiCarrelloServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String immagineProdotto= request.getParameter("immagineProdotto");
		String marcaProdotto= request.getParameter("marcaProdotto");
		String nomeProdotto= request.getParameter("nomeProdotto");
		String prezzoProdotto= request.getParameter("prezzoProdotto");
		String gradazione= request.getParameter("gradazione");
		String coloreSelezionato= request.getParameter("coloreProdottoAttivo");
		int quantitàProdotto= Integer.parseInt(request.getParameter("quantitaProdotto"));
		JSONObject prodotto=(JSONObject) request.getSession().getAttribute("dettaglioProdotto");
		JSONObject coloreJSON=(JSONObject) prodotto.get(coloreSelezionato);
		int id=(Integer) coloreJSON.get("idProdotto");
		int idColore=(Integer) coloreJSON.get("id");
		String colorazione=(String) coloreJSON.get("colore");
		String codiceProdotto=(String) coloreJSON.get("codiceProdotto");
		
		Utente utente=(Utente) request.getSession().getAttribute("utente");
		Carrello carrelloSessione;
		if(utente!=null)
		{
			if(request.getSession().getAttribute("carrello")!=null)
			{
				carrelloSessione=(Carrello) request.getSession().getAttribute("carrello");
				request.getSession().setAttribute("carrello", null);
				request.getSession().setAttribute("carrelloUtente", carrelloSessione);
			}
			else
			{
				carrelloSessione=(Carrello) request.getSession().getAttribute("carrelloUtente");
			}
		}
		else
		{
			carrelloSessione=new Carrello();
			carrelloSessione= (Carrello) request.getSession().getAttribute("carrello");
		}
		ProdottoCarrello prodottoSelezionato= new ProdottoCarrello();
		prodottoSelezionato.setNome(nomeProdotto);
		prodottoSelezionato.setId(id);
		prodottoSelezionato.setMarca(marcaProdotto);
		Colore coloreSelezionatoOggetto=new Colore();
		coloreSelezionatoOggetto.setId(idColore);
		coloreSelezionatoOggetto.setImmagine(immagineProdotto);
		coloreSelezionatoOggetto.setColore(colorazione);
		coloreSelezionatoOggetto.setPrezzo(prezzoProdotto);
		coloreSelezionatoOggetto.setQuantità(quantitàProdotto);
		coloreSelezionatoOggetto.setCodiceProdotto(codiceProdotto);
		prodottoSelezionato.setColore(coloreSelezionatoOggetto);
		prodottoSelezionato.setGradazione(gradazione);
		if(gradazione.equals("Graduati"))
		{
			Part filePart= request.getPart("uploadDocument");
			String path="D:\\Università\\2 anno\\2 semestre\\TSW\\RepoGithub\\Progetto\\src\\main\\webapp\\tmp file\\";
			String nomeFilePart= filePart.getSubmittedFileName();
			int indice= nomeFilePart.lastIndexOf(".");
			String uuid= UUID.randomUUID().toString();
			String nomeFile= nomeFilePart.substring(0, indice);
			String estensione= nomeFilePart.substring(indice);
			filePart.write(path+nomeFile+uuid+estensione);
			prodottoSelezionato.setPath(path+nomeFile+uuid+estensione);
			prodottoSelezionato.setNomeFile(nomeFilePart);
		}
		else
		{
			prodottoSelezionato.setPath(null);
			prodottoSelezionato.setNomeFile("null");
		}
		
		if(carrelloSessione==null)
		{
			Carrello carrello=new Carrello();
			carrello.setQuantitaCarrello(1);
			carrello.setTotaleCosto(prezzoProdotto);
			carrello.aggiungiProdotto(prodottoSelezionato);
			if(utente!=null)
			{
				request.getSession().setAttribute("carrelloUtente", carrello);
			}
			else
			{
				request.getSession().setAttribute("carrello", carrello);
			}
			ColoreDAO coloreDAO=new ColoreDAO();
			String totaleCosto=coloreDAO.prezzo(carrello.getTotaleCosto());
		}
		else
		{
			int quantitàCarrello=carrelloSessione.getQuantitaCarrello();
			quantitàCarrello=quantitàCarrello+1;
			carrelloSessione.setQuantitaCarrello(quantitàCarrello);
			Double prezzoCarrello=Double.parseDouble(carrelloSessione.getTotaleCosto());
			prezzoCarrello=prezzoCarrello+Double.parseDouble(prezzoProdotto);
			carrelloSessione.setTotaleCosto(prezzoCarrello.toString());
			carrelloSessione.aggiungiProdotto(prodottoSelezionato);
			Boolean duplicato=carrelloSessione.duplicato(carrelloSessione.getListaProdotti());
			if(duplicato==true)
			{
				quantitàCarrello--;
				carrelloSessione.setQuantitaCarrello(quantitàCarrello);
			}
			if(utente!=null)
			{
				request.getSession().setAttribute("carrelloUtente", carrelloSessione);
			}
			else
			{
				request.getSession().setAttribute("carrello", carrelloSessione);
			}
			ColoreDAO coloreDAO=new ColoreDAO();
			String totaleCosto=coloreDAO.prezzo(carrelloSessione.getTotaleCosto());
		}
		request.setAttribute("prodotto", prodotto);
		request.setAttribute("coloreSelezionato", coloreSelezionato);
		request.setAttribute("modal", true);
		RequestDispatcher dispatcher= request.getRequestDispatcher("DettaglioProdotto.jsp");
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