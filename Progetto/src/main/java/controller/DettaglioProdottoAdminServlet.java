package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Colore;
import model.ColoreDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet che serve per il dettaglio prodotto per l'amministratore. 
 */
@WebServlet("/DettaglioProdottoAdminServlet")
@MultipartConfig
public class DettaglioProdottoAdminServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(DettaglioProdottoAdminServlet.class.getName());
	
	private ProdottoDAO prodottoDAO;
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioProdottoAdminServlet() 
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
		
		int idProdotto= Integer.parseInt(request.getParameter("idProdotto"));
		Part filePart= request.getPart("uploadDocument");
		System.out.println("File: "+filePart.getSubmittedFileName().length());
		String path="D:\\Università\\2 anno\\2 semestre\\TSW\\RepoGithub\\Progetto\\src\\main\\webapp\\img\\prodotti\\";
		if(filePart.getSubmittedFileName().length()!=0)
		{
			String nomeFilePart= filePart.getSubmittedFileName();
			System.out.println("Nome file: "+nomeFilePart);
			filePart.write(path+nomeFilePart);
		}
		
		System.out.println("Path: "+path);
		String marcaProdotto= request.getParameter("marcaProdotto");
		String nomeProdotto= request.getParameter("nomeProdotto");
		String genereProdotto= request.getParameter("genereProdotto");
		String prezzoProdotto= request.getParameter("prezzoProdotto");
		String coloreProdotto= request.getParameter("coloreProdotto");
		System.out.println("Colore: "+coloreProdotto);
		String nomeColoreProdotto= request.getParameter("nomeColoreProdotto");
		System.out.println("Nome colore: "+nomeColoreProdotto);
		int quantitàProdotto= Integer.parseInt(request.getParameter("quantitaProdotto"));
		System.out.println("Quantità: "+quantitàProdotto);
		String immagine= request.getParameter("immagineProdotto");
		System.out.println("Immagine: "+immagine);
		String codiceProdotto= request.getParameter("codiceProdotto");
		System.out.println("Codice prodotto: "+codiceProdotto);
		
		int idColore= Integer.parseInt(request.getParameter("idColore"));
		System.out.println("Colore id: "+idColore);
		
		String valoreBottone= request.getParameter("bottone");
		System.out.println(valoreBottone);
		
		Prodotto prodotto= new Prodotto();
		prodotto.setId(idProdotto);
		prodotto.setNome(nomeProdotto);
		prodotto.setMarca(marcaProdotto);
		prodotto.setSesso(genereProdotto);
		Colore colore= new Colore();
		colore.setId(idColore);
		colore.setIdProdotto(idProdotto);
		colore.setColore(nomeColoreProdotto);
		colore.setImmagine(immagine);
		colore.setPrezzo(prezzoProdotto);
		colore.setQuantità(quantitàProdotto);
		colore.setCodiceProdotto(codiceProdotto);
		
		try 
		{
			if(valoreBottone.equals("update"))
			{
				prodottoDAO.aggiornaProdotto(prodotto);
				coloreDAO.aggiornaColore(colore);
			}
			else if(valoreBottone.equals("insert"))
			{
				coloreDAO.inserisciColore(colore);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			logger.log(Level.INFO, "Exception", e);
		}
		request.setAttribute("prodotti", request.getSession().getAttribute("prodottiAdmin"));
		RequestDispatcher dispatcher= request.getRequestDispatcher("/ProdottiAdmin.jsp");
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
