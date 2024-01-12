package controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class InserisciProdotto
 */
@WebServlet("/InserisciProdotto")
@MultipartConfig
public class InserisciProdotto extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
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
		
		String nomeProdotto= request.getParameter("nomeProdotto");
		String marcaProdotto= request.getParameter("marcaProdotto");
		String genereProdotto= request.getParameter("genereProdotto");
		System.out.println(request.getParameter("idColore"));
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
				Part filePart= request.getPart("immagine"+i);
				String nomeFile= filePart.getSubmittedFileName();
				System.out.println("File: "+nomeFile);
				filePart.write(path+nomeFile);
				prezzo[i-1]= request.getParameter("prezzoProdotto"+i).replace(',', '.');
				System.out.println(prezzo[i-1]);
				nomeColore[i-1]= request.getParameter("nomeColoreProdotto"+i);
				quantità[i-1]= Integer.parseInt(request.getParameter("quantitaProdotto"+i));
				codiceProdotto[i-1]= request.getParameter("codiceProdotto"+i);
				Colore colore= new Colore();
				colore.setIdProdotto(ultimoId);
				colore.setColore(nomeColore[i-1]);
				colore.setImmagine(nomeFile);
				colore.setPrezzo(prezzo[i-1]);
				colore.setQuantità(quantità[i-1]);
				colore.setCodiceProdotto(codiceProdotto[i-1]);
				coloreDAO.inserisciColore(colore);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		} 
		RequestDispatcher dispatcher= request.getRequestDispatcher("/DashboardAdmin.jsp");
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
