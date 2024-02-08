package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import model.Utente;

/**
 * Servlet che serve per inserire un prodotto. 
 */
@WebServlet("/InserisciProdotto")
@MultipartConfig
public class InserisciProdotto extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(InserisciProdotto.class.getName());
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciProdotto() 
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
		
		Utente utente=(Utente) request.getSession().getAttribute("utente");
		if(utente==null)
		{
			response.sendRedirect("Login.jsp");
		}
		else
		{		
			String nomeProdotto= request.getParameter("nomeProdotto");
			String marcaProdotto= request.getParameter("marcaProdotto");
			String genereProdotto= request.getParameter("genereProdotto");
			int colori= Integer.parseInt(request.getParameter("idColore"));
			String path="D:\\Università\\2 anno\\2 semestre\\TSW\\RepoGithub\\Progetto\\src\\main\\webapp\\img\\prodotti\\";
			String[] prezzo = new String[colori];
			String[] nomeColore= new String[colori];
			int[] quantità = new int[colori];
			String[] codiceProdotto = new String[colori];
			Prodotto prodotto= new Prodotto();
			prodotto.setNome(nomeProdotto);
			prodotto.setMarca(marcaProdotto);
			if(genereProdotto.equals("Uomo"))
			{
				prodotto.setSesso("M");
			}
			else if(genereProdotto.equals("Donna"))
			{
				prodotto.setSesso("F");
			}
			try 
			{
				prodottoDAO.inserisciProdotto(prodotto);
				int ultimoId= prodottoDAO.ultimoId();
				for(int i=1; i<=colori; i++)
				{
					String fileImmagine="";
					Part filePart= request.getPart("immagine"+i);
					String nomeFilePart= filePart.getSubmittedFileName();
					int indice= nomeFilePart.lastIndexOf(".");
					String uuid= UUID.randomUUID().toString();
					String nomeFile= nomeFilePart.substring(0, indice);
					String estensione= nomeFilePart.substring(indice);
					filePart.write(path+nomeFile+uuid+estensione);
					fileImmagine=nomeFile+uuid+estensione;
					prezzo[i-1]= request.getParameter("prezzoProdotto"+i).replace(',', '.');
					nomeColore[i-1]= request.getParameter("nomeColoreProdotto"+i);
					quantità[i-1]= Integer.parseInt(request.getParameter("quantitaProdotto"+i));
					codiceProdotto[i-1]= request.getParameter("codiceProdotto"+i);
					Colore colore= new Colore();
					colore.setIdProdotto(ultimoId);
					colore.setColore(nomeColore[i-1]);
					colore.setImmagine(fileImmagine);
					colore.setPrezzo(prezzo[i-1]);
					colore.setQuantità(quantità[i-1]);
					colore.setCodiceProdotto(codiceProdotto[i-1]);
					coloreDAO.inserisciColore(colore);
				}
				response.sendRedirect("DashboardAdminServlet");
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				logger.log(Level.INFO, "Exception", e);
			} 
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
