package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Carello;
import model.Colore;
import model.ColoreDAO;
import model.Prodotto;
import model.ProdottoCarello;
import model.Utente;

/**
 * Servlet che serve per i dati da inviare al modal tramite JSON.
 */
@WebServlet("/CarelloServlet")
public class CarelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public CarelloServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome= request.getParameter("nome");
		String immagine= request.getParameter("immagine");
		int quantità= Integer.parseInt(request.getParameter("quantità"));
		String gradazione= request.getParameter("graduati");
		String file= request.getParameter("file");
		
		JSONObject prodotto=(JSONObject) request.getSession().getAttribute("dettaglioProdotto");
		String colore=request.getParameter("colore");
		JSONObject coloreJSON=(JSONObject) prodotto.get(colore);
		String colorazione=(String) coloreJSON.get("colore");
		String prezzo=(String) coloreJSON.get("prezzo");
		String codiceProdotto=(String) coloreJSON.get("codiceProdotto");
		
		Utente utente=(Utente) request.getSession().getAttribute("utente");
		System.out.println("CarelloServlet utente: "+utente);
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
			System.out.println("CarelloServlet carello utente: "+carelloSessione);
		}
		else
		{
			carelloSessione=new Carello();
			carelloSessione= (Carello) request.getSession().getAttribute("carello");
		}
		ProdottoCarello prodottoSelezionato= new ProdottoCarello();
		prodottoSelezionato.setNome(nome);
		Colore coloreSelezionato=new Colore();
		coloreSelezionato.setImmagine(immagine);
		coloreSelezionato.setColore(colorazione);
		coloreSelezionato.setPrezzo(prezzo);
		coloreSelezionato.setQuantità(quantità);
		coloreSelezionato.setCodiceProdotto(codiceProdotto);
		prodottoSelezionato.setColore(coloreSelezionato);
		prodottoSelezionato.setGradazione(gradazione);
		if(gradazione.equals("Graduati"))
		{
			prodottoSelezionato.setNomeFile(file);
		}
		else
		{
			prodottoSelezionato.setNomeFile("null");
		}
		
		request.setAttribute("prova", "daiiii");
		response.setContentType("application/json");
		PrintWriter out= response.getWriter();
		JSONObject json=new JSONObject();
		json.put("nome", nome);
		json.put("immagine", immagine);
		json.put("colorazione", colorazione);
		json.put("prezzo", prezzo);
		
		if(carelloSessione==null)
		{
			Carello carello=new Carello();
			carello.setQuantitaCarello(1);
			carello.setTotaleCosto(prezzo);
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
			json.put("totaleCarello", totaleCosto);
			json.put("quantitàCarello", carello.getQuantitaCarello());
		}
		else
		{
			int quantitàCarello=carelloSessione.getQuantitaCarello();
			quantitàCarello=quantitàCarello+1;
			carelloSessione.setQuantitaCarello(quantitàCarello);
			Double prezzoCarello=Double.parseDouble(carelloSessione.getTotaleCosto());
			prezzoCarello=prezzoCarello+Double.parseDouble(prezzo);
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
			json.put("totaleCarello", totaleCosto);
			json.put("quantitàCarello", carelloSessione.getQuantitaCarello());
		}
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
