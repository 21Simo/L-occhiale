package controller;

import java.io.File;
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

import model.Carello;
import model.Colore;
import model.ColoreDAO;
import model.ProdottoCarello;
import model.Utente;

/**
 * Servlet implementation class ProvaServlet
 */
@WebServlet("/AggiungiCarelloServlet")
@MultipartConfig
public class AggiungiCarelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiCarelloServlet() 
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
		Carello carelloSessione;
		if(utente!=null)
		{
			if(request.getSession().getAttribute("carello")!=null)
			{
				carelloSessione=(Carello) request.getSession().getAttribute("carello");
				request.getSession().setAttribute("carello", null);
				request.getSession().setAttribute("carelloUtente", carelloSessione);
			}
			else
			{
				carelloSessione=(Carello) request.getSession().getAttribute("carelloUtente");
			}
		}
		else
		{
			carelloSessione=new Carello();
			carelloSessione= (Carello) request.getSession().getAttribute("carello");
		}
		ProdottoCarello prodottoSelezionato= new ProdottoCarello();
		prodottoSelezionato.setNome(nomeProdotto);
		prodottoSelezionato.setId(id);
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
		
		if(carelloSessione==null)
		{
			Carello carello=new Carello();
			carello.setQuantitaCarello(1);
			carello.setTotaleCosto(prezzoProdotto);
			carello.aggiungiProdotto(prodottoSelezionato);
			if(utente!=null)
			{
				request.getSession().setAttribute("carelloUtente", carello);
			}
			else
			{
				request.getSession().setAttribute("carello", carello);
			}
			ColoreDAO coloreDAO=new ColoreDAO();
			String totaleCosto=coloreDAO.prezzo(carello.getTotaleCosto());
		}
		else
		{
			int quantitàCarello=carelloSessione.getQuantitaCarello();
			quantitàCarello=quantitàCarello+1;
			carelloSessione.setQuantitaCarello(quantitàCarello);
			Double prezzoCarello=Double.parseDouble(carelloSessione.getTotaleCosto());
			prezzoCarello=prezzoCarello+Double.parseDouble(prezzoProdotto);
			carelloSessione.setTotaleCosto(prezzoCarello.toString());
			carelloSessione.aggiungiProdotto(prodottoSelezionato);
			Boolean duplicato=carelloSessione.duplicato(carelloSessione.getListaProdotti());
			if(duplicato==true)
			{
				quantitàCarello--;
				carelloSessione.setQuantitaCarello(quantitàCarello);
			}
			if(utente!=null)
			{
				request.getSession().setAttribute("carelloUtente", carelloSessione);
			}
			else
			{
				request.getSession().setAttribute("carello", carelloSessione);
			}
			ColoreDAO coloreDAO=new ColoreDAO();
			String totaleCosto=coloreDAO.prezzo(carelloSessione.getTotaleCosto());
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